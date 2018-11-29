package com.dai.service.login

import com.dai.bean.UserInfo
import com.dai.dao.RegisterMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by dai on 2018/1/25.
 */
@Service
class RegisterService @Autowired
constructor(private val registerMapper: RegisterMapper) {

    //用户数据是否插入成功
    fun insertUserInfo(user: UserInfo): Boolean {
        try {
            registerMapper.insertUserInfo(user)
        } catch (e: Exception) {
            println("e.cause.toString() = ${e.cause.toString()}")
            return false
        }
        return true
    }

    /**
     * 返回注册结果
     */
    fun registerResult(user: UserInfo): Any {
        // 查找所有账号
        val isContain = registerMapper.getAllAcounts().any { user.account == it }
        val result = HashMap<String, String>()
        if (isContain) {
            result["success"] = "0"
            result["error"] = "该账号已被注册"
            return result
        }
        val bool = insertUserInfo(user);
        result["success"] = if (bool) "1" else "0"
        result["error"] = if (bool) "" else "注册失败"
        return result

    }

    /**
     * 返回数据库中用户个数
     */
    fun getUserCount(): Int {
        return registerMapper.getUserCount()
    }
}
