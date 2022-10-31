package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Semester;
import tanulmanyitervezo.tervezo.Repository.SemesterRepository;

import java.util.Collections;
import java.util.List;

@Service
public class SemesterService {
    @Autowired
    SemesterRepository repository;

    public Semester addSemester(Semester semester){
        if(semester.isCurrent()){
            Semester current = findCurrent();
            if(current!=null) current.setCurrent(false);
        }
        return repository.save(semester);
    }

    public List<Semester> findAll(){
        return repository.findAll();
    }

    public Semester findCurrent(){
        List<Semester> semesters = repository.findAll();
        for (Semester s : semesters){
            if(s.isCurrent()) return s;
        }
        return null;
    }
    public Semester setCurrent(int id){
        Semester semester = findCurrent();
        if(semester!=null) semester.setCurrent(false);

        Semester newCurrent = repository.findById(id).get();
        newCurrent.setCurrent(true);
        return  newCurrent;
    }

    public Semester findNext(int id){
        List<Semester> semesters = repository.findAll();
        Collections.sort(semesters);
        for(int i=0; i< semesters.size(); i++){
            if(semesters.get(i).getId()==id){
                if(i< semesters.size()-1) {
                    i++;
                    return semesters.get(i);
                }
            }
        }
        return null;
    }

    public Semester findPrevious(int id){
        List<Semester> semesters = repository.findAll();
        Collections.sort(semesters);
        for(int i=0; i< semesters.size(); i++){
            if(semesters.get(i).getId()==id){
                if(i>0) {
                    i--;
                    return semesters.get(i);
                }
            }
        }
        return null;
    }
}
