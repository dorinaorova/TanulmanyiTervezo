package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.Subject;

import java.util.Optional;

public interface SubjectRepository  extends JpaRepository<Subject, Long> {
    public Optional<Subject> findByName(String name);
}