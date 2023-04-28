package kafka.boardproject.sys.controller;

import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import kafka.boardproject.sys.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/{id}/like")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping()
    public ResponseEntity insert(@PathVariable int id) {
        boardLikeService.insert(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_LIKE_CREATE));
    }

    @DeleteMapping()
    public ResponseEntity delete(@PathVariable int id) {
        boardLikeService.delete(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_LIKE_DELETE));
    }

}
