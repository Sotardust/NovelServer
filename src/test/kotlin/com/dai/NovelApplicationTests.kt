package com.dai

import com.dai.designpatterns.observerpattern.CurrentConditionsDisplay
import com.dai.designpatterns.observerpattern.StatisticsDisplay
import com.dai.designpatterns.observerpattern.ThirdPartyDisplay
import com.dai.designpatterns.observerpattern.WeatherData
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

    @Test
    fun testObserver() {
        val weatherData = WeatherData()
        val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
        val statisticsDisplay = StatisticsDisplay(weatherData)
        val thirdPartyDisplay = ThirdPartyDisplay(weatherData)

        weatherData.setMeasurements(80f,65f,30.4f)
        weatherData.setMeasurements(82f,70f,32.4f)
        weatherData.setMeasurements(88f,79f,36.5f)
    }
}
