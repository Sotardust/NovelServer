package com.dai

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NovelApplication

fun main(args: Array<String>) {
    println("hello kotlin")
    SpringApplication.run(NovelApplication::class.java, *args)
}
