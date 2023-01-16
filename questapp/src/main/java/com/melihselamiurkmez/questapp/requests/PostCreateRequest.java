package com.melihselamiurkmez.questapp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {

    Long id;
    String title;
    String text;
    Long user_id;

}
