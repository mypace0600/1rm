package com.example.rm.admin.user.repository;

import com.example.rm.admin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.userId = :userId")
    User findByUserId(@Param("userId") String userId);
}
