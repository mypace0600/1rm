package com.example.rm.reply.repository;

import com.example.rm.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {


}
