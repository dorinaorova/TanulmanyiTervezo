package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.GradeZH;

import java.util.List;

public interface GradeZHRepository extends JpaRepository<GradeZH, Integer> {

    List<GradeZH> findAllByUser_id(int user_id);
}
