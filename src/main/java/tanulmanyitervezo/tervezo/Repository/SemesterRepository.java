package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
