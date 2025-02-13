package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Comment;
import com.example.schedulerv2.entity.QComment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CommentsRepositoryImpl implements CommentsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentsRepositoryImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Comment> findByScheduleIdAndUserId(Long scheduleId, Long userId) {
        QComment comment = QComment.comment;
        BooleanBuilder builder = new BooleanBuilder();

        if(scheduleId != null) {
            builder.or(comment.schedule.id.eq(scheduleId));
        }

        if(userId != null){
            builder.or(comment.user.id.eq(userId));
        }

        if(scheduleId == null && userId == null){
            return List.of();
        }

        return queryFactory
                .selectFrom(comment)
                .where(builder)
                .fetch();
    }
}
