package kafka.boardproject.sys.controller;

import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import kafka.boardproject.sys.service.BoardLikeService;
import kafka.boardproject.sys.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/{id}/like")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping()
    public ResponseEntity insert(@PathVariable int id) {
        commentLikeService.insert(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.COMMENT_LIKE_CREATE));
    }

    @DeleteMapping()
    public ResponseEntity delete(@PathVariable int id) {
        commentLikeService.delete(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.COMMENT_LIKE_DELETE));
    }

}
