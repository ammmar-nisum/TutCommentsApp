package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") int tutorialId) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId).orElseThrow();
        List<Comment> comments = new ArrayList<Comment>();
        comments.addAll(tutorial.getComments());

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("tutorials/{tutorialId}/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") int tutorialId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            tutorial.getComments().add(commentRequest);
            return commentRepository.save(commentRequest);
        }).orElseThrow();
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @PutMapping("tutorials/{tutorialId}/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int id, @RequestBody Comment commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.setContent(commentRequest.getContent());
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }
    @DeleteMapping("tutorials/{tutorialId}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") int id) {
        commentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") int tutorialId) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);
        tutorial.ifPresent(value -> commentRepository.deleteAllByTutorialId(value.getId()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
