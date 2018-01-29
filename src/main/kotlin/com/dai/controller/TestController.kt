package com.dai.controller

import com.dai.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by dai on 2018/1/29.
 */
@Controller
@RequestMapping("/mobile")
class TestController @Autowired
constructor(private val loginService: LoginService) {

    @ResponseBody
    @RequestMapping("/get_accounts", method = [(RequestMethod.GET)])
    fun getAllAccounts(): Any {
        return loginService.getAllAccount()
    }
}