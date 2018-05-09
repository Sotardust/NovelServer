package com.dai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.Serializable;

/**
 * Created by dai on 2018/5/9.
 */

public class Song implements Serializable {
    private static final int ID = 1324578930;

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    @Bean
    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    private FileInputStream fileInputStream;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
