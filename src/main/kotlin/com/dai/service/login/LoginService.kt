package com.dai.service.login

import com.dai.dao.RegisterMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by dai on 2018/1/29.
 */
@Service
class LoginService @Autowired
constructor(private val registerMapper: RegisterMapper) {

    // 返回登录结果
    fun returnResult(name: String, password: String): Any {
        val result = HashMap<String, String>()
        val flag = registerMapper.getAllAccount().any { name == it }
        if (!flag) {
            result["success"] = "0";
            result["error"] = "用户名错误";

            //TODO 未注册账号的一种情况
//            result["error"] = "该账户未注册，请重新注册后登录";
            return result
        }
        // 根据账号查找该用户密码
        val pwd = registerMapper.findPassword(name)
        if (pwd == password) {
            result["success"] = "1"
            result["error"] = ""
        } else {
            result["success"] = "0"
            result["error"] = "密码错误"
        }
        return result
    }

    // 获取所有账号
    fun getAllAccount(): Any {
        return registerMapper.getAllAccount();
    }
}