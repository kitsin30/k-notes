package com.example.knotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;
import com.example.knotes.models.UserModel;
import com.example.knotes.repos.UserRepository;
import com.example.knotes.validators.UserValidator;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private UserValidator userValidator = new UserValidator();

    public Integer addUser(String username, String password) throws ClientException{
        userValidator.checkUsername(username);
        userValidator.checkPassword(password);

        return userRepository.save(username, password);
    }

    public Integer updateUsername(Integer id, String username) throws ClientException{
        userValidator.checkUsername(username);

        return userRepository.updateUsername(id, username);
    }

    public Integer updatePassword(String username, String password) throws ClientException, NotFoundExceptions{
        userValidator.checkUsername(username);
        userValidator.checkPassword(password);

        Integer dataCount = userRepository.updatePassword(username, password);

        userValidator.checkSuccessUpdatePassword(dataCount);

        return dataCount;
    }

    public UserModel getUser(String username, String password) throws ClientException, NotFoundExceptions{
        userValidator.checkUsername(username);
        userValidator.checkPassword(password);

        UserModel userModel = userRepository.findById(username, password);
        userValidator.checkUserExist(userModel);

        return userModel;
    }

    public Integer deleteUser(Integer id) throws ClientException{
        return userRepository.deleteById(id);
    }
}
