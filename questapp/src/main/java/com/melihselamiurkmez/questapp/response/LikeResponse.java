package com.melihselamiurkmez.questapp.response;

import com.melihselamiurkmez.questapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id;
    Long post_id;
    Long user_id;

    public LikeResponse(Like like){

        this.id=like.getId();
        this.post_id=like.getPost().getId();
        this.user_id=like.getUser().getId();

    }



}
