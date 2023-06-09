package kafka.boardproject.sys.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import kafka.boardproject.sec.jwt.JwtUtil;
import kafka.boardproject.sys.dto.CommentDto;
import kafka.boardproject.sys.entity.Comment;
import kafka.boardproject.sys.entity.User;
import kafka.boardproject.sys.entity.UserRoleEnum;
import kafka.boardproject.sys.repository.CommentRepository;
import kafka.boardproject.sys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    @Transactional
    public Comment createComment(CommentDto commentDto) {

        // Spring Security에서 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Optional<User> found = userRepository.findByUsername(userDetails.getUsername());

            if (found.isEmpty()) {
                throw new IllegalArgumentException("등록된 사용자가 없습니다.");
            }

            // 요청받은 DTO 로 DB에 저장할 객체 만들기

            Comment comment = new Comment(commentDto, userDetails.getUsername());

            commentRepository.save(comment);

            return comment;


        } else {
            throw new IllegalArgumentException("Token Error");
        }
    }

    @Transactional
    public Comment updateComment(int id, CommentDto commentDto) {

        // Spring Security에서 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청 받은 id 를 게시판 찾기
            Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

            // 로그인한 유저가 관리자 이거나, 게시물을 작성한 유저여야만 수정이 가능하다.

            if (comment.getUsername().equals(userDetails.getUsername()) || userDetails.getAuthorities().equals(UserRoleEnum.ADMIN)) {

                comment.update(commentDto);

                commentRepository.save(comment);

                return comment;

            } else {
                // 아이디가 일치하지 않는다. 관리자가 아니다.
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
            }

            // 요청받은 DTO 로 DB에 업데이트 할 객체 만들기


        } else {
            throw new IllegalArgumentException("Token Error");
        }


    }

    @Transactional
    public Comment deleteComment(int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (authentication.isAuthenticated())  {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청 받은 id 를 게시판 찾기
            Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

            // 로그인한 유저가 관리자 이거나, 댓글을 작성한 유저여야만 수정이 가능하다.

            if (comment.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
                // 요청받은 댓글 삭제하기
                commentRepository.deleteById(id);
                return comment;
            } else {
                // 아이디가 일치하지 않는다. 관리자가 아니다.
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");

            }

        } else {
            throw new IllegalArgumentException("Token Error");
        }


    }


}
