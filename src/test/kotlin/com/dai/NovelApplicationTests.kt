package com.dai

import com.dai.designpatterns.strategypattern.FlyRocketPowered
import com.dai.designpatterns.strategypattern.ModelDuck
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class NovelApplicationTests {

    @Test
    fun contextLoads() {
//        val registerMapper: RegisterMapper? = null
//        val loginService: LoginService = LoginService(registerMapper!!)
//
//        println("login = ${loginService.returnResult("xiao", "1")}")
    }

    @Test
    fun main() {
        val model = ModelDuck()

        model.performFly()
        model.setFlyBehavior(FlyRocketPowered())
        model.performFly()
    }
}
