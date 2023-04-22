package com.senbol.seda.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Document(value = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Set<String> subscribedToUsers;
    private Set<String> subscribers;
    private List<String> videoHistory;
    private Set<String> likedVideos;
    private Set<String> dislikedVideos;
}
