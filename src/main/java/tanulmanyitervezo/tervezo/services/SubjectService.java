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

    public List<Subject> findAll(){
        return repository.findAll();
    }

    public Optional<Subject> findByName(String name){
        return repository.findByName(name);
    }

    public Subject addSubject (Subject subject){
        return repository.save(subject);
    }

    public void deleteSubject(long id) throws Exception {
        try{
            repository.deleteById(id);
        }
        catch(Exception e){
            throw new Exception("NOT FOUND");
        }
    }

    public Optional<Subject> findById(long id){
        return repository.findById(id);
    }

    public Subject updateSubject(long id, Subject subj) throws Exception {
        Optional<Subject> subject = repository.findById(id);
        if(subject.isPresent()){
            if(subj.getName()!=null) subject.get().setName(subj.getName());
            if(subj.getDescription()!=null) subject.get().setDescription(subj.getDescription());
            if(subj.getKredit()>0) subject.get().setKredit(subj.getKredit());
            return repository.save(subject.get());
        }
        else throw new Exception("NOT FOUND");
    }
}