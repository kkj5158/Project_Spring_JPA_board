package kafka.boardproject.service;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.dto.BoardDto;
import kafka.boardproject.entity.Board;
import kafka.boardproject.entity.User;
import kafka.boardproject.jwt.JWTUtil;
import kafka.boardproject.repository.BoardRepository;
import kafka.boardproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final JWTUtil jwtUtil;

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
    public Board createBoard(BoardDto boardDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기

            Board board = new Board(boardDto, user.getUsername());

            return boardRepository.save(board);
        } else {
            return null;
        }



    }

    @Transactional
    public Board update(int id, BoardDto boardDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청 받은 id 를 게시판 찾기
            Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시판이 존재하지 않습니다.") );

            // 요청받은 DTO 로 DB에 업데이트 할 객체 만들기

            board.update(boardDto);

            boardRepository.save(board);

            return board;

        } else {
            return null;
        }


    }
    @Transactional
    public String deleteBoard(int id,HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청 받은 id 를 게시판 찾기
            Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시판이 존재하지 않습니다.") );

            // 요청받은 게시판 삭제하기
            boardRepository.deleteById(id);
            return "success";

        } else {
            return "fail";
        }


    }



}