package com.example.demo.controller;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;
import com.example.demo.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialService service;

    @GetMapping("/tutorials")
    public List<Tutorial> getTutorials(){
        return service.getTutorials();
    }

    @GetMapping("/tutorials/{id}")
    public Tutorial getTutorial(@PathVariable int id){
        return (Tutorial) service.getTutorial(id);
    }
    @PostMapping("/tutorials")
    public void addTutorial(@RequestBody Tutorial listElement){
        service.addTutorial(listElement);
    }

    @PutMapping("/tutorials/{id}")
    public void updateTutorial(@RequestBody Tutorial post){
        service.updateTutorial(post);
    }

    @DeleteMapping("/tutorials/{id}")
    public void deleteTutorial(@PathVariable int id){
        service.deleteTutorial(id);
    }

}

