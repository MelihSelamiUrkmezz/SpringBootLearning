package com.melihselamiurkmez.questapp.services;

import com.melihselamiurkmez.questapp.entities.Like;
import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.LikeRepository;
import com.melihselamiurkmez.questapp.requests.LikeCreateRequest;
import com.melihselamiurkmez.questapp.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;


    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }


    public List<LikeResponse> getAllLikes(Optional<Long> postId, Optional<Long> userId) {

        List<Like> likes;


        if(postId.isPresent() && userId.isPresent()){

            likes=likeRepository.findByUserIdAndPostId(userId,postId);

        }
        else if(postId.isPresent()){

            likes= likeRepository.findByPostId(postId);

        }

        else if(userId.isPresent()){

            likes= likeRepository.findByUserId(userId);
        }
        else{
            likes= likeRepository.findAll();
        }

        return likes.stream().map(l -> new LikeResponse(l)).collect(Collectors.toList());

    }

    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLike(Long likeId) {

        likeRepository.deleteById(likeId);
    }

    public Like createOneLike(LikeCreateRequest newLikeRequest) {

        User user = userService.getOneUser(newLikeRequest.getUser_id());
        Post post= postService.getOnePost(newLikeRequest.getPost_id());

        if(user!=null && post!=null){

            Like like = new Like();

            like.setId(newLikeRequest.getId());
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);

        }

        return null;
    }
}
