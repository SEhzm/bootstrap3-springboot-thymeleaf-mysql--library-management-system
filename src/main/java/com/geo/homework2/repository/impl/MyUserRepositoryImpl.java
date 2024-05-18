package com.geo.homework2.repository.impl;

import com.geo.homework2.pojo.MyUser;
import com.geo.homework2.repository.MyUserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class MyUserRepositoryImpl implements MyUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveUser(MyUser myUser) {
        String sql = "insert into user (id,username, password) values (?,?,?)";
        Object args[] = {
                myUser.getId(),
                myUser.getUsername(),
                myUser.getPassword()
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteUser(Integer id) {
        String sql = "delete from book where id = ? ";
        Object args[] = {
                id
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateUser(MyUser myUser) {
        String sql = "update user set username = ?, password = ? where id = ? ";
        Object args[] = {
                myUser.getUsername(),
                myUser.getPassword(),
                myUser.getId()
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public List<MyUser> findAll() {
        String sql = "select * from user ";
        //定义一个RowMapper
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<MyUser>(MyUser.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public MyUser findUserById(Integer id) {
        String sql = "select * from user where id = ? ";
        Object args[] = {
                id
        };
        //定义一个RowMapper
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<MyUser>(MyUser.class);
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }

    @Override
    public MyUser findUserByNameandPawd(MyUser myUser) {
        String sql = "select * from user where id=? and password=?";
        Object args[] = {
                myUser.getId(),
                myUser.getPassword()
        };
        //定义一个RowMapper
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<MyUser>(MyUser.class);
        return jdbcTemplate.queryForObject(sql, args, rowMapper);

    }

    @Override
    public int[] batchInsert(List<Object[]> my) {
        String sql = "insert into user (username, password) values (?,?)";
        return jdbcTemplate.batchUpdate(sql, my);
    }

    @Override
    public String getName(Integer id) {
        String sql = "select username from user where id = ? ";
        Object args[] = {
                id
        };
        return jdbcTemplate.queryForObject(sql, String.class, args);
    }

    @Override
    public long counter() {
        String sql = "SELECT count(id) FROM user";
        long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count;
    }

    @Override
    public List<MyUser> findUsersByCondition(MyUser myUser) {
        StringBuilder userSqlBuilder = new StringBuilder("select * from user where 1=1");
        List<Object> args1 = new ArrayList<>();

        if(StringUtils.isNotBlank(myUser.getId())){
            userSqlBuilder.append(" and id = ?");
            args1.add(myUser.getId());
        }
        if(StringUtils.isNotBlank(myUser.getUsername())){ // 使用StringUtils来检查空字符串
            userSqlBuilder.append(" and username = ?");
            args1.add(myUser.getUsername());
        }
        String sql = userSqlBuilder.toString();
        //定义一个RowMapper
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<MyUser>(MyUser.class);
        return jdbcTemplate.query(sql, rowMapper, args1.toArray());
    }
}
