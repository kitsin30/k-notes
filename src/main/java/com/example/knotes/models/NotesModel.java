package com.example.knotes.models;

public class NotesModel {
    private Integer id;
    private String userNotes;
    private Integer userId;

    public NotesModel() {

    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserNotes() {
        return userNotes;
    }
    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
