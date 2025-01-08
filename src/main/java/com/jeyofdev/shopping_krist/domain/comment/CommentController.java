package com.jeyofdev.shopping_krist.domain.comment;

import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.domain.comment.dto.SaveCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<CommentDTO>>> findAllComment() {
        List<Comment> commentList = commentService.findAll();
        List<CommentDTO> commentDTOList = commentList.stream().map(commentMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, commentDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<DomainSuccessResponse<CommentDTO>> findCommentById(@PathVariable("commentId") UUID commentId) {
        Comment comment = commentService.findById(commentId);
        CommentDTO commentDTO = commentMapper.mapFromEntity(comment);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, commentDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<DomainSuccessResponse<CommentDTO>> saveComment(
            @RequestBody SaveCommentDTO saveCommentDTO,
            @PathVariable("productId") UUID productId
    ) {
        Comment comment = commentMapper.mapToEntity(saveCommentDTO);
        Comment newComment = commentService.save(comment, productId);
        CommentDTO newCommentDTO = commentMapper.mapFromEntity(newComment);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newCommentDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<DomainSuccessResponse<CommentDTO>> updateCommentById(
            @PathVariable("commentId") UUID commentId,
            @RequestBody SaveCommentDTO saveCommentDTO
    ) {
        Comment comment = commentMapper.mapToEntity(saveCommentDTO);
        Comment updateComment = commentService.updateById(commentId, comment);
        CommentDTO updateCommentDTO = commentMapper.mapFromEntity(updateComment);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateCommentDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteCommentById(@PathVariable("commentId") UUID commentId) {
        String message = commentService.deleteById(commentId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
