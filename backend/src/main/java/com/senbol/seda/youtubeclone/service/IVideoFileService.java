package com.senbol.seda.youtubeclone.service;

import com.senbol.seda.youtubeclone.error.EntityNotSavedException;
import com.senbol.seda.youtubeclone.error.NullFieldException;
import com.senbol.seda.youtubeclone.model.VideoFile;
import org.springframework.stereotype.Service;

@Service
public interface IVideoFileService {
    VideoFile save(VideoFile videoFile) throws NullFieldException, IllegalAccessException, EntityNotSavedException;
}
