package com.senbol.seda.youtubeclone.repository;

import com.senbol.seda.youtubeclone.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
