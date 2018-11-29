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
    //    private static final String PATH = Environment.getExternalStorageDirectory()
    //            + File.separator + BuildConfig.APPLICATION_ID + File.separator;
    private val PATH =Thread.currentThread().contextClassLoader.getResource("")!!.toString() ;

    /**
     * 数据文件路径
     */
    val MUSIC_PATH = PATH + "data" + File.separator

    /**
     * 日志路径
     */
    val LOG_PATH = PATH + "log" + File.separator

}
