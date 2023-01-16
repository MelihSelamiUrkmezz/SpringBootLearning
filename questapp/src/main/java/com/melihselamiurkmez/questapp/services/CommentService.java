package com.melihselamiurkmez.questapp.services;

import com.melihselamiurkmez.questapp.entities.Comment;
import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.CommentRepository;
import com.melihselamiurkmez.questapp.requests.CommentCreateRequest;
import com.melihselamiurkmez.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;


    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {

        if(userId.isPresent() && postId.isPresent()){

            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());

        }
        else if(userId.isPresent()){

            return commentRepository.findByUserId(userId.get());

        }

        else if(postId.isPresent()){

            return commentRepository.findByPostId(postId.get());

        }
        else {
            return commentRepository.findAll();
        }

    }

    public Comment getOneComment(Long commentId) {

        return commentRepository.findById(commentId).orElse(null);

    }

    public Comment addNewComment(CommentCreateRequest newComment) {


        User user= userService.getOneUser(newComment.getId());
        Post post= postService.getOnePost(newComment.getPostId());

        if(user!=null && post!=null){

            Comment comment = new Comment();
            comment.setText(newComment.getText());
            comment.setPost(post);
            comment.setUser(user);
            comment.setId(newComment.getId());
            return commentRepository.save(comment);
        }
        return null;



    }

    public void deleteOneComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }

    public Comment updateOneComment(Long commentId,CommentUpdateRequest updateComment) {

        Optional<Comment> comment=commentRepository.findById(commentId);

        if(comment.isPresent()){

            Comment newComment = comment.get();
            newComment.setText(updateComment.getText());
            return commentRepository.save(newComment);
        }
        return null;

    }
}
