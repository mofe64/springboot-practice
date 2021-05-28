package com.sampleClass.BlogApp.data.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private UUID id;
    private String commentatorName;
    @Column(nullable = false, length = 150)
    private String content;
    private LocalDate dateCreated;
}
