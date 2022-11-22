package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.StudentsSubject;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.services.StudentsSubjectService;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentsSubjectController {

    @Autowired
    StudentsSubjectService service;

    @GetMapping("/findbystudent/{semesterid}/{id}")
    public ResponseEntity<List<Subject>> findSubjectsByStudent_id(@PathVariable("id") int id, @PathVariable("semesterid") int semesterid){
        List<Subject> subjects = service.findSubjectsByStudent_id(id, semesterid);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/findbystudent/current/{id}")
    public ResponseEntity<List<Subject>> findcurrentByStudentt_id(@PathVariable("id") int id){
        List<Subject> subjects = service.findCurrentByStudent_id(id);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<StudentsSubject> add(@RequestBody Subject subject, @PathVariable("id") int studentId){
        StudentsSubject newStudentsSubject = service.add(subject, studentId);
        return  new ResponseEntity<>(newStudentsSubject, HttpStatus.CREATED);
    }

}
