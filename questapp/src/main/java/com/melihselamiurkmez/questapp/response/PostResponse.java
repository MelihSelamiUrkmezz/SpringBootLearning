package com.melihselamiurkmez.questapp.response;

import com.melihselamiurkmez.questapp.entities.Post;
import com.melihselamiurkmez.questapp.entities.Like;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long user_id;
    String username;
    String text;
    String title;

     List<LikeResponse> likes;
    public PostResponse(Post entity,List<LikeResponse> likes){

        this.id=entity.getId();
        this.user_id=entity.getUser().getId();
        this.username=entity.getUser().getUsername();
        this.text=entity.getText();
        this.title=entity.getTitle();
        this.likes=likes;

    }

}
