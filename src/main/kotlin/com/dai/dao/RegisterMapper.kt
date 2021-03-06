package com.dai.dao

import com.dai.bean.Person
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * Created by dai on 2018/1/26.
 */
@Mapper
interface RegisterMapper {
    fun getAllCount(): Int
    fun insertPerson(person: Person)
    fun getAllAccount(): List<Person>
    fun findPassword(account: String): String
    fun findPersonId(account: String): Int
}