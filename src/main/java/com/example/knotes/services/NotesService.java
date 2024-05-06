package com.example.knotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;
import com.example.knotes.models.NotesModel;
import com.example.knotes.repos.NotesRepository;
import com.example.knotes.validators.NotesValidator;

@Service
public class NotesService {
    @Autowired
    NotesRepository notesRepository;

    private NotesValidator notesValidator = new NotesValidator();

    public Integer addNotes(Integer userId, String notes) throws ClientException{
        notesValidator.checkNullUserId(userId);
        notesValidator.checkNullNote(notes);

        return notesRepository.save(userId, notes);
    }

    public Integer updateNotes(Integer id, String notes) throws ClientException{
        notesValidator.checkNullId(id);
        notesValidator.checkNullNote(notes);

        return notesRepository.updateNotes(id, notes);
    }

    public List<NotesModel> listNote(Integer userId) throws ClientException{
        notesValidator.checkNullUserId(userId);

        return notesRepository.findByCategoryUser(userId);
    }

    public List<NotesModel> notesBySearch(Integer userId, String search) throws ClientException, NotFoundExceptions{
        notesValidator.checkNullId(userId);

        return notesRepository.findNotesBySearch(userId, search);
    }

    public Integer deleteNotes(Integer id) throws ClientException{
        notesValidator.checkNullId(id);

        return notesRepository.deleteById(id);
    }

}
