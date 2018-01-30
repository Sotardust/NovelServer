package com.dai.dao

import com.dai.bean.User
import org.apache.ibatis.annotations.Mapper

/**
 * Created by dai on 2018/1/26.
 */
@Mapper
interface RegisterMapper {
    abstract fun getIds(): Int
    abstract fun insertData(user: User)
    abstract fun findAllAccount(): List<String>
    abstract fun findPassword(account: String): String
}