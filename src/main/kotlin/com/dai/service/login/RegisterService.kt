package com.dai.service.login

import com.alibaba.fastjson.JSON
import com.dai.bean.Person
import com.dai.bean.model.HttpStatusCode
import com.dai.bean.model.LoginModel
import com.dai.dao.RegisterMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

/**
 * Created by dai on 2018/1/25.
 */
@Suppress("DEPRECATION")
@Service
class RegisterService @Autowired
constructor(val registerMapper: RegisterMapper) {


    //用户数据是否插入成功
    fun insertData(person: Person): Boolean {
        try {
            registerMapper.insertPerson(person)
        } catch (e: SQLException) {
            println("e.cause.toString() = ${e.cause.toString()}")
            return false
        }
        return true
    }

    /**
     * 返回注册结果
     *
     * @param person Person实体类
     */
    fun registerResult(person: Person): String {

        // 查找所有账号
        val flag = registerMapper.getAllAccount().any { person.name == it }
        val loginModel = LoginModel()
        if (flag) {
            loginModel.code = HttpStatusCode.CODE_100
            loginModel.msg = "该账号已被注册"
            return JSON.toJSONString(loginModel)
        }
        val bool = insertData(person);
        loginModel.code = if (bool) HttpStatusCode.CODE_100 else HttpStatusCode.CODE_99
        loginModel.msg = if (bool) "注册成功" else "注册失败"
        return JSON.toJSONString(loginModel)
    }

    /**
     * 查找人员对应id
     */
    fun findPersonId(name: String): Int {
        try {
            return registerMapper.findPersonId(name)
        } catch (e: SQLException) {

        }
        return 0
    }

    /**
     *
     * 返回登录结果
     *
     * @param name  姓名
     * @param password 密码
     */
    fun loginResult(name: String, password: String): String {

        val loginModel = LoginModel()
        try {

            val pd = registerMapper.findPassword(name)
            if (pd == null) {
                loginModel.code = HttpStatusCode.CODE_99
                loginModel.msg = "该账号未注册";
                return JSON.toJSONString(loginModel)
            }

            val flag = password.equals(pd)
            loginModel.code = if (flag) HttpStatusCode.CODE_100 else HttpStatusCode.CODE_99
            loginModel.msg = if (flag) "登录成功" else "密码错误"
        } catch (e: SQLException) {
            e.printStackTrace()
            loginModel.code = HttpStatusCode.CODE_101
            loginModel.msg = "服务器数据异常"
        }

        return JSON.toJSONString(loginModel)
    }

    /**
     * 获取所有用户个数
     */
    fun getAllCount(): Int {
        return registerMapper.getAllCount()
    }
}
