package com.dai.controller

import com.dai.bean.UserInfo
import com.dai.service.RegisterService
import com.dai.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by dai on 2018/1/25.
 */
@Controller
@RequestMapping("/message")
class RegisterController
@Autowired
constructor(private val registerService: RegisterService, private val tokenService: TokenService) {

    @ResponseBody
    @RequestMapping(value = ["/register"], method = [(RequestMethod.POST)])
    fun receiveData(@RequestParam(value = "userInfo", required = true) user: UserInfo,
                    httpServletRequest: HttpServletRequest): Any {//接收数据
        return registerService.backResult(user)
    }

    @ResponseBody
    @RequestMapping(value = ["/list"])
    fun getList(httpServletRequest: HttpServletRequest): Any {
        val currentTime = System.currentTimeMillis()
        val list = (0..15).mapTo(ArrayList<String>()) {
            "测试数据" + it
        }
        for (it in 0..15) {
            val userInfo = UserInfo("xiaoming" + it, "123456", it.toString(), currentTime + it.toLong())
            registerService.insertData(userInfo)
        }
//        var reslut = tokenService.verifyToken(httpServletRequest)
//        if (reslut == null) reslut = list
        return list
    }
}