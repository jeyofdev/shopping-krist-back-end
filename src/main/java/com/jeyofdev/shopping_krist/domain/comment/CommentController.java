package com.jeyofdev.shopping_krist.domain.comment;

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
    private final CommentDomainMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllComment() {
        List<Comment> commentList = commentService.findAll();
        List<CommentDTO> commentDTOList = commentList.stream().map(commentMapper::mapFromEntity).toList();

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable("commentId") UUID commentId) {
        Comment comment = commentService.findById(commentId);
        CommentDTO commentDTO = commentMapper.mapFromEntity(comment);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CommentDTO> saveComment(
            @RequestBody SaveCommentDTO saveCommentDTO
    ) {
        Comment comment = commentMapper.mapToEntity(saveCommentDTO);
        Comment newComment = commentService.save(comment);
        CommentDTO newCommentDTO = commentMapper.mapFromEntity(newComment);

        return new ResponseEntity<>(newCommentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateCommentById(
            @PathVariable("commentId") UUID commentId,
            @RequestBody SaveCommentDTO saveCommentDTO
    ) {
        Comment comment = commentMapper.mapToEntity(saveCommentDTO);
        Comment updateComment = commentService.updateById(commentId, comment);
        CommentDTO updateCommentDTO = commentMapper.mapFromEntity(updateComment);

        return new ResponseEntity<>(updateCommentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable("commentId") UUID commentId) {
        commentService.deleteById(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
