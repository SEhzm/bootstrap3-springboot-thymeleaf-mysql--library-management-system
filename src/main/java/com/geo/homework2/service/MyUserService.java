package com.geo.homework2.service;

import com.geo.homework2.pojo.MyUser;

import java.util.List;

public interface MyUserService {
    public int saveUser(MyUser myUser);

    public int deleteUser(Integer id);

    public int updateUser(MyUser myUser);

    public List<MyUser> findAll();

    public MyUser findUserById(Integer id);

    public int[] batchInsert(List<Object[]> myArgs);

    public String getNamebyId(Integer id);

    public MyUser login(MyUser myUser);

    public long getTotal();

    public List<MyUser> findUsersByCondtion(MyUser myUser);
}
