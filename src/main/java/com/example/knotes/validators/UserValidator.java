package com.example.knotes.validators;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;

public class UserValidator {
    
    public void checkUsername(String username) throws ClientException{
        if(username == null){
            throw new ClientException("username is null");
        }

        if(username.length() < 3){
            throw new ClientException("username length atleast should be 3");
        }
    }

    public void checkPassword(String password) throws ClientException{
        if(password == null){
            throw new ClientException("password is null");
        }

        if(password.length() < 5){
            throw new ClientException("password length atleast should be 5");
        }
    }

    public void checkSuccessUpdatePassword(Integer dataCount) throws NotFoundExceptions{
        if(dataCount == 0){
            throw new NotFoundExceptions("Failed to update password");
        }
    }

    public void checkUserExist(Object obj) throws NotFoundExceptions{
        if(obj == null){
            throw new NotFoundExceptions("wrong username or password");
        }
    }

}
