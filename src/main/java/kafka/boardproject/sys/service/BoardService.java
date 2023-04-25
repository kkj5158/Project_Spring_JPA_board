package kafka.boardproject.sys.service;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.sys.dto.BoardDto;
import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.entity.User;
import kafka.boardproject.sec.jwt.JWTUtil;
import kafka.boardproject.sys.entity.UserRoleEnum;
import kafka.boardproject.sys.repository.BoardRepository;
import kafka.boardproject.sys.repository.UserRepository;
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

        Board board = boardRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당 게시글이 존재하지 않습니다.") );

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
            // 토큰이 일치하지 않는다.
            throw new IllegalArgumentException("Token Error");
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

            // 로그인한 유저가 관리자 이거나, 게시물을 작성한 유저여야만 수정이 가능하다.

            if(board.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){

                board.update(boardDto);

                boardRepository.save(board);

                return board;

            }
            else{
                // 아이디가 일치하지 않는다. 관리자가 아니다.
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
            }

            // 요청받은 DTO 로 DB에 업데이트 할 객체 만들기


        } else {
            throw new IllegalArgumentException("Token Error");
        }


    }
    @Transactional
    public Board deleteBoard(int id,HttpServletRequest request) {

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


            // 로그인한 유저가 관리자 이거나, 게시물을 작성한 유저여야만 수정이 가능하다.

            if(board.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){

                // 요청받은 게시판 삭제하기
                boardRepository.deleteById(id);
                System.out.println("삭제 완료");
                return board;
            }
            else{
                // 아이디가 일치하지 않는다. 관리자가 아니다.
                System.out.println("삭제 실패");

                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");

            }

        } else {
            throw new IllegalArgumentException("Token Error");
        }


    }



}