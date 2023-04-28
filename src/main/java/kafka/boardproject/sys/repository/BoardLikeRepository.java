package kafka.boardproject.sys.repository;

import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.entity.BoardLike;
import kafka.boardproject.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardLikeRepository extends JpaRepository<BoardLike, Integer> {

    Optional<BoardLike> findByUserAndBoard(User user, Board board);

}
