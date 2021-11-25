package com.example.mybatis.mapper;

import com.example.mybatis.domain.Sex;
import com.example.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;
    @BeforeEach
    void setUp() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @AfterEach
    void tearDown() {
        sqlSession.close();
    }

    @Test
    void save() {
        User user = new User();
        user.setEmail("katty@google.com");
        user.setPassword("567890");
        user.setBirthday(LocalDate.now());
        user.setSex(Sex.F);
        user.setLoginCount(0);
        user.setLastLoginTime(null);
        userMapper.save(user);
        sqlSession.commit();
    }

    @Test
    void deleteById() {
        userMapper.deleteById(1);
        sqlSession.commit();
    }

    @Test
    void updateById() {
        User user = new User();
        user.setId(2);
        user.setEmail("jerry@google.com");
        user.setPassword("345678");
        user.setBirthday(LocalDate.now());
        user.setSex(Sex.F);
        user.setLoginCount(0);
        user.setLastLoginTime(null);
        userMapper.updateById(user);
        sqlSession.commit();
    }

    @Test
    void findOne() {
        System.out.println(userMapper.findOne(2));
    }

    @Test
    void findAll() {
        userMapper.findAll().forEach(System.out::println);
    }
}