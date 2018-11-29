package com.dai.dao

import com.dai.bean.User
import com.dai.bean.UserInfo
import org.apache.ibatis.annotations.Mapper

/**
 * Created by dai on 2018/1/26.
 */
@Mapper
interface RegisterMapper {
    fun getUserCount(): Int
    fun insertUserInfo(user: UserInfo)
    fun getAllAcounts(): List<String>
    fun findPassword(account: String): String
}