package com.dai.bean.music

data class MusicLibrary(

        val musicId: Long, //音乐对应唯一ID
        val name: String, //音乐名称
        val path: String, //存放路径
        val type: String,//音乐类型
        val duration: String//音乐时长
)