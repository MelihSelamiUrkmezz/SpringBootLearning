package com.melihselamiurkmez.questapp.services;

import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.PostRepository;
import com.melihselamiurkmez.questapp.requests.PostCreateRequest;
import com.melihselamiurkmez.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }
    public List<Post> getAllPosts(Optional<Long> userId) {

        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }
    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }


    public Post createNewPost(PostCreateRequest newPost) {

        User user = userService.getOneUser(newPost.getUser_id());

        if(user!=null){

            Post newpost = new Post();
            newpost.setId(newPost.getId());
            newpost.setText(newPost.getText());
            newpost.setTitle(newPost.getTitle());
            newpost.setUser(user);

            return postRepository.save(newpost);
        }
        return null;

    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest updateRequest) {

        Optional<Post> post = postRepository.findById(postId);

        if(post.isPresent()){

            Post update_post = post.get();
            update_post.setText(updateRequest.getText());
            update_post.setTitle(updateRequest.getTitle());
            return postRepository.save(update_post);
        }
        else{
            return null;
        }


    }
}
