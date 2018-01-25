package com.dai.dao;

import com.dai.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
@Mapper
public interface RegisterMapper {

    int getIds();

    void insertData(User user);

}
