package com.example.knotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;
import com.example.knotes.models.NotesModel;
import com.example.knotes.models.ResponseModel;
import com.example.knotes.services.NotesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {
    @Autowired
    NotesService notesService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postAddUser(@RequestBody NotesModel notesModel) {
        try {
            notesService.addNotes(notesModel.getUserId(), notesModel.getUserNotes());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success add new note");
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

    @PutMapping(value = "/updatenote")
    public ResponseEntity<ResponseModel> putUpdateNote(@RequestBody NotesModel notesModel){
        try {
            notesService.updateNotes(notesModel.getId(), notesModel.getUserNotes());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("success update note");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");
            return ResponseEntity.internalServerError().body(responseModel);
        }
    }

    @PostMapping(value = "/list")
    public ResponseEntity<ResponseModel> getListUserNotes(@RequestBody NotesModel notesModel) {
        try {
            List<NotesModel> notesModels = notesService.listNote(notesModel.getUserId());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success get user notes");
            responseModel.setStatus(HttpStatus.OK.value());
            responseModel.setObj(notesModels);

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");
            return ResponseEntity.internalServerError().body(responseModel);
        }

    }

    @PostMapping(value = "/search")
    public ResponseEntity<ResponseModel> getNotesById(@RequestBody NotesModel notesModel) {
        try {
            List<NotesModel> notesModels = notesService.notesBySearch(notesModel.getUserId(), notesModel.getUserNotes());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Success search");
            responseModel.setStatus(HttpStatus.OK.value());
            responseModel.setObj(notesModels);

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (NotFoundExceptions e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");
            return ResponseEntity.internalServerError().body(responseModel);
        }
    }

    @DeleteMapping(value = "/deletenote")
    public ResponseEntity<ResponseModel> deleteNote(@RequestBody NotesModel notesModel){
        try {
            notesService.deleteNotes(notesModel.getId());
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("success delete note");
            responseModel.setStatus(HttpStatus.OK.value());

            return ResponseEntity.ok(responseModel);

        } catch (ClientException e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMsg("Internal server failure");
            return ResponseEntity.internalServerError().body(responseModel);
        }
    }
    
}
