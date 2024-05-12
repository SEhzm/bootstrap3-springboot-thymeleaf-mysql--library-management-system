package com.geo.homework2.service.impl;

import com.geo.homework2.pojo.MyUser;
import com.geo.homework2.repository.MyUserRepository;
import com.geo.homework2.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public int saveUser(MyUser myUser) {
        return myUserRepository.saveUser(myUser);
    }

    @Override
    public int deleteUser(Integer id) {
        return myUserRepository.deleteUser(id);
    }

    @Override
    public int updateUser(MyUser myUser) {
        return myUserRepository.updateUser(myUser);
    }

    @Override
    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    @Override
    public MyUser findUserById(Integer id) {
        return myUserRepository.findUserById(id);
    }

    @Override
    public int[] batchInsert(List<Object[]> myArgs) {
        return myUserRepository.batchInsert(myArgs);
    }

    @Override
    public String getNamebyId(Integer id) {
       return myUserRepository.getName(id);
    }

    @Override
    public MyUser login(MyUser myUser) {
        return myUserRepository.findUserByNameandPawd(myUser);
    }

    @Override
    public long getTotal() {
        return myUserRepository.counter();
    }

    @Override
    public List<MyUser> findUsersByCondtion(MyUser myUser) {
        return myUserRepository.findUsersByCondition(myUser);
    }


}
