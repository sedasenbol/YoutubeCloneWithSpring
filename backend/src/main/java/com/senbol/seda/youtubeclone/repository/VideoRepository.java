package com.senbol.seda.youtubeclone.repository;

import com.senbol.seda.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {

}
