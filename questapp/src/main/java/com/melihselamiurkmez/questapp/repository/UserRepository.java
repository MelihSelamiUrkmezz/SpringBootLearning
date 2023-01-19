package com.melihselamiurkmez.questapp.repository;

import com.melihselamiurkmez.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByUsername(String username);
}
