package com.dai.utils.file


import java.io.File
import java.io.IOException
import java.util.*
import java.util.logging.Logger

/**
 * 文件操作管理工具
 * Created by Administrator on 2018/6/26.
 */

class FileManager
private constructor() {

    private val logger = Logger.getLogger(TAG)

    init {
        initDirectory()
    }

    /**
     * 初始化文件目录
     */
    private fun initDirectory() {
        createDirectory()
    }

    /**
     * 创建文件目录
     */
    private fun createDirectory() {
        val directoryList = ArrayList<String>()
        directoryList.add(PathUtil.MUSIC_PATH)
        directoryList.add(PathUtil.LOG_PATH)
        for (path in directoryList) {
            val directory = File(path)
            if (!directory.exists()) {
                val isSuccessful = directory.mkdirs()
                logger.info("createDirectory: isSuccessful$isSuccessful")
            }
        }
    }

    /**
     * 创建新文件
     *
     * @param path 文件路径
     * @return 文件对象
     */
    fun createNewFile(path: String): File {
        //        LogUtil.writeInfo(TAG, "createNewFile", path);
        val file = File(path)
        try {
            if (!file.exists()) {
                val isSuccessful = file.createNewFile()
                //                Log.d(TAG, "createNewFile: " + path + (isSuccessful ? "成功" : "失败"));
            }
        } catch (e: IOException) {
            //            LogUtil.writeErrorInfo(TAG, "createNewFile", e.toString());
            e.printStackTrace()
        }

        return file
    }

    companion object {
        private val TAG = "FileManager"
        private var instance: FileManager? = null

        fun getInstance(): FileManager? {
            if (instance == null) {
                synchronized(FileManager::class.java) {
                    if (instance == null) {
                        instance = FileManager()
                    }
                }
            }
            return this.instance
        }
    }
}
