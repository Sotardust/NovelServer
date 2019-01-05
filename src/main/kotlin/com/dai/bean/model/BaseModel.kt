package com.dai.bean.model


open class BaseModel<T> {
    /**
     * -3 : sessionId 会话超时
     * -2 : 服务器未设置返回结果（默认返回值）
     * -1 : 服务器程序异常
     * 0 : 返回成功
     * 1 : 返回失败
     */
    var code: Int = -2

    var msg: String? = null

    var result: T? = null

}
