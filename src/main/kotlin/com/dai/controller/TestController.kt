package com.dai.controller

import com.dai.service.LoginService
import com.dai.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by dai on 2018/1/29.
 */
@Controller
@RequestMapping("/mobile")
class TestController @Autowired
constructor(private val loginService: LoginService, private val tokenService: TokenService) {

    @ResponseBody
    @RequestMapping("/get_accounts", method = [(RequestMethod.GET)])
    fun getAllAccounts(httpServletRequest: HttpServletRequest): Any {
        var reslut = tokenService.verifyToken(httpServletRequest)
        if (reslut == null) reslut = loginService.getAllAccount()
        return reslut
    }
}