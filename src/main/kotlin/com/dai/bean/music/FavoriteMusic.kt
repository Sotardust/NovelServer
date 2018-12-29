package com.dai.bean.music

data class FavoriteMusic(

        val personId: Long,//人员ID
        val musicId: Long, //音乐对应唯一ID
        val name: String, //音乐名称
        val path: String, //存放路径
        val duration: String,//音乐时长
        val like: Boolean//是否喜欢
)