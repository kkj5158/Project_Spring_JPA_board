package kafka.boardproject.sys.controller;

import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.sys.dto.BoardDto;
import kafka.boardproject.sys.dto.CommentDto;
import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import kafka.boardproject.sys.entity.Comment;
import kafka.boardproject.sys.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/comment")
    public ResponseEntity createComment(HttpServletRequest request, @RequestBody CommentDto commentDto){

        Comment comment = commentService.createComment(commentDto, request);

        CommentDto commentresponseDto = new CommentDto(comment);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.COMMENT_CREATE, commentresponseDto), HttpStatus.OK);

    }

    @PutMapping("/comment/{id}")
    public ResponseEntity updateComment(@PathVariable int id, @RequestBody CommentDto commentDto, HttpServletRequest request) {

        Comment comment = commentService.updateComment(id, commentDto, request);

        CommentDto commentresponseDto = new CommentDto(comment);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.COMMENT_UPDATE, commentresponseDto), HttpStatus.OK);


    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable int id, HttpServletRequest request) {

        Comment comment = commentService.deleteComment(id, request);

        CommentDto commentresponseDto = new CommentDto(comment);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.COMMENT_DELETE, commentresponseDto), HttpStatus.OK);




    }



}
