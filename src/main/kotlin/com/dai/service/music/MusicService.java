package com.dai.service.music;


import com.dai.bean.music.MusicLibrary;
import com.dai.dao.MusicMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
     * 像音乐数据库中插入数据
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
}
