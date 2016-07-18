package com.scj.user.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/7/10.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private List<Talk> talks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", talks=" + talks +
                '}';
    }
}
