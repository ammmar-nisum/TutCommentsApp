package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //@Query("DELETE FROM Comment c WHERE c.tutorial_id = ?1")
    void deleteAllByTutorialId(int tutorialId);
}
