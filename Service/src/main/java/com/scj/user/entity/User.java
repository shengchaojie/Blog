package com.scj.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/10.
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;
    private String username;
    private String password;
    private String nickname;

    /*@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<Talk> talks;*/

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private UserInfo userInfo;

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

   /* public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }*/

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +

                '}';
    }
}
