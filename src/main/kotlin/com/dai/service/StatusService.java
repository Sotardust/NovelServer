package com.dai.service;

import com.dai.bean.OnlineStatus;
import com.dai.dao.StatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * 根据sessionId 获取对应人员信息
     *
     * @param request 请求
     * @return personId
     */
    public int getPersonId(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return getPersonId(session.getId());
    }

    @Override
    public int getPersonId(String sessionId) {
        return statusMapper.getPersonId(sessionId);

    }

    @Override
    public List<String> findSessionList() {
        return statusMapper.findSessionList();
    }

    @Override
    public void insertOnlineStatus(OnlineStatus status) {

        statusMapper.insertOnlineStatus(status);
    }

    @Override
    public void updateStatus(OnlineStatus status) {
        statusMapper.updateStatus(status);
    }


}
