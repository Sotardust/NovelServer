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
        val result = HashMap<String, String>()
        val bool = insertData(user);
        if (bool) {
            result.put("success", "0")
            result.put("error", "用户名错误")
        } else {
            result.put("success", "0")
            result.put("error", "密码错误")
        }
        return result
    }

    fun getIds(): Int {
        return registerMapper.ids
    }
}