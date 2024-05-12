package com.geo.homework2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MyUser {
    private String id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
