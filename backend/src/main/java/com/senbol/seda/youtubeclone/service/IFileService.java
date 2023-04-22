package com.senbol.seda.youtubeclone.service;

import com.senbol.seda.youtubeclone.dto.VideoFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IFileService {
    String uploadFile(MultipartFile file);

    List<VideoFileDto> getListObjects();
}
