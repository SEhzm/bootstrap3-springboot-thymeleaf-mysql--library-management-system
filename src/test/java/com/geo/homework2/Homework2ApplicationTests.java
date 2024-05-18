package com.geo.homework2;

import com.geo.homework2.pojo.MyUser;
import com.geo.homework2.service.MyUserService;
import com.geo.homework2.service.impl.MyUserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.*;


@SpringBootTest
class Homework2ApplicationTests {
    @Autowired
    ApplicationContext ac;

    @Autowired
    MyUserService myUserService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveUser(){
        MyUserService myUserService = ac.getBean(MyUserServiceImpl.class);
        MyUser myuser = new MyUser();
        myuser.setUsername("test3");
        myuser.setPassword("123456");
        int res = myUserService.saveUser(myuser);
        if (res > 0)
            System.out.println("保存 成功！");
    }

//    @Test
//    public void testDeleteUser(){
//        int res = myUserService.deleteUser(7);
//        if (res > 0)
//            System.out.println("删除成功");
//    }

    @Test
    public void testUpdateUser(){
        MyUser myUser = new MyUser();
        myUser.setId(3);
        myUser.setUsername("updateusername2");
        myUser.setPassword("mima");
        int res = myUserService.updateUser(myUser);
        if(res > 0)
            System.out.println("更新成功");
    }

    @Test
    public void testfindAll(){
        List<MyUser> myUsers = myUserService.findAll();
        for (MyUser myUser:myUsers) {
            System.out.println(myUser);
        }
    }

    @Test
    public void testfindById(){
        MyUser myUser = myUserService.findUserById(1);
        System.out.println(myUser);
    }

    @Test
    public void testBatch() {
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"A", "28"});
        batchArgs.add(new Object[]{"B", "20"});
        batchArgs.add(new Object[]{"C", "30"});

        int[] res = myUserService.batchInsert(batchArgs);
        for (int r:res) {
            System.out.println(r);
        }
    }

    @Test
    public void testUserName(){
        String name = myUserService.getNamebyId(3);
        System.out.println(name);
    }

    @Test
    public void testCounter(){
        long l = myUserService.getTotal();
        System.out.println(l);
    }

    @Test
    public void genJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");

        String jwt = Jwts.builder()
                .setClaims(claims) //自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "itheima") //签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 24*3600*1000)) //有效期
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzEzMzQzMjYxLCJ1c2VybmFtZSI6IlRvbSJ9.ZQq4CTi3tFLharlDSoJWXipyTZEYojXYwjksHd0UVcw")
                .getBody();

        System.out.println(claims);
    }
}
