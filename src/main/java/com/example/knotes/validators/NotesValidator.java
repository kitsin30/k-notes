package com.example.knotes.validators;

import com.example.knotes.exceptions.ClientException;
import com.example.knotes.exceptions.NotFoundExceptions;

public class NotesValidator extends Exception{

    public void checkNullId(Integer id) throws ClientException{
        if(id == null){
            throw new ClientException("id cannot null");
        }
    }

    public void checkNullUserId(Integer userId) throws ClientException{
        if(userId == null){
            throw new ClientException("user id cannot null");
        }
    }

    public void checkNullNote(String userNotes) throws ClientException{
        if(userNotes == null){
            throw new ClientException("Notes cannot null");
        }
    }

    public void checkNullNoteObj(Object obj) throws NotFoundExceptions{
        if(obj == null){
            throw new NotFoundExceptions("Note is Not Exist");
        }
    }

}
