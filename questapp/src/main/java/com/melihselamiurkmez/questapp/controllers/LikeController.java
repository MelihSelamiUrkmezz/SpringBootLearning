package com.melihselamiurkmez.questapp.controllers;


import com.melihselamiurkmez.questapp.entities.Like;
import com.melihselamiurkmez.questapp.requests.LikeCreateRequest;
import com.melihselamiurkmez.questapp.response.LikeResponse;
import com.melihselamiurkmez.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;


    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){

        return likeService.getAllLikes(postId,userId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLike(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLike(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest NewLikeRequest){
        return likeService.createOneLike(NewLikeRequest);
    }


}
