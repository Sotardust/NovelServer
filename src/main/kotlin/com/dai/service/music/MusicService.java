package com.dai.service.music;


import com.dai.bean.model.BaseModel;
import com.dai.bean.music.CloudMusic;
import com.dai.bean.music.MusicLibrary;
import com.dai.dao.MusicMapper;
import com.dai.service.StatusService;
import com.dai.utils.file.PathUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {

    private static final String TAG = "MusicService";

    private final Logger logger = Logger.getLogger(MusicService.class);

    private final MusicMapper musicMapper;

    @Autowired
    private StatusService statusService;

    @Autowired
    public MusicService(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }


    /**
     * 批量上传音乐
     *
     * @param files              MultipartFile 集合
     * @param httpServletRequest request
     * @return BaseModel
     */
    public BaseModel<ArrayList<String>> uploadMusic(ArrayList<MultipartFile> files,
                                                    HttpServletRequest httpServletRequest) {
        ArrayList<String> errorList = new ArrayList<>();
        long id = System.currentTimeMillis();
        BaseModel<ArrayList<String>> baseModel = new BaseModel<>();
        for (int i = 0; i < files.size(); i++) {
            try {
                baseModel.setCode(0);
                final MultipartFile multipartFile = files.get(i);
                final String path = PathUtil.INSTANCE.getMUSIC_PATH() + URLDecoder.decode(multipartFile.getOriginalFilename(), "UTF-8");
                long musicId = id + i;
                final File file = new File(path);
                final String name = file.getName();
                logger.info(name);
                final String type = name.split("\\.")[1];
                final int duration = 0;
                final int personId = statusService.getPersonId(httpServletRequest);

                if (!isExist(name)) {//若音乐库中不存在则进行保存
                    multipartFile.transferTo(file);
                    MusicLibrary musicLibrary = new MusicLibrary(musicId, name, path, type, duration);
                    insertMusic(musicLibrary);
                } else {
                    musicId = getMusicId(name);
                }

                List<String> musicList = getNames(personId);
                if (musicList != null) {
                    if (!musicList.contains(name)) { //若数据库中没有人员对应歌曲则进行保存
                        CloudMusic cloudMusic = new CloudMusic(personId, musicId, name, path, type, duration);
                        insertCloudMusic(cloudMusic);
                    }
                }

            } catch (IOException e) {
                errorList.add(files.get(i).getName());
                e.printStackTrace();
            }
        }
        baseModel.setMsg("");
        baseModel.setResult(errorList);
        return baseModel;
    }


    /**
     * 根据用户名Id 获取云盘音乐数据
     *
     * @param httpServletRequest request
     * @return List<CloudMusic> 的实体类
     */
    public BaseModel<List<CloudMusic>> getCloudMusics(HttpServletRequest httpServletRequest) {

        final int personId = statusService.getPersonId(httpServletRequest);
        BaseModel<List<CloudMusic>> baseModel = new BaseModel<>();
        baseModel.setCode(0);
        baseModel.setMsg("");
        baseModel.setResult(musicMapper.getCloudMusics(personId));
        return baseModel;
    }

    /**
     * 向音乐数据库中插入数据
     *
     * @param library MusicLibrary实体类
     */
    private void insertMusic(MusicLibrary library) {
        try {
            musicMapper.insertMusicLibrary(library);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TAG, e);
        }
    }

    /**
     * 向音乐云盘数据库中插入数据
     *
     * @param music CloudMusic实体类
     */
    private void insertCloudMusic(CloudMusic music) {
        try {
            musicMapper.insertCloudMusic(music);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TAG, e);
        }
    }


    /**
     * 返回personId对应云盘音乐
     *
     * @param personId 人员ID
     * @return name 数据集合
     */
    private List<String> getNames(int personId) {
        return musicMapper.getNames(String.valueOf(personId));
    }

    /**
     * 返回personId对应云盘音乐
     *
     * @param name 歌曲名称
     * @return 歌曲其对应ID
     */
    private Long getMusicId(String name) {
        return musicMapper.getMusicId(name);
    }

    /**
     * 判断音乐云盘中是否已经存在该音乐
     *
     * @param name 音乐名称
     * @return boolean
     */
    private boolean isExist(String name) {
        try {
            List<String> localName = musicMapper.getAllNames();
            for (String value : localName) {
                if (value.equals(name)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TAG, e);
        }
        return false;
    }
}
