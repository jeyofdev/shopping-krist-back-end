package com.jeyofdev.shopping_krist.domain.comment;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.ProductRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class CommentService extends AbstractDomainServiceBase<Comment, CommentRepository> {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ProductRepository productRepository) {
        super(commentRepository, "comment");
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Comment save(Comment comment, UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Product with id {0} not found", productId))
                );

        comment.setCreatedAt(new Date());
        comment.setProduct(product);

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
                .product(existingComment.getProduct())
                .build();

        return commentRepository.save(existingCommentUpdated);
    }

    @Transactional
    public void deleteById(UUID commentId) {
        findById(commentId);
        commentRepository.deleteById(commentId);
    }
}
