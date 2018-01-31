package com.dai.service

import com.dai.bean.Token
import com.dai.dao.TokenMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap


/**
 * Created by dai on 2018/1/30.
 *
 * 暂时使用该方法验证token
 * 后续使用 单点验证sso
 */
@Service
class TokenService @Autowired
constructor(val tokenMapper: TokenMapper) {

    val date = Date();

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    // 自动生成token
    fun verifyToken(httpServletRequest: HttpServletRequest): Any? {

        println("httpServletRequest.requestedSessionId = ${httpServletRequest.requestedSessionId}")
        println("httpServletRequest.requestedSessionId = ${httpServletRequest.cookies}")
        val token = httpServletRequest.cookies[0].value
        println("token = ${token}")
        val result = HashMap<String, String>()
        result["success"] = "0"
        result["error"] = "请登录后获取数据"
        try {
            val flag = tokenMapper.findAllToken().any { token == it }
            return if (flag && "否" == tokenMapper.findLogout(token)) {
                val tableDate = tokenMapper.findStartTime(token)
                val dt1 = simpleDateFormat.parse(tableDate)
                val dt2 = simpleDateFormat.parse(simpleDateFormat.format(date))
                val timeDif = Math.abs((dt2.time - dt1.time).toLong()).toInt()
                println("Integer.getInteger(tokenMapper.findTime(token)) = ${tokenMapper.findTime(token)}")
                val constantTime = tokenMapper.findTime(token).toInt() * 365 * 20 * 3600 * 1000
                if (timeDif > constantTime) {
                    val account = tokenMapper.findAccount(token)
                    tokenMapper.updateToken(generateToken(), "否", account)
                    result
                } else {
                    null
                }
            } else result
        } catch (e: Exception) {
            e.printStackTrace()
            return result
        }
    }

    //插入token数据
    fun insertTokenData(account: String) {
        println("simpleDateFormat.format(date) = ${simpleDateFormat.format(date)}")
        val token = Token(account, generateToken(), simpleDateFormat.format(date), "1", "否")

        try {
            tokenMapper.insertTokenData(token)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //验证是否是同一个用户
    fun verifyAccount(account: String) {
        // 查找所有账号
        println("account = $account")
        val flag = tokenMapper.findAllAccount().any { account == it }
        if (flag) {
            val logout = "否"
            tokenMapper.updateLogout(logout, account)
        } else {
            insertTokenData(account)
        }
    }

    //通过账号查找token
    fun findToken(account: String): String {
        return tokenMapper.findToken(account)
    }

    //通过token 判断是那个用户


    /**
     * 把格式化后的进行MD5加密生成的字符作为token
     *
     * @return
     */
    fun generateToken(): String {
        try {
            val md5 = MessageDigest.getInstance("MD5")
            md5.update(simpleDateFormat.format(date).toByteArray(charset("UTF-8")))
            val encryption = md5.digest()
            val strBuf = StringBuffer()
            for (i in encryption.indices) {
                if (Integer.toHexString(0xff and encryption[i].toInt()).length == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff and encryption[i].toInt()))
                } else {
                    strBuf.append(Integer.toHexString(0xff and encryption[i].toInt()))
                }
            }
            return strBuf.toString().substring(0, 15)
        } catch (e: NoSuchAlgorithmException) {
            return ""
        } catch (e: UnsupportedEncodingException) {
            return ""
        }

    }
}