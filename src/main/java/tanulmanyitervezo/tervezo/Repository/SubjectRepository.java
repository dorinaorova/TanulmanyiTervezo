package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.Subject;

import java.util.Optional;

public interface SubjectRepository  extends JpaRepository<Subject, Long> {
    public Optional<Subject> findByName(String name);
}