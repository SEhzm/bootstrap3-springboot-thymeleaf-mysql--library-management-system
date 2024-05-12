package com.geo.homework2.repository;

import com.geo.homework2.pojo.MyUser;

import java.util.List;

public interface MyUserRepository {
    public int saveUser(MyUser myUser);

    public int deleteUser(Integer id);

    public int updateUser(MyUser myUser);

    public List<MyUser> findAll();

    public MyUser findUserById(Integer id);

    public MyUser findUserByNameandPawd(MyUser myUser);

    public int[] batchInsert(List<Object[]> my);

    public String getName(Integer id);

    public long counter();

    public List<MyUser> findUsersByCondition(MyUser myUser);

}