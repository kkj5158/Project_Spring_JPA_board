package kafka.boardproject.sys.service;

import jakarta.transaction.Transactional;
import kafka.boardproject.sys.entity.*;
import kafka.boardproject.sys.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void insert(int commentid) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {

            // 유저 정보 확인하기

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();


            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 게시물 정보 확인하기

            Comment comment = commentRepository.findById(commentid)
                    .orElseThrow(() -> new IllegalArgumentException("존재하는 댓글이 아닙니다 : " + commentid));

            // 이미 좋아요되어있으면 에러 반환
            if (commentLikeRepository.findByUserAndComment(user, comment).isPresent()) {
                //TODO 409에러로 변경
                throw new IllegalArgumentException("이미 like가 눌러져있습니다.");
            }

            CommentLike commentLike = CommentLike.builder()
                    .comment(comment)
                    .user(user)
                    .build();

            commentLikeRepository.save(commentLike);
        }
        else{
            // 토큰이 일치하지 않는다.
            throw new IllegalArgumentException("Token Error");
        }
    }

    @Transactional
    public void delete(int commentid) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 유저 정보 확인하기

        if (authentication.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );


            // 게시물 정보 확인하기
            Comment comment = commentRepository.findById(commentid)
                    .orElseThrow(() -> new IllegalArgumentException("존재하는 댓글이 아닙니다 : " + commentid));

            // 이미 눌러진 라이크가 아니면 예외 반환하기

            CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                    .orElseThrow(() -> new IllegalArgumentException("눌려진 라이크가 아닙니다."));

            commentLikeRepository.delete(commentLike);

        }
        else{
            throw new IllegalArgumentException("Token Error");
        }
    }
}