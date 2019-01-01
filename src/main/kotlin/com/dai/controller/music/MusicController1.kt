//package com.dai.controller.music
//
//import com.alibaba.fastjson.JSON
//import com.dai.bean.model.BaseModel
//import com.dai.bean.model.LoginModel
//import com.dai.bean.music.MusicLibrary
//import com.dai.service.music.MusicService
//import com.dai.utils.file.FileUtil
//import com.dai.utils.file.PathUtil
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestMethod
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.ResponseBody
//import org.springframework.web.multipart.MultipartFile
//import org.springframework.web.multipart.commons.CommonsMultipartFile
//import java.beans.Encoder
//import java.io.File
//import java.io.IOException
//import java.net.URLDecoder
//import java.net.URLEncoder
//import java.sql.SQLException
//import java.util.logging.Logger
//import javax.servlet.http.HttpServletRequest
//
//
//@Controller
//@RequestMapping("/message")
//class MusicController1 @Autowired
//constructor(private val musicService: MusicService) {
//
//    private val logger = Logger.getLogger(FileUtil.javaClass.name)
//
//    @ResponseBody
//    @RequestMapping(value = ["/addmusic"], method = [(RequestMethod.POST)])
//    fun insertMusic(@RequestParam(value = "name", required = true) name: String,
//                    @RequestParam(value = "password", required = true) password: String,
//                    httpServletRequest: HttpServletRequest): Any {//接收数据
//
//        var musicId = 123456L
//        var name = "123"
//        var path = "12346"
//        var type = "123"
//        var duration = "3:80"
//        val musicLibrary = MusicLibrary(musicId, name, path, type, duration)
//
//        return musicService.insertMusic(musicLibrary)
//
//    }
//
//    @ResponseBody
//    @RequestMapping(value = ["/uploadmusic"], method = [(RequestMethod.POST)])
//    fun uploadMusic(@RequestParam(value = "file", required = true) files: Array<MultipartFile>): Any {//接收数据
//
//        val errorArray = arrayListOf<String>()
//        val baseModel = BaseModel<Array<String>>();
//
//        val id = System.currentTimeMillis() / 100000
//        for (i in 0..files.size - 1) {
//            val path = PathUtil.MUSIC_PATH + URLDecoder.decode(files.get(i).originalFilename, "UTF-8")
//            try {
//                val musicId = id + 1;
//                val name = files.get(i).name
//                val type = name.split("\\.")[1]
//                val duration = ""
//                files.get(i).transferTo(File(path))
//                val musicLibrary = MusicLibrary(musicId, name, path, type, duration)
//                musicService.insertMusic(musicLibrary)
//                    baseModel.code = 0
//            } catch (e: IOException) {
//                baseModel.code = 1
//                logger.warning(e.toString())
//                baseModel.msg ="上传文件不合法"
//            } catch (e: IllegalStateException) {
//                baseModel.code = 1
//                baseModel.msg ="上传文件不合法"
//                logger.warning(e.toString())
//            }
//        }
//
//        var musicId = 123456L
//        var name = "123"
//        var path = "12346"
//        var type = "123"
//        var duration = "3:80"
//
////        return musicService.uploadMusic()
//        loginModel.code = 1
//        loginModel.msg = "测试返回结果"
//        return JSON.toJSONString(loginModel)
//
//    }
//
//}
