package com.sampleClass.BlogApp.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, length = 500)
    private String content;
    private String coverImageUrl;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate dateModified;
    @ManyToOne(cascade = CascadeType.PERSIST)
    //@Join column is used to modify props of join column added to this table eg change the name of the added fk column
    // join columns appear on the many side of the relationship
    private Author author;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }
}
