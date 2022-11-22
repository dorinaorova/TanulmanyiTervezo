package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository repository;

    @Autowired
    PeriodService periodService;

    @Autowired
    ZHService zhService;

    @Autowired
    HomeworkService homeworkService;

    public List<Subject> findAll(){
        return repository.findAll();
    }

    public Optional<Subject> findByName(String name){
        return repository.findByName(name);
    }

    public Subject addSubject (Subject subject){
        return repository.save(subject);
    }

    public void deleteSubject(long id){
        repository.deleteById(id);
        periodService.deleteAllBySubjectId(id);
        zhService.deleteAllBySubject_id(id);
        homeworkService.deleteAllBySubject_id(id);
    }

    public Optional<Subject> findById(long id){
        return repository.findById(id);
    }

    public Subject updateSubject(Subject subject){
        return repository.save(subject);
    }
}