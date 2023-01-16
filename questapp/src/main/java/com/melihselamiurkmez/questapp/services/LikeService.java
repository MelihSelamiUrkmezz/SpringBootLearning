package com.melihselamiurkmez.questapp.services;

import com.melihselamiurkmez.questapp.entities.Like;
import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.LikeRepository;
import com.melihselamiurkmez.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {

        if(postId.isPresent() && userId.isPresent()){

            return likeRepository.findByUserIdAndPostId(userId,postId);

        }
        else if(postId.isPresent()){

            return likeRepository.findByPostId(postId);

        }

        else if(userId.isPresent()){

            return likeRepository.findByUserId(userId);
        }
        else{
            return likeRepository.findAll();
        }


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
