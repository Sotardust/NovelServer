package com.dai.controller

import com.dai.bean.User
import com.dai.service.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

/**
 * Created by dai on 2018/1/25.
 */
@Controller
@RequestMapping("/mobile")
class RegisterController
@Autowired
constructor(private val registerService: RegisterService) {

    @ResponseBody
    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.POST))
    fun receiveData(@RequestParam(value = "account", required = true) account: String,
                    @RequestParam(value = "password", required = true) password: String): Any {//接收数据
        val user = User(account, password, "", 0, "", "", "", "", "");
        return registerService.backResult(user)
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    fun getList(): Any {
        val list = (0..15).mapTo(ArrayList<String>()) { "测试数据" + it }
        return list
    }
}