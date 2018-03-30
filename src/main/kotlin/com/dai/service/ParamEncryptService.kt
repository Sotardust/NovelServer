package com.dai.service

import jdk.nashorn.api.scripting.ScriptObjectMirror
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.script.Invocable
import javax.script.ScriptEngineManager


/**
 * Created by dai on 2018/3/30.
 */
@Service
class ParamEncryptService() {

    private var invocable: Invocable? = null
    private val encText = "encText"
    private val encSecKey = "encSecKey"

    init {
        try {
            val path = Paths.get("core.js")
            val bytes = Files.readAllBytes(path)
            val js = String(bytes)
            val factory = ScriptEngineManager()
            val engine = factory.getEngineByName("JavaScript")
            engine.eval(js)
            invocable = engine as Invocable
            println("Init completed")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 从本地加载修改后的 js 文件到 scriptEngine
     */

    fun getParams(paras: String): HashMap<String, String>? {
        return try {
            val so = invocable!!.invokeFunction("myFunc", paras) as ScriptObjectMirror
            val datas = HashMap<String, String>()
            datas["params"] = so[encText].toString()
            datas["encSecKey"] = so[encSecKey].toString()
            datas
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}