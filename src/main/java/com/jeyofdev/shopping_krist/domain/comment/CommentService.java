package com.jeyofdev.shopping_krist.domain.comment;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class CommentService extends AbstractDomainServiceBase<Comment, CommentRepository> {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        super(commentRepository, "comment");
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        comment.setCreatedAt(new Date());
        return commentRepository.save(comment);
    }

    public Comment updateById(UUID commentId, Comment updatedComment) {
        Comment existingComment = findById(commentId);
        Comment existingCommentUpdated = Comment.builder()
                .id(commentId)
                .title(updatedComment.getTitle() != null ? updatedComment.getTitle() : existingComment.getTitle())
                .review(updatedComment.getReview() != null ? updatedComment.getReview() : existingComment.getReview())
                .rating(updatedComment.getRating() != null ? updatedComment.getRating() : existingComment.getRating())
                .createdAt(existingComment.getCreatedAt())
                .build();

        return commentRepository.save(existingCommentUpdated);
    }

    @Transactional
    public void deleteById(UUID commentId) {
        findById(commentId);
        commentRepository.deleteById(commentId);
    }
}
