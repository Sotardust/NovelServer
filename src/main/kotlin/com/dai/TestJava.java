package com.dai;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dai on 2018/1/26.
 */
public class TestJava {

    private void test() {
        for (int i = 0; i < 10; i++) {
            System.out.println("测试demo = " + i);
        }

        String[] strings = new String[20];
        for (int i = 0; i < 10; i++) {
            strings[i] = "数据" + i;
        }
    }

    boolean flag = true ;
    String value = flag?"de":"fafdas";
    ArrayList<File> files = new ArrayList<>();
}
