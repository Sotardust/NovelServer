package com.dai.service

import com.dai.bean.User
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
    fun insertData(user: User): Boolean {
        try {
            registerMapper.insertData(user)
        } catch (e: Exception) {
            println("e.cause.toString() = ${e.cause.toString()}")
            return false
        }
        return true
    }

    // 返回注册结果
    fun backResult(user: User): Any {
        println("registerMapper.findAllAccount() = ${registerMapper.findAllAccount()}")
        // 查找所有账号
        val flag = registerMapper.findAllAccount().any { user.account == it }

        val result = HashMap<String, String>()
        if (flag) {
            result["success"] = "0"
            result["error"] = "该账号已被注册"
            return result
        }
        val bool = insertData(user);
        if (bool) {
            result["success"] = "1"
            result["error"] = ""
        } else {
            result["success"] = "0"
            result["error"] = "注册失败"
        }
        return result
    }

    fun getIds(): Int {
        return registerMapper.getIds()
    }
}
