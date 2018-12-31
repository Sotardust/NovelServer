package com.dai.controller

import com.dai.bean.Person
import com.dai.bean.UserInfo
import com.dai.service.login.RegisterService
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
constructor(private val registerService: RegisterService) {

    @ResponseBody
    @RequestMapping(value = ["/login"], method = [(RequestMethod.POST)])
    fun login(@RequestParam(value = "name", required = true) name: String,
              @RequestParam(value = "password", required = true) password: String,
              httpServletRequest: HttpServletRequest): Any {//接收数据
        return registerService.loginResult(name, password)

    }

    @ResponseBody
    @RequestMapping(value = ["/register"], method = [(RequestMethod.POST)])
    fun register(@RequestParam(value = "name", required = true) name: String,
                 @RequestParam(value = "password", required = true) password: String,
                 @RequestParam(value = "register_time", required = true) registerTime: String,
                 httpServletRequest: HttpServletRequest): Any {//接收数据

        val number = "00000000000"
        val sex = "男"
        val age = 22
        // 人员id 取 时间戳最后6位数做为人员ID
        val personId = registerTime.substring(registerTime.length - 8, registerTime.length).toLong()
        val person = Person(name, personId, password, number, sex, age, registerTime = registerTime.toLong())
        return registerService.registerResult(person)
    }

    @ResponseBody
    @RequestMapping(value = ["/list"])
    fun getList(httpServletRequest: HttpServletRequest): Any {
        val currentTime = System.currentTimeMillis()
        val list = (0..15).mapTo(ArrayList<String>()) {
            "测试数据" + it
        }
        return list
    }
}