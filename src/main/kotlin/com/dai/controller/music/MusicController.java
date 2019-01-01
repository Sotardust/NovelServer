package com.dai.controller.music;

import com.dai.bean.model.BaseModel;
import com.dai.bean.music.MusicLibrary;
import com.dai.service.music.MusicService;
import com.dai.utils.file.PathUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
@RequestMapping("/message")
public class MusicController {


    private final Logger logger = Logger.getLogger(MusicController.class);

    private final MusicService musicService;
    private int anInt = 0;


    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    /**
     * 批量上传音乐文件或上传单个文件
     *
     * @param files 文件集合
     * @return BaseModel<ArrayList       <       String>>
     */
    @ResponseBody
    @RequestMapping(value = "/uploadmusic", method = RequestMethod.POST)
    public BaseModel<ArrayList<String>> uploadMusic(@RequestParam(value = "file") ArrayList<MultipartFile> files) {

        ArrayList<String> errorList = new ArrayList<>();
        long id = System.currentTimeMillis();
        BaseModel<ArrayList<String>> baseModel = new BaseModel<>();
        for (int i = 0; i < files.size(); i++) {
            try {
                final MultipartFile multipartFile = files.get(i);
                final String path = PathUtil.INSTANCE.getMUSIC_PATH() + URLDecoder.decode(multipartFile.getOriginalFilename(), "UTF-8");
                long musicId = id + i;
                final File file = new File(path);
                final String name = file.getName();
                logger.info(name);
                final String type = name.split("\\.")[1];
                final String duration = "";
                multipartFile.transferTo(file);
                MusicLibrary musicLibrary = new MusicLibrary(musicId, name, path, type, duration);
                musicService.insertMusic(musicLibrary);
                baseModel.setCode(0);
            } catch (IOException e) {
                errorList.add(files.get(i).getName());
                e.printStackTrace();
            }
        }
        baseModel.setMsg("");
        baseModel.setResult(errorList);
        return baseModel;
    }

}
