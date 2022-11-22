package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
