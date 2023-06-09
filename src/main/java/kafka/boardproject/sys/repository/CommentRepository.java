package kafka.boardproject.sys.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import kafka.boardproject.sys.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
