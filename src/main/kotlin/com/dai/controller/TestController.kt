package com.dai.controller

import com.dai.service.LoginService
import com.dai.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by dai on 2018/1/29.
 */
@Controller
@RequestMapping("/mobile")
class TestController @Autowired
constructor(private val loginService: LoginService, private val tokenService: TokenService) {

    var files = ArrayList<File>()

    @ResponseBody
    @RequestMapping("/get_accounts", method = [(RequestMethod.GET)])
    fun getAllAccounts(httpServletRequest: HttpServletRequest): Any {
        var reslut = tokenService.verifyToken(httpServletRequest)
        println("loginService = ${loginService}")
        if (reslut == null) reslut = loginService.getAllAccount()
        return reslut
    }

    @ResponseBody
    @RequestMapping("/getFiles", method = [(RequestMethod.GET)])
    fun getMusic(): Any {
        getFileList("/var/workfile/music")
        return files;
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