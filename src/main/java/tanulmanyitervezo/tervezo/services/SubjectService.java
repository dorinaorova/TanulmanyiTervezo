package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Subject;
import tanulmanyitervezo.tervezo.Repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository repository;

    public List<Subject> findAll(){
        return repository.findAll();
    }

    public Optional<Subject> findByName(String name){
        return repository.findByName(name);
    }

    public Subject addSubject (Subject subject){
        return repository.save(subject);
    }

    public void deleteSubject(int id){
        repository.deleteById(id);
    }
}
