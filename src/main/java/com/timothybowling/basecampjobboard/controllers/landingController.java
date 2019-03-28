package com.timothybowling.basecampjobboard.controllers;

import com.timothybowling.basecampjobboard.modal.Comment;
import com.timothybowling.basecampjobboard.modal.Employer;
import com.timothybowling.basecampjobboard.repositories.PostgresRepository;
import com.timothybowling.basecampjobboard.repositories.commentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")

public class landingController {

    PostgresRepository repository;
    commentRepository comments;

    @Autowired
    public landingController(PostgresRepository repo, commentRepository comment){
        comments = comment;
        repository = repo;

    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("employers", repository.findAll());


        return "home";
    }

    @GetMapping("/2")
    public String userSortPosition(Model model) {
        model.addAttribute("employers", repository.sortByPosition());
        return "userP";
    }
    @GetMapping("/3")
    public String userSortLocation(Model model) {
        model.addAttribute("employers", repository.sortByLocation());
        return "userL";
    }


    @PostMapping("/")
    public String next(Employer employer, Model model) {
        repository.save(employer);
        model.addAttribute("employers", repository.findAll());
        return "home";
    }

    @GetMapping("/jobPost")
    public String jobPost() {
        return "addJob";
    }

    @GetMapping("/{uuid}")
    public String employer(Model model, @PathVariable(value = "uuid") UUID uuid) {
        Optional<Employer> student = repository.findById(uuid);



        if (student.isPresent()) {
            model.addAttribute("employer", student.get());
            return "employer";
        } else {
            return "admin";
        }

    }

    @GetMapping("/{uuid}/apply")
    public String application(Model model, @PathVariable(value = "uuid") UUID uuid) {
        Optional<Employer> student = repository.findById(uuid);



        if (student.isPresent()) {
            model.addAttribute("employer", student.get());
            return "jobApplication";
        } else {
            return "admin";
        }

    }

    @GetMapping("/{uuid}/delete")
    public String delete(Model model, @PathVariable(value = "uuid") UUID uuid) {
            comments.delete(uuid);
            repository.delete(uuid);

            model.addAttribute("employers", repository.findAll());

            return "admin";

    }

    @GetMapping("/{uuid}/comments/delete")
    public String deleteComments(Model model, @PathVariable(value = "uuid") UUID uuid, Comment comment) {
        comments.deleteComment(uuid);
        return "delete";

    }



    @GetMapping("/{uuid}/comments")
    public String comments(Model model, @PathVariable(value = "uuid") UUID uuid, Comment comment) {
        comments.deleteComment(uuid);
        model.addAttribute("comments", comments.findById(uuid));
        return "comments";



    }



    @PostMapping("/{uuid}/comments")
    public String comment(Model model, Comment comment, Employer employer, @PathVariable(value = "uuid") UUID uuid) {
        comment.employer_id = uuid;
        comments.save(comment);
        model.addAttribute("comments", comments.findById(uuid));
        return "comments";

    }


    @GetMapping("/admin/2")
    public String sortPosition(Model model) {
        model.addAttribute("employers", repository.sortByPosition());
        return "position";
    }
    @GetMapping("/admin/3")
    public String sortLocation(Model model) {
        model.addAttribute("employers", repository.sortByLocation());
        return "location";
    }



    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("employers", repository.findAll());
        return "admin";
    }



}
