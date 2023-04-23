package kafka.boardproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.dto.BoardDto;
import kafka.boardproject.dto.DefaultRes;
import kafka.boardproject.entity.Board;
import kafka.boardproject.http.ResponseMessage;
import kafka.boardproject.http.StatusCode;
import kafka.boardproject.service.BoardService;
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

    @GetMapping("/board")
    public List<BoardDto> getboard() {

        List<Board> boardList = boardService.getBoards();

        return boardList.stream().map(BoardDto::new).collect(Collectors.toList());

    }

    @GetMapping("/board/{id}")
    public BoardDto getBoardbyid(@PathVariable int id) {

        Board board = boardService.getBoardbyid(id);

        BoardDto reponseBoardDto = new BoardDto(board);

        return reponseBoardDto;
    }


    @PostMapping("/board")
    public BoardDto createboard(HttpServletRequest request, @RequestBody BoardDto boardDto){



        Board board = boardService.createBoard(boardDto, request);

        BoardDto reponseBoardDto = new BoardDto(board);

        return reponseBoardDto;
    }

    @PutMapping("/board/{id}")
    public BoardDto updateBoard(@PathVariable int id, @RequestBody BoardDto boardDto, HttpServletRequest request) {

        Board board = boardService.update(id, boardDto, request);

        BoardDto reponseBoardDto = new BoardDto(board);

        return reponseBoardDto;

    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable int id, HttpServletRequest request) {

        boardService.deleteBoard(id, request);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_DELETE), HttpStatus.OK);

    }



}


