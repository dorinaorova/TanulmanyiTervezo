package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.model.StudentsSubject;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.StudentsSubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsSubjectService {

    @Autowired
    StudentsSubjectRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    SemesterService semesterService;

    public StudentsSubject add(Subject subject,int id) throws Exception {
        boolean findSS = false;
        StudentsSubject newStudentSubject = new StudentsSubject();
        List<StudentsSubject> studentsSubjects = repository.findAllByStudent_Id(id);
        for(StudentsSubject ss: studentsSubjects){
            if(ss.getSemester().isCurrent()){
                boolean subjectExist=false;
                for(Subject s: ss.getSubject()){
                    if (s.getId() == subject.getId()) {
                        subjectExist = true;
                        break;
                    }
                }
                if(!subjectExist) {
                    ss.addSubject(subject);
                }
                newStudentSubject=ss;
                findSS = true;
                repository.save(newStudentSubject);
            }
        }
        if(!findSS){
            Optional<User> student = userService.findById(id);
            if(student.isPresent()) {
                Semester semester = semesterService.findCurrent();
                newStudentSubject.setSemester(semester);
                newStudentSubject.setStudent(student.get());
                newStudentSubject.addSubject(subject);
                repository.save(newStudentSubject);
            }
            else throw new Exception("NOT FOUND");
        }
        return newStudentSubject;
    }

    public List<Subject> findSubjectsByStudent_id(int id, int semesterid){
        List<StudentsSubject> studentsSubjects = repository.findAllByStudent_Id(id);
        for(StudentsSubject ss: studentsSubjects){
            if(ss.getSemester().getId()==semesterid){
                return ss.getSubject();
            }
        }
        return null;
    }

    public List<Subject> findCurrentByStudent_id(int id){
        Semester current = semesterService.findCurrent();
        List<Subject> subjects = findSubjectsByStudent_id(id, current.getId());
        return  subjects;
    }
}
