package com.senbol.seda.youtubeclone.service.Impl;

import com.senbol.seda.youtubeclone.common.util.CommonUtils;
import com.senbol.seda.youtubeclone.dto.VideoFileDto;
import com.senbol.seda.youtubeclone.error.EntityNotSavedException;
import com.senbol.seda.youtubeclone.error.NullFieldException;
import com.senbol.seda.youtubeclone.model.Video;
import com.senbol.seda.youtubeclone.model.VideoFile;
import com.senbol.seda.youtubeclone.repository.VideoRepository;
import com.senbol.seda.youtubeclone.service.IFileService;
import com.senbol.seda.youtubeclone.service.IVideoFileService;
import com.senbol.seda.youtubeclone.service.IVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoService implements IVideoService {

    @Autowired
    private final IFileService fileService;
    @Autowired
    private final IVideoFileService videoFileService;
    @Autowired
    private final VideoRepository videoRepository;

    public String uploadVideo(MultipartFile file) {
        String url = fileService.uploadFile(file);
        try {
            Video video = saveVideo(file, url);
            log.info("uploadVideo:: Video entity with id {} was saved to the database.", video.getId());
        } catch (Exception e) {
            log.error("uploadVideo:: Error while saving the video to the database. Error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while saving the video to the database.");
        }
        return url;
    }

    private Video saveVideo(MultipartFile file, String url) throws IllegalAccessException, NullFieldException, EntityNotSavedException {
        VideoFile videoFile = saveVideoFile(file, url);

        Video video = Video.builder().fileId(videoFile.getId()).build();

        CommonUtils.checkNullFields(video);
        return CommonUtils.checkSavedEntity(videoRepository.save(video), Video.class.getSimpleName());
    }

    @NotNull
    private VideoFile saveVideoFile(MultipartFile file, String url) throws IllegalAccessException, NullFieldException, EntityNotSavedException {
        VideoFile videoFile = VideoFile.builder()
                .multipartFile(file)
                .url(url)
                .size(file.getSize())
                .build();

        CommonUtils.checkNullFields(videoFile);
        return videoFileService.save(videoFile);
    }

    public List<VideoFileDto> getListObjects() {
        return fileService.getListObjects();
    }
}
