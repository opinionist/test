package com.afterschool.test.repository;

import com.afterschool.test.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
//    User findUserByEmail(String email);
    User findUserById(Long id);
    User getByUid(String uid);
}