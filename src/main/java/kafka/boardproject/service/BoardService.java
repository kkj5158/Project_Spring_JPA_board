package kafka.boardproject.service;


import kafka.boardproject.dto.BoardDto;
import kafka.boardproject.entity.Board;
import kafka.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Board getBoardbyid(int id) {

        Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시판이 존재하지 않습니다.") );

        return board;
    }

    @Transactional
    public Board createBoard(BoardDto boardDto){

        Board board = new Board(boardDto);

        return boardRepository.save(board);
    }

    @Transactional
    public Board update(int id, BoardDto boardDto) {

        Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시판이 존재하지 않습니다.") );

        if(boardDto.getPw().equals(board.getPw())) {
            board.update(boardDto);

            return board;
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }




    }

    @Transactional
    public String deleteMemo(int id, BoardDto boardDto) {

        Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시판이 존재하지 않습니다.") );

        if(boardDto.getPw().equals(board.getPw())) {
            boardRepository.deleteById(id);
            return "success";
        }

        return "fail";

    }



}