package com.melihselamiurkmez.questapp.services;
import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.PostRepository;
import com.melihselamiurkmez.questapp.requests.PostCreateRequest;
import com.melihselamiurkmez.questapp.requests.PostUpdateRequest;
import com.melihselamiurkmez.questapp.response.LikeResponse;
import com.melihselamiurkmez.questapp.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {

        List<Post> posts_list;

        if(userId.isPresent()){
            posts_list = postRepository.findByUserId(userId.get());
        }
        else{
            posts_list =postRepository.findAll();
        }
        return posts_list.stream().map(p ->{
            List<LikeResponse> likes=likeService.getAllLikes(Optional.of(null),Optional.of(p.getId()));
        return new PostResponse(p,likes);}).collect(Collectors.toList());
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
