package kafka.boardproject.sys.controller;

import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.sys.dto.BoardDto;
import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import kafka.boardproject.sys.entity.Comment;
import kafka.boardproject.sys.service.BoardService;
import kafka.boardproject.sys.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    @GetMapping("/boards")
    public ResponseEntity getboard() {

        List<Board> boardList = boardService.getBoards();
        
        System.out.println("서비스 통과");

        if(boardList.size() == 0){
            List<BoardDto> boardDtoList = boardList.stream().map(BoardDto::new).collect(Collectors.toList());

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_GET_FAIL, boardDtoList), HttpStatus.OK);

        }
        else{
            List<BoardDto> boardDtoList = boardList.stream().map(BoardDto::new).collect(Collectors.toList());

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_GET, boardDtoList), HttpStatus.OK);
        }


    }

    @GetMapping("/boards/{id}")
    public ResponseEntity getBoardbyid(@PathVariable int id) {

        Board board = boardService.getBoardbyid(id);

        BoardDto reponseBoardDto = new BoardDto(board);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_GET_ID, reponseBoardDto), HttpStatus.OK);


    }


    @PostMapping("/board")
    public ResponseEntity createboard(HttpServletRequest request, @RequestBody BoardDto boardDto){

        Board board = boardService.createBoard(boardDto, request);

        BoardDto reponseBoardDto = new BoardDto(board);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_CREATE, reponseBoardDto), HttpStatus.OK);



    }

    @PutMapping("/board/{id}")
    public ResponseEntity updateBoard(@PathVariable int id, @RequestBody BoardDto boardDto, HttpServletRequest request) {

        Board board = boardService.update(id, boardDto, request);

        BoardDto reponseBoardDto = new BoardDto(board);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_UPDATE, reponseBoardDto), HttpStatus.OK);


    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable int id, HttpServletRequest request) {

        Board board = boardService.deleteBoard(id, request);

        BoardDto reponseBoardDto = new BoardDto(board);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_DELETE,reponseBoardDto), HttpStatus.OK);


    }



}


