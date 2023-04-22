package com.senbol.seda.youtubeclone.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoFile extends BaseEntity {
    private String title;
    private String description;
    @SuppressWarnings("java:S1948")
    private MultipartFile multipartFile;
    private String url;
    private Long size;
    private String filename;
}
