package com.dai.dao;


import com.dai.bean.music.MusicLibrary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {

    List<MusicLibrary> getAllMusics();

    void insertMusicLibrary(MusicLibrary musicLibrary);

    List<String> getAllNames();
}
