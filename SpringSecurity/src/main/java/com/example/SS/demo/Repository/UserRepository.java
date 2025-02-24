package com.example.SS.demo.Repository;

import com.example.SS.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername (String username);
}
