package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.Models.Subject;
import tanulmanyitervezo.tervezo.services.SubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectCotnroller {

    @Autowired
    SubjectService service;

    @GetMapping("/findAll")
    public ResponseEntity<List<Subject>> findAll(){
        List<Subject> subjects = service.findAll();
        return  new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<Subject>> findByName(@PathVariable("name") String name){
        Optional<Subject> subject = service.findByName(name);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Subject>> findById(@PathVariable("id") int id){
        Optional<Subject> subject = service.findById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/add")
    public  ResponseEntity<Subject> addSubject(@RequestBody Subject newSubject){
        Subject subject = service.addSubject(newSubject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteSubject(@PathVariable("id") int id){
        service.deleteSubject(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") int id, @RequestBody Subject subj){
        Subject subject = service.findById(id).get();
        if(subj.getName()!=null) subject.setName(subj.getName());
        if(subj.getDescription()!=null) subject.setDescription(subj.getDescription());
        if(subj.getKredit()>0) subject.setKredit(subj.getKredit());
        Subject updateSubj = service.updateSubject(subject);
        return new ResponseEntity<>(updateSubj, HttpStatus.OK);
    }
}