package com.dai.dao;

import com.dai.bean.OnlineStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StatusMapper {


    String findSessionId(String personId);

    int getPersonId(String sessionId);

    List<String> findSessionList();

    void insertOnlineStatus(OnlineStatus status);

    /**
     * 更新用户在线状态
     *
     * @param status 0：在线
     */
    void updateStatus(OnlineStatus status);
//    void updateStatus(@Param("personId") String personId, @Param("sessionId") String sessionId, @Param("status") int status);
}
