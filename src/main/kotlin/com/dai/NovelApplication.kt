package com.dai

import com.dai.utils.file.FileUtil
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.web.servlet.ServletComponentScan
import java.io.File

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration(exclude = [(DataSourceAutoConfiguration::class)])
class NovelApplication

fun main(args: Array<String>) {

    SpringApplication.run(NovelApplication::class.java, *args)
    FileUtil.createLogFile();
    println("run successful")
}

fun getFileList(path: String) {
    val file = File(path)
    try {
        when {
            file.isDirectory -> {
                val fs = file.listFiles()
                (0..fs.size - 3)
                        .map { fs[it].absolutePath }
                        .forEach {
                            getFileList(it)
                            println("fs = ${it}")
                        }
            }
            file.isFile -> {
                val fName = file.absoluteFile;
                println("fName.name = ${fName.name}")
                println("fName.totalSpace = ${fName.totalSpace}")
                println("fName.length = ${fName.length()}")
            }
            else -> System.out.println("路径不正确!")
        }
    } catch (e: Exception) {
        println("e.message = $e")
    }
}
