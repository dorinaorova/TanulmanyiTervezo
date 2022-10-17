package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Homework;
import tanulmanyitervezo.tervezo.Repository.HomeworkRepository;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository repository;

    public Homework addHomework(Homework homework){
        return repository.save(homework);
    }

    public List<Homework> findBySubjectId(long id){
        return repository.findBySubject_Id(id);
    }

    public void deleteHomework(int id){
        repository.deleteById(id);
    }

    public void deleteAllBySubject_id(long id){
        repository.deleteBySubject_Id(id);
    }
}
