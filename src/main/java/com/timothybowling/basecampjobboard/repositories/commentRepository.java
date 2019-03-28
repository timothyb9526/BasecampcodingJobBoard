package com.timothybowling.basecampjobboard.repositories;

import com.timothybowling.basecampjobboard.modal.Comment;
import com.timothybowling.basecampjobboard.modal.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public class commentRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public commentRepository(JdbcTemplate jdbtemplate) {
        jdbc = jdbtemplate;
    }

    public void save(Comment comment) {
        comment.id = UUID.randomUUID();

        jdbc.update("INSERT INTO comments (id, employer_id, name, status, date, comment) VALUES (?, ?, ?, ?, ?, ?)", comment.id, comment.employer_id, comment.name, comment.status,
                comment.date, comment.comment);
    }

    public void delete(UUID id) {
        jdbc.update("DELETE FROM comments WHERE employer_id= ?", id);
    }

    public void deleteComment(UUID id) {
        jdbc.update("DELETE FROM comments WHERE id= ?", id);
    }

    public List<Comment> findById(UUID id) {
        return jdbc.query("SELECT id, employer_id, name, status, date, comment FROM comments WHERE employer_id= ?",
                this::mapRowToStory, id);
    }

    public List<Comment> findAll() {
        return jdbc.query("SELECT id, employer_id, name, status, date, comment FROM comments ORDER BY name ASC", this::mapRowToStory);

    }

    private Comment mapRowToStory(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(UUID.fromString(rs.getString("id")), UUID.fromString(rs.getString("employer_id")), rs.getString("name"), rs.getString("status"), rs.getString("comment"));
    }

}
