package com.dai

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = arrayOf(DataSourceAutoConfiguration::class))
class NovelApplication

fun main(args: Array<String>) {
    println("hello kotlin")
    SpringApplication.run(NovelApplication::class.java, *args)
    println("运行成功")
}
