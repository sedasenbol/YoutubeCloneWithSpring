package com.senbol.seda.youtubeclone.repository;

import com.senbol.seda.youtubeclone.model.VideoFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoFileRepository extends MongoRepository<VideoFile, String> {
}
