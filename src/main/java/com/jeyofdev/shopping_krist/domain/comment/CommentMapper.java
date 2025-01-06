package com.jeyofdev.shopping_krist.domain.comment;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.domain.comment.dto.SaveCommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements IDomainMapper<Comment, CommentDTO, SaveCommentDTO> {
    @Override
    public CommentDTO mapFromEntity(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getTitle(),
                comment.getReview(),
                comment.getRating(),
                comment.getCreatedAt()
        );
    }

    @Override
    public Comment mapToEntity(SaveCommentDTO saveCommentDTO) {
        return Comment.builder()
                .title(saveCommentDTO.title())
                .review(saveCommentDTO.review())
                .rating(saveCommentDTO.rating())
                .build();
    }
}
