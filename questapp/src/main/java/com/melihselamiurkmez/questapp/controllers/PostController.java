package com.melihselamiurkmez.questapp.controllers;

import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.requests.PostCreateRequest;
import com.melihselamiurkmez.questapp.requests.PostUpdateRequest;
import com.melihselamiurkmez.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePost(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPost){
        return postService.createNewPost(newPost);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest){

        return postService.updateOnePost(postId,updateRequest);

    }


    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePost(postId);
    }

}