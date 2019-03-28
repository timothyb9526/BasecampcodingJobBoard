package com.timothybowling.basecampjobboard.repositories;


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
public class PostgresRepository {
        private JdbcTemplate jdbc;

        @Autowired
        public PostgresRepository(JdbcTemplate jdbtemplate) {
            jdbc = jdbtemplate;
        }

        public void save(Employer employer) {
            employer.id = UUID.randomUUID();

            jdbc.update("INSERT INTO employers (id, name, companyName, logo, city, state, country, position, description, benefits, date, responsibilities, qualifications, preferredQualifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", employer.id, employer.name,
                    employer.companyName, employer.logo, employer.city, employer.state, employer.country, employer.position, employer.description, employer.benefits, employer.date, employer.responsibilities, employer.qualifications, employer.preferredQualifications);
        }

        public void delete(UUID id) {
            jdbc.update("DELETE FROM employers WHERE id= ?", id);
        }

        public Optional<Employer> findById(UUID id) {
            return Optional.ofNullable(jdbc.queryForObject("SELECT id, name, companyName, logo, city, state, country, position, description, benefits, date, responsibilities, qualifications, preferredQualifications FROM employers WHERE id= ?",
                    this::mapRowToStory, id));
        }

        public List<Employer> sortByLocation() {
            return jdbc.query("SELECT id, name, companyName, logo, city, state, country, position, description, benefits, date, responsibilities, qualifications, preferredQualifications FROM employers ORDER BY name ASC", this::mapRowToStory);
        }

        public List<Employer> sortByPosition() {
            return jdbc.query("SELECT id, name, companyName, logo, city, state, country, position, description, benefits, date, responsibilities, qualifications, preferredQualifications FROM employers ORDER BY position ASC", this::mapRowToStory);
        }

        public List<Employer> findAll() {
            return jdbc.query("SELECT id, name, companyName, logo, city, state, country, position, description, benefits, date, responsibilities, qualifications, preferredQualifications FROM employers ORDER BY name ASC", this::mapRowToStory);
        }

        private Employer mapRowToStory(ResultSet rs, int rowNum) throws SQLException {
            return new Employer(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("companyName"), rs.getString("logo"),
                    rs.getString("city"), rs.getString("state"), rs.getString("country"), rs.getString("position"), rs.getString("description"), rs.getString("date"), rs.getString("benefits"), rs.getString("responsibilities"), rs.getString("qualifications"), rs.getString("preferredQualifications"));
        }
    }
