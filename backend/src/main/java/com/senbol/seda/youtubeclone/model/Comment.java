package com.senbol.seda.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    private String text;
    private String authorId;
    private Integer likeCount;
    private Integer dislikeCount;
}
