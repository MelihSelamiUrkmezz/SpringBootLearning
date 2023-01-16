package com.melihselamiurkmez.questapp.controllers;

import com.melihselamiurkmez.questapp.entities.Comment;
import com.melihselamiurkmez.questapp.requests.CommentCreateRequest;
import com.melihselamiurkmez.questapp.requests.CommentUpdateRequest;
import com.melihselamiurkmez.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){

        return commentService.getAllComments(userId,postId);

    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){

        return commentService.getOneComment(commentId);


    }

    @PostMapping
    public Comment addNewComment(@RequestBody CommentCreateRequest newComment){

        return commentService.addNewComment(newComment);

    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateComment){

        return commentService.updateOneComment(commentId,updateComment);


    }


}
