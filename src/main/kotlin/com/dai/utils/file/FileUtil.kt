package com.dai.utils.file


import java.util.logging.Logger

/**
 * Created by dai on 2018/6/1.
 */

object FileUtil {

    private val logger = Logger.getLogger(FileUtil.javaClass.name)

    /**
     * 日志文件名称
     */
    val LOG_FILE = "log.txt"

    /**
     * 创建日志文件log.txt
     */
    fun createLogFile() {
        logger.info("createLogFile: ")
        val log = PathUtil.LOG_PATH + LOG_FILE
        FileManager.getInstance()?.createNewFile(log)
    }
}
