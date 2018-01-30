package com.dai.dao

import com.dai.bean.Token
import org.apache.ibatis.annotations.Mapper

/**
 * Created by dai on 2018/1/30.
 */
@Mapper
interface TokenMapper {
    abstract fun findAllAccount(): List<String>
    abstract fun insertTokenData(token: Token): Boolean
    abstract fun findToken(account: String): String
    abstract fun findAccount(token: String): String
    abstract fun findStartTime(token: String): String
    abstract fun findTime(token: String): String
    abstract fun findLogout(token: String): String
    abstract fun updateToken(token: String): String
    abstract fun updateLogout(token: String): String
}