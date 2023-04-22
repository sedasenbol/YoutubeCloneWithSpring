package com.senbol.seda.youtubeclone.service.Impl;

import com.senbol.seda.youtubeclone.common.util.CommonUtils;
import com.senbol.seda.youtubeclone.error.EntityNotSavedException;
import com.senbol.seda.youtubeclone.error.NullFieldException;
import com.senbol.seda.youtubeclone.model.VideoFile;
import com.senbol.seda.youtubeclone.repository.VideoFileRepository;
import com.senbol.seda.youtubeclone.service.IVideoFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoFileService implements IVideoFileService {

    @Autowired
    private final VideoFileRepository videoFileRepository;

    @Override
    public VideoFile save(VideoFile videoFile) throws NullFieldException, IllegalAccessException, EntityNotSavedException {
        CommonUtils.checkNullFields(videoFile);
        return CommonUtils.checkSavedEntity(videoFileRepository.save(videoFile), VideoFile.class.getSimpleName());
    }
}
