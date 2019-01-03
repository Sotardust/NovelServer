package com.dai.bean;

public class OnlineStatus {

    //人员id
    public int personId;

    //缓存sessionId
    public String sessionId;

    // 0/在线，1/不在线
    public int status;

    //第一次登陆时间
    public long loginTime;

    //存活时间
    public long aliveTime;
}
