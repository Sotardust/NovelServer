package com.dai.service;

import com.dai.bean.OnlineStatus;
import com.dai.dao.StatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements StatusMapper {

    private static final String TAG = "StatusService";

    private final Logger logger = Logger.getLogger(StatusService.class);

    private final StatusMapper statusMapper;

    @Autowired
    public StatusService(StatusMapper statusMapper) {
        this.statusMapper = statusMapper;
    }

    @Override
    public String findSessionId(String personId) {

        return statusMapper.findSessionId(personId);
    }

    @Override
    public void insertOnlineStatus(OnlineStatus status) {

        statusMapper.insertOnlineStatus(status);
    }

    @Override
    public void updateStatus(OnlineStatus status) {

    }


}
