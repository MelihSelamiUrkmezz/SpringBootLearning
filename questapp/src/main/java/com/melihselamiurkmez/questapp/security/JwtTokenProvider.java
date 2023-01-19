package com.melihselamiurkmez.questapp.security;

import lombok.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String APP_SECRET;
    private long EXPIRES_IN;



}
