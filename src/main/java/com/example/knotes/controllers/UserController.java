package com.example.knotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;
import com.example.knotes.models.ResponseModel;
import com.example.knotes.models.UserModel;
import com.example.knotes.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postAddUser(@RequestBody UserModel userModel) {
        try {
            userService.addUser(userModel.getUsername(), userModel.getPassword());

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success register new user");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");
            return ResponseEntity.internalServerError().body(responseModel);
        }
        
    }

    @PutMapping(value = "/updateusername")
    public ResponseEntity<ResponseModel> putUpdateUsername(@RequestBody UserModel userModel) {
        try {
            userService.updateUsername(userModel.getId(), userModel.getUsername());

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success update username");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");

            return ResponseEntity.internalServerError().body(responseModel);
        }
        
    }

    @PutMapping(value = "/updatepassword")
    public ResponseEntity<ResponseModel> putUpdatePassword(@RequestBody UserModel userModel) {
        try {
            userService.updatePassword(userModel.getUsername(), userModel.getPassword());
            
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success update password");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (NotFoundExceptions e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");

            return ResponseEntity.internalServerError().body(responseModel);
        }
        
    }

    @PostMapping(value = "/findbyid")
    public ResponseEntity<ResponseModel> getFindById(@RequestBody UserModel userModel) {
        try {
            UserModel response = userService.getUser(userModel.getUsername(), userModel.getPassword());

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success get user");
            responseModel.setStatus(HttpStatus.OK.value());
            responseModel.setObj(response);

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (NotFoundExceptions e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");

            return ResponseEntity.internalServerError().body(responseModel);
        }
        
    }

    @DeleteMapping(value = "/deleteuser")
    public ResponseEntity<ResponseModel> deleteUser(@RequestBody UserModel userModel) {
        try {
            userService.deleteUser(userModel.getId());

            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success delete user");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");

            return ResponseEntity.internalServerError().body(responseModel);
        }
        
    }

}
