package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.StudentsSubject;

import java.util.List;

public interface StudentsSubjectRepository extends JpaRepository<StudentsSubject, Long> {
    List<StudentsSubject> findAllByStudent_Id(int id);
}
