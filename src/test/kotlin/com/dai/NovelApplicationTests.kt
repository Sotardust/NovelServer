package com.dai

import com.dai.designpatterns.decoratorpattern.Beverage
import com.dai.designpatterns.decoratorpattern.DarkRoast
import com.dai.designpatterns.decoratorpattern.Espresso
import com.dai.designpatterns.decoratorpattern.Mocha
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
    fun testStrategy() {
        //策略模式 测试用例
        val model = ModelDuck()

        model.performFly()
        model.setFlyBehavior(FlyRocketPowered())
        model.performFly()
    }

    @Test
    fun testObserver() {
        //观察者模式 测试用例
        val weatherData = WeatherData()
        val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
        val statisticsDisplay = StatisticsDisplay(weatherData)
        val thirdPartyDisplay = ThirdPartyDisplay(weatherData)

        weatherData.setMeasurements(80f, 65f, 30.4f)
        weatherData.setMeasurements(82f, 70f, 32.4f)
        weatherData.setMeasurements(88f, 79f, 36.5f)

    }

    @Test
    fun testDecorator() {
        //装饰者测试用例
        val beverage = Espresso()
        println("beverage.getDescription()  = " + beverage.description + "$ , beverage.cost()" + beverage.cost())

        var beverage1: Beverage = DarkRoast()
        beverage1 = Mocha(beverage1)
        beverage1 = Mocha(beverage1)
        beverage1 = Mocha(beverage1)
        println("beverage1.getDescription()  = " + beverage1.getDescription() + "$ , beverage1.cost()" + beverage1.cost())
    }
}
