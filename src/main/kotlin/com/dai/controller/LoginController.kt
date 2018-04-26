package com.dai.controller

import com.dai.service.LoginService
import com.dai.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by dai on 2018/1/29.
 */
@Controller
@RequestMapping("/mobile")
class LoginController @Autowired
constructor(private val loginService: LoginService, private val tokenService: TokenService) {

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
        if (token == null) {
            tokenService.verifyAccount(account)
            val cookie = Cookie("token", tokenService.findToken(account));
            httpServletResponse.addCookie(cookie)
        } else {
            val versify = tokenService.verifyToken(httpServletRequest)
            if (versify != null) result = versify
        }
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
}