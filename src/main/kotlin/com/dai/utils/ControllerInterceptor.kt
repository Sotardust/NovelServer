package com.dai.utils

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by dai on 2018/1/30.
 */
/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 * @author XuJijun
 */
@Aspect
@Component
class ControllerInterceptor {

    @Value("\${spring.profiles}")
    private val env: String? = null

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.xjj.web.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    fun controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    fun Interceptor(pjp: ProceedingJoinPoint): Any? {
        val beginTime = System.currentTimeMillis()
        val signature = pjp.signature as MethodSignature
        val method = signature.getMethod() //获取被拦截的方法
        val methodName = method.getName() //获取被拦截的方法名

        val allParams = LinkedHashSet<Any>() //保存所有请求参数，用于输出到日志中


        println("\"请求开始，方法：{}\", methodName = ${methodName}")

        var result: Any? = null

        val args = pjp.args
        for (arg in args) {
            //logger.debug("arg: {}", arg);
            if (arg is Map<*, *>) {
                //提取方法中的MAP参数，用于记录进日志中
                val map = arg as Map<String, Any>

                allParams.add(map)
            } else if (arg is HttpServletRequest) {
                val request = arg as HttpServletRequest
                if (isLoginRequired(method)) {
                    if (!isLogin(request)) {
                        result = "该操作需要登录！去登录吗？"
                    }
                }

                //获取query string 或 posted form data参数
                val paramMap = request.getParameterMap()
                if (paramMap != null && paramMap!!.size > 0) {
                    allParams.add(paramMap)
                }
            } else if (arg is HttpServletResponse) {
                println("arg = ${arg}")
                //do nothing...
            } else {
                //allParams.add(arg);
            }
        }

        try {
            if (result == null) {
                // 一切正常的情况下，继续执行被拦截的方法
                result = pjp.proceed()
            }
        } catch (e: Throwable) {
            println("e.toString() = ${e.toString()}")
//            result = JsonResult(ResultCode.EXCEPTION, "发生异常：" + e.message)
        }

//        if (result is JsonResult) {
//            val costMs = System.currentTimeMillis() - beginTime
//            println("{}请求结束，耗时：{}ms = ${ methodName ,costMs}")
//        }

        return result
    }

    /**
     * 判断一个方法是否需要登录
     * @param method
     * @return
     */
    private fun isLoginRequired(method: Method): Boolean {
        if (env != "prod") { //只有生产环境才需要登录
            return false
        }

        var result = true
//        if (method.isAnnotationPresent(Permission::class.java)) {
//            result = method.getAnnotation(Permission::class.java).loginReqired()
//        }

        return result
    }

    //判断是否已经登录
    private fun isLogin(request: HttpServletRequest): Boolean {
        return true
        /*String token = XWebUtils.getCookieByName(request, WebConstants.CookieName.AdminToken);
		if("1".equals(redisOperator.get(RedisConstants.Prefix.ADMIN_TOKEN+token))){
			return true;
		}else {
			return false;
		}*/
    }

    companion object {
//        private val logger = LoggerFactory.getLogger(ControllerInterceptor::class.java)
    }
}