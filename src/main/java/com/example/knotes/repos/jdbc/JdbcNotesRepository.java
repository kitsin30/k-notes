package com.example.knotes.repos.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.knotes.models.NotesModel;
import com.example.knotes.repos.NotesRepository;

@Repository
public class JdbcNotesRepository implements NotesRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(int userId, String notes) {
        String sql = "INSERT INTO notes(user_id, user_notes, created_date, updated_date) VALUES(?,?,NOW(),NOW())";
        return jdbcTemplate.update(sql, new Object[]{userId, notes});
    }

    @Override
    public int updateNotes(Integer id, String notes) {
        String sql = "UPDATE notes SET user_notes=?, updated_date=NOW() WHERE id=?";
        return jdbcTemplate.update(sql, new Object[]{notes,id});
    }

    @Override
    public List<NotesModel> findByCategoryUser(Integer userId) {
        String sql = "SELECT n.id, n.user_notes, n.user_id FROM notes n WHERE n.user_id=? ORDER BY n.updated_date DESC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class), userId);
    }

    @Override
    public List<NotesModel> findNotesBySearch(Integer userId, String search) {
        search = "%" + search + "%";
        String sql = "SELECT n.id, n.user_notes, n.user_id FROM notes n WHERE n.user_id=? AND n.user_notes LIKE ? ORDER BY n.updated_date DESC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class), userId, search);
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM notes WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }


}
