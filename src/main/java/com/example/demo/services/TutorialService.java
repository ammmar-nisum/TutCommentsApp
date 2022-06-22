package com.example.demo.services;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService {
    @Autowired
    TutorialRepository repo;

        public List<Tutorial> getTutorials(){
            List<Tutorial> list = new ArrayList<>();
            for(Tutorial tutorial: repo.findAll()){
                list.add(tutorial);
            }
            return list;
        }

    public Tutorial getTutorial(int id) {
            return repo.findById(id).get();
    }


    public void addTutorial(Tutorial listElement) {
        repo.save(listElement);
    }

    public void updateTutorial(Tutorial tutorial) {
        repo.save(tutorial);
    }

    public void deleteTutorial(int id) {
        repo.deleteById(id);
    }
}
