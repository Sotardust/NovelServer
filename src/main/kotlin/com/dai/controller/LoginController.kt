package com.dai.controller

import com.dai.service.login.LoginService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.ArrayList
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap
import kotlin.collections.set


/**
 * Created by dai on 2018/1/29.
 */
@Suppress("DEPRECATION")
@Controller
@RequestMapping("/mobile")
class LoginController @Autowired
constructor(private val loginService: LoginService) {

    @ResponseBody
    @RequestMapping("/login", method = [RequestMethod.POST])
    fun login(@RequestParam(value = "account", required = true) account: String,
              @RequestParam(value = "password", required = true) password: String,
              httpServletResponse: HttpServletResponse,
              httpServletRequest: HttpServletRequest): Any? {
        val token = httpServletRequest.cookies

        println("token LoginController = ${token}")
        println("account LoginController = ${account}")
        var result = loginService.returnResult(account, password);
        return result
    }

    @ResponseBody
    @RequestMapping("/no_sleep", method = [RequestMethod.GET])
    fun getNoSleep(): Any? {
        val result = HashMap<String, Any>()
        result["data"] = "无睡眠时间"
        return result
    }

    @ResponseBody
    @RequestMapping("/sleep_two", method = [RequestMethod.GET])
    fun getSleep2Second(): Any? {
        val result = intArrayOf(20)

        try {
            for (i in 0..19) {
                result[i] = i;
            }
            Thread.sleep(2000)
        } catch (e: Exception) {

        }
        return result
    }

    @ResponseBody
    @RequestMapping("/sleep_three", method = [RequestMethod.GET])
    fun getSleep3Second(): Any? {
        val strings = arrayOfNulls<String>(20)
        try {
            for (i in 0..19) {
                strings[i] = "延迟3秒数据" + i
            }
            Thread.sleep(3000)
        } catch (e: Exception) {

        }

        return strings;
    }

    @ResponseBody
    @RequestMapping("/sleep_five", method = [RequestMethod.GET])
    fun getSleep5Second(): Any? {
        val strings = arrayOfNulls<String>(20)
        try {
            for (i in 0..19) {
                strings[i] = "延迟5秒数据" + i
            }
            Thread.sleep(5000)
        } catch (e: Exception) {

        }
        return strings
    }

    var files = ArrayList<File>()
    @ResponseBody
    @RequestMapping("/getFiles", method = [(RequestMethod.GET)])
    fun getMusic(): Any {
        if (files.size > 0) files.clear()
//        getFileList("/var/workfile/music")
        getFileList("E:\\music")
        return files;
    }

    @ResponseBody
    @RequestMapping("/music/fushishanxia", method = [(RequestMethod.GET)])
    fun getSingleSong(): InputStream {
        val fileInput = FileInputStream("E:\\music\\陈奕迅 - 富士山下.mp3")
        val song = Song()
//        song.fileInputStream = FileInputStream("/var/workfile/music/陈奕迅 - 富士山下.mp3");
        song.fileInputStream = fileInput

        fileInput.close()
        return song.fileInputStream
    }

    @ResponseBody
    @RequestMapping("/music/qiansixi", method = [(RequestMethod.GET)])
    fun getSingleSong1(): Any {
        val filename = "富士山下样.mp3"
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "attachment;filename=" + filename);
//        filename = String(filename.("gbk"), "iso8859-1")
//        val fileInput = FileInputStream("/var/workfile/music/陈奕迅 - 富士山下.mp3")
        val fileInput = FileInputStream("F:\\testmusic")
        val b = ByteArray(fileInput.available())
        fileInput.read(b)
        headers.setContentDispositionFormData("attachment",filename)
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM
        println("filename = $filename")
        val response = ResponseEntity(b, headers, HttpStatus.OK);

        return response
    }


    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
    }

    fun getFileList(path: String) {
        val file = File(path)
        try {
            when {
                file.isDirectory -> {
                    val fs = file.listFiles()
                    (0..fs.size - 3)
                            .map { fs[it].absolutePath }
                            .forEach {
                                getFileList(it)
                            }
                }
                file.isFile -> {
                    val fName = file.absoluteFile;
                    files.add(fName)
                    println("fName.name = ${fName.name}")
                    println("fName.totalSpace = ${fName.totalSpace}")
                    println("fName.length = ${fName.length()}")
                }
                else -> System.out.println("路径不正确!")
            }
        } catch (e: Exception) {
            println("e.message = $e")
        }
    }
}