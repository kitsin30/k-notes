package com.example.knotes.repos;

import com.example.knotes.models.UserModel;

public interface UserRepository {
    int save(String username, String password);

    int updateUsername(Integer id, String username);

    int updatePassword(String username, String password);

    UserModel findById(String username, String password);

    int deleteById(Integer id);

}
