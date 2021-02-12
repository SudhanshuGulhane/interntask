package com.spring.loginexample.repo;

import com.spring.loginexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extending JpaRepo for accessing the defined functions
//for performing crud operations on request received from user

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
