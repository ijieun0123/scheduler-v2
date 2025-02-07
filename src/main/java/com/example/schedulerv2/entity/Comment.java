package com.example.schedulerv2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(String contents, Schedule schedule, User user) {
        this.contents = contents;
        this.schedule = schedule;
        this.user = user;
    }
}
