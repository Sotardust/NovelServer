package com.dai.bean.music


//所有人员歌单列表
data class SongList(
        val personId: Int, //人员id
        val name: String, //歌单名字
        val createTime: String, //创建时间
        val introduction: String  //歌单简介
)