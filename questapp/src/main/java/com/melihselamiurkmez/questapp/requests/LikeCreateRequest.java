package com.melihselamiurkmez.questapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    Long id;
    Long user_id;
    Long post_id;
}
