package com.dai.controller

import com.dai.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by dai on 2018/1/29.
 */
@Controller
@RequestMapping("/mobile")
class LoginController @Autowired
constructor(private val loginService: LoginService) {

    @ResponseBody
    @RequestMapping("/login", method = arrayOf(RequestMethod.POST))
    fun login(@RequestParam(value = "account", required = true) account: String,
              @RequestParam(value = "password", required = true) password: String): Any {
        return loginService.returnResult(account, password)

    }
}