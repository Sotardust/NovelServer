package com.dai.utils.token

import javax.servlet.http.HttpServletRequest

/**
 * Created by dai on 2018/1/30.
 *
 * 暂时使用该方法验证token
 * 后续使用 单点验证sso
 */
open class TokenManager {

    // 自动生成token

    //验证是否是同一个用户
    fun verifyAccount(httpServletRequest: HttpServletRequest) {
        val cookies = httpServletRequest.cookies
        for (cookie in cookies) {
            println("cookie maxAge= ${cookie.maxAge}")
            println("cookie comment= ${cookie.comment}")
            println("cookie name= ${cookie.name}")
            println("cookie value= ${cookie.value}")
            println("cookie secure= ${cookie.secure}")
        }
    }


    //通过token 判断是那个用户


}