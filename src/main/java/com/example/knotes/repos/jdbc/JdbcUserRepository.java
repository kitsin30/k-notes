package com.example.knotes.repos.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.knotes.models.UserModel;
import com.example.knotes.repos.UserRepository;

@Repository
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(String username, String password) {
        String sql = "INSERT INTO users(username,password,created_date,updated_date) VALUES(?,?,NOW(),NOW())";
        return jdbcTemplate.update(sql, new Object[]{username, password});
    }

    @Override
    public int updateUsername(Integer id, String username) {
        String sql = "UPDATE users SET username=?, updated_date=NOW() WHERE id=?";
        return jdbcTemplate.update(sql, new Object[]{username, id});
    }

    @Override
    public int updatePassword(String username, String password) {
        String sql = "UPDATE users SET password=?, updated_date=NOW() WHERE username=?";
        return jdbcTemplate.update(sql, new Object[]{password, username});
    }

    @Override
    public UserModel findById(String username, String password) {
        String sql = "SELECT u.id, u.username, u.password FROM users u WHERE username=? AND password=?";
        try {
            UserModel userModel = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(UserModel.class), username, password);

            return userModel;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM users WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
    
}
