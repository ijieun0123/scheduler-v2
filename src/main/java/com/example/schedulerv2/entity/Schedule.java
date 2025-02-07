package com.example.schedulerv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter private String title;

    @Column(columnDefinition = "longtext")
    @Setter private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
