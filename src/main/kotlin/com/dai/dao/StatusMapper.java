package com.dai.dao;

import com.dai.bean.OnlineStatus;

public interface StatusMapper {

    String findSessionId(String personId);

    void insertOnlineStatus(OnlineStatus status);

    void updateStatus(OnlineStatus status);
}
