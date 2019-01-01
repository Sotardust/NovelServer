package com.dai.utils.file


import java.io.File

/**
 * 放置各个文件路径名
 *
 *
 * Created by Administrator on 2018/6/26.
 */

object PathUtil {

    /**
     * 以包名作为文件总路径
     */
//    private val PATH =Thread.currentThread().contextClassLoader.getResource("")!!.toString() ;

    //windows测试路径
    private val PATH = "F:\\testmusic\\"
//    //Linux测试路径
//    private val PATH  = "/var/workfile/music/"
    /**
     * 数据文件路径
     */
    open val MUSIC_PATH = PATH + "music" + File.separator

    /**
     * 日志路径
     */
    val LOG_PATH = PATH + "log" + File.separator

}
