package com.timothybowling.basecampjobboard.modal;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Comment {

    public UUID id;
    public UUID employer_id;

    @NotNull
    public String name;

    @NotNull
    public String status;

    @NotNull
    public String comment;

    @NotNull
    public String date;



    public Comment(UUID id, UUID employerId, String name, String status, String comment) {

        String pattern = "dd-MM-yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());

        this.id = id;
        this.employer_id = employerId;
        this.name = name;
        this.status = status;
        this.comment = comment;
        this.date = dateInString;

    }
}
