package com.senbol.seda.youtubeclone.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Document(value = "Video")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video extends BaseEntity {
    private String fileId;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private Set<String> tags;
    private String videoUrl;
    private String thumbnailUrl;
    private List<Comment> commentList;

}
