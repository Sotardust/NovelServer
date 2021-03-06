package com.dai.controller.music;

import com.dai.bean.model.BaseModel;
import com.dai.bean.music.CloudMusic;
import com.dai.service.music.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    /**
     * 批量上传音乐文件或上传单个文件
     *
     * @param files 文件集合
     * @return BaseModel
     */
    @ResponseBody
    @RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
    public BaseModel<ArrayList<String>> uploadMusic(@RequestParam(value = "file") ArrayList<MultipartFile> files,
                                                    HttpServletRequest httpServletRequest) {

        return musicService.uploadMusic(files, httpServletRequest);
    }

    /**
     * 批量上传音乐文件或上传单个文件
     *
     * @return BaseModel
     */
    @ResponseBody
    @RequestMapping(value = "/getCloudMusics", method = RequestMethod.GET)
    public BaseModel<List<CloudMusic>> getCloudMusics(HttpServletRequest httpServletRequest) {

        return musicService.getCloudMusics(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/downloadMusic")
    public BaseModel<String> downloadMusic(@RequestParam("songName") String songName) {
        return musicService.downloadMusic(songName);
    }


}
