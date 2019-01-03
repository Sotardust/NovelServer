package com.dai.service.music;


import com.dai.bean.music.MusicLibrary;
import com.dai.dao.MusicMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MusicService {

    private static final String TAG = "MusicService";

    private final Logger logger = Logger.getLogger(MusicService.class);

    private final MusicMapper musicMapper;

    @Autowired
    public MusicService(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    /**
     * 向音乐数据库中插入数据
     *
     * @param library MusicLibrary实体类
     */
    public void insertMusic(MusicLibrary library) {
        try {
            musicMapper.insertMusicLibrary(library);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(TAG, e);
        }
    }

    /**
     * 判断音乐云盘中是否已经存在该音乐
     *
     * @param name 音乐名称
     * @return boolean
     */
    public boolean isExist(String name) {
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
