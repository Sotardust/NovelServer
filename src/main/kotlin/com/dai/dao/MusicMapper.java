package com.dai.dao;


import com.dai.bean.music.CloudMusic;
import com.dai.bean.music.MusicLibrary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {


    void insertMusicLibrary(MusicLibrary musicLibrary);

    void insertCloudMusic(CloudMusic cloudMusic);

    List<MusicLibrary> getAllMusics();

    List<CloudMusic> getCloudMusics(int personId);

    List<String> getAllNames();

    Long getMusicId(String name);

    List<String> getNames(String personId);

    String getMusicPath(String name);
}
