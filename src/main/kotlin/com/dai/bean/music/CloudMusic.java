package com.dai.bean.music;

public class CloudMusic {

//    public int id ;
    public int personId;//人员ID
    public long musicId; //音乐对应唯一ID
    public String name; //音乐名称

    public CloudMusic(int personId, long musicId, String name, String path, String type, int duration) {

        this.personId = personId;
        this.musicId = musicId;
        this.name = name;
        this.path = path;
        this.type = type;
        this.duration = duration;
    }

    public String path; //存放路径
    public String type;//音乐类型
    public int duration; //音乐时长

}
