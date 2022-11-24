package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Homework;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.repository.HomeworkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository repository;

    @Autowired
    SubjectService subjectService;

    public Homework addHomework(long subjectID, Homework homework) throws Exception {
        Optional<Subject> subject = subjectService.findById(subjectID);
        if(subject.isPresent()) {
            homework.setSubject(subject.get());
            return repository.save(homework);
        }
        else throw new Exception("NOT FOUND");
    }

    public List<Homework> findBySubjectId(long id){
        return repository.findBySubject_Id(id);
    }

    public void deleteHomework(int id) throws Exception {
        try{
            repository.deleteById(id);
        }catch (Exception e ){
            throw new Exception("NOT FOUND");
        }

    }
}
