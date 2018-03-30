package com.dai.controller

import com.dai.service.ParamEncryptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by dai on 2018/3/30.
 */
@Controller
@RequestMapping("/mobile")
class ParamEncryptController @Autowired
constructor(private val paramEncryptService: ParamEncryptService) {
    @ResponseBody
    @RequestMapping("/params", method = [RequestMethod.POST])
    fun getResult(@RequestParam(value = "param", required = true) param: String): Any? {
        return paramEncryptService.getParams(param);
    }


}