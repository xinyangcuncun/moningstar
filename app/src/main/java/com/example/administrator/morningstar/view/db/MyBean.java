package com.example.administrator.morningstar.view.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by anson on 2017/6/7.
 */
@Entity
public class MyBean {
    @Id
    private Long id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "NICKNAME")
    private String nickname;

    private int state;
    private String filepath;
    private float progress;
    private int downid;
    private String url;

    public MyBean(Long id, String username, String nickname, int state,
            String filepath, float progress) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.state = state;
        this.filepath = filepath;
        this.progress = progress;
    }
    @Generated(hash = 1281580447)
    public MyBean() {
    }
    @Generated(hash = 2011086007)
    public MyBean(Long id, String username, String nickname, int state,
            String filepath, float progress, int downid, String url) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.state = state;
        this.filepath = filepath;
        this.progress = progress;
        this.downid = downid;
        this.url = url;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getFilepath() {
        return this.filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public float getProgress() {
        return this.progress;
    }
    public void setProgress(float progress) {
        this.progress = progress;
    }
    public int getDownid() {
        return this.downid;
    }
    public void setDownid(int downid) {
        this.downid = downid;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
