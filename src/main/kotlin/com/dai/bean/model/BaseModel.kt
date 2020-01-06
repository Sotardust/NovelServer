package com.dai.bean.model


open class BaseModel<T> {
    /**
     * 103 : sessionId 会话超时
     * 102 : 服务器未设置返回结果（默认返回值）
     * 101 : 服务器程序异常
     * 100 : 成功
     * 99 : 失败
     */
    var code: Int = HttpStatusCode.CODE_102

    var msg: String? = null

    var result: T? = null

}
