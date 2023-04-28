package kafka.boardproject.sys.repository;

import kafka.boardproject.sys.entity.Comment;
import kafka.boardproject.sys.entity.CommentLike;
import kafka.boardproject.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Integer> {

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

}
