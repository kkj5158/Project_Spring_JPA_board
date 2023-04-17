package kafka.boardproject.controller;

import kafka.boardproject.dto.BoardDto;
import kafka.boardproject.entity.Board;
import kafka.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public List<Board> getboard() {
        return boardService.getBoards();
    }

    @GetMapping("/board/{id}")
    public Board getboardbyid(@PathVariable int id) {


        return boardService.getBoardbyid(id);
    }


    @PostMapping("/board")
    public Board createboard(@RequestBody BoardDto boardDto){

        return boardService.createBoard(boardDto);
    }

    @PutMapping("/board/{id}")
    public Board updateBoard(@PathVariable int id, @RequestBody BoardDto boardDto) {

        return boardService.update(id, boardDto);

    }

    @DeleteMapping("/board/{id}")
    public String deleteMemo(@PathVariable int id, @RequestBody BoardDto boardDto) {


        return boardService.deleteMemo(id, boardDto);
    }



}


