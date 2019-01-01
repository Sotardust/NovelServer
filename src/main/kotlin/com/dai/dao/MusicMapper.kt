package com.dai.dao

import com.dai.bean.music.MusicLibrary
import org.apache.ibatis.annotations.Mapper

/**
 * Created by dai on 2018/12/26.
 */
@Mapper
interface MusicMapper {

    fun getAllMusics(): List<MusicLibrary>
    fun insertMusicLibrary(musicLibrary: MusicLibrary)
}