package com.dai.bean

/**
 *人员表信息
 */

data class Person(
        val name: String,//用户名
        val personId: Long, //人员对应唯一ID
        val password: String,//密码
        val number: String, // 手机号
        val sex: String, // 性别
        val age: Int, // 年龄
        val registerTime: Long //注册时间
)
