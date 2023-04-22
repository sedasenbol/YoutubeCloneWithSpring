package com.senbol.seda.youtubeclone.controller;

import com.senbol.seda.youtubeclone.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @GetMapping
    public ResponseEntity<Object> getFiles() {
        return ResponseEntity.ok(videoService.getListObjects());
    }
}
