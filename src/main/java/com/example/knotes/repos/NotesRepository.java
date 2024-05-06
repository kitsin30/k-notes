package com.example.knotes.repos;

import java.util.List;

import com.example.knotes.models.NotesModel;

public interface NotesRepository {
    int save(int userId, String notes);

    int updateNotes(Integer id, String notes);

    List<NotesModel> findByCategoryUser(Integer userId);

    List<NotesModel> findNotesBySearch(Integer userId, String search);

    int deleteById(Integer id);
}
