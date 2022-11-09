package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.StudentsSubject;

import java.util.List;

public interface StudentsSubjectRepository extends JpaRepository<StudentsSubject, Long> {
    List<StudentsSubject> findAllByStudent_Id(int id);
}
