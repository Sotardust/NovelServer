package com.dai.dao

import com.dai.bean.User
import com.dai.bean.UserInfo
import org.apache.ibatis.annotations.Mapper

/**
 * Created by dai on 2018/1/26.
 */
@Mapper
interface RegisterMapper {
    abstract fun getAllCount(): Int
    abstract fun insertUserInfo(user: UserInfo)
    abstract fun getAllAccount(): List<String>
    abstract fun findPassword(account: String): String
}