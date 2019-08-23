package com.dai.utils

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest


/**
 * Created by dai on 2018/1/30.
 */
/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 *
 * @author XuJijun
 */
@Aspect
@Component
class ControllerInterceptor {

    @Before("execution(* com.dai.*Controller.*(..)) && !within(com.dai.controller.LoginController)")
    fun logAccess(joinPoint: JoinPoint) {
        println("Completed: $joinPoint")

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes)
                .request
        val remoteAddr = getIpAddress(request)
        val userAgent = request.getHeader("user-agent")
        val requestUri = request.requestURI
        val method = request.method

        val paramMap = request.parameterMap
        val params = StringBuffer()
        if (paramMap != null && !paramMap.isEmpty()) {
            for ((key, value) in paramMap) {
                if ("" == params.toString()) {
                    params.append("$key=")
                } else {
                    params.append("&$key=")
                }

                var paramValue = ""
                if (value != null && value.isNotEmpty()) {
                    paramValue = value[0]
                }

                // 屏蔽密码获取
                if ("password" != key) {
                    params.append(paramValue)
                }
            }
        }

        println("ip address:" + remoteAddr)
        println("useragent:" + userAgent)
        println("url:" + requestUri)
        println("request method:" + method)
        println("method params:" + params.toString())
        println("---------------------------------")
    }

    fun getIpAddress(request: HttpServletRequest): String {
        var ip = request.getHeader("X-Forwarded-For")
        if (ip != null && !"unKnown".equals(ip, ignoreCase = true)) {
            // 多次反向代理后会有多个ip值,第一个ip才是真实ip
            val index = ip.indexOf(",")
            return if (index != -1) {
                ip.substring(0, index)
            } else {
                ip
            }
        }
        ip = request.getHeader("X-Real-IP")
        return if (ip != null && !"unKnown".equals(ip, ignoreCase = true)) {
            ip
        } else request.remoteAddr
    }
}