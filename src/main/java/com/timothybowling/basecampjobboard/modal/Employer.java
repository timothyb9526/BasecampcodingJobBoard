package com.timothybowling.basecampjobboard.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Employer {
    public UUID id;

    @NotNull
    @Size(min = 1, max= 20)
    public String name;
    @NotNull
    @Size(min = 1, max = 20)
    public String companyName;
    @NotNull
    public String logo;
    @NotNull
    public String city;
    @NotNull
    public String state;
    @NotNull
    public String country;
    @NotNull
    public  String position;
    @NotNull
    public String description;
    @NotNull
    public String date;
    @NotNull
    @Size(min = 5)
    public String benefits;
    @NotNull
    @Size(min = 5)
    public String responsibilities;
    @NotNull
    @Size(min = 5)
    public String qualifications;
    @NotNull
    public String preferredQualifications;

//    public void setAge(Integer age) {
//        if (age == null) {
//            Age = 18;
//        }
//    }




//    public void setDate() {
//
//
//
//        String pattern = "dd-MM-yyyy";
//        String dateInString = new SimpleDateFormat(pattern).format(new Date());
//
//        date = dateInString;
//
////        try {
////            String pattern = "yyyy-MM-dd";
////            DateFormat df = new SimpleDateFormat(pattern);
////            Date today = df.parse(dateString);
////
////            date = today;
////        } catch (ParseException ex) {
////            ex.printStackTrace();
////        }
//
//    }


    public Employer(UUID id, String name, String companyName, String logo, String city, String state, String country, String position, String description, String date, String benefits, String responsibilities, String qualifications, String preferredQualifications) {

        String pattern = "dd-MM-yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());

        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.logo = logo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.position = position;
        this.description = description;
        this.date = dateInString;
        this.benefits = benefits;
        this.responsibilities = responsibilities;
        this.qualifications = qualifications;
        this.preferredQualifications = preferredQualifications;

    }

    public String preview(String text) {
        if (text.length() < 100) {
            return text;
        } else {
            return text.substring(0,97) + "...";
        }
    }

    public String[] paragraphs(String text) {
        return text.split("\n");
    }
}
