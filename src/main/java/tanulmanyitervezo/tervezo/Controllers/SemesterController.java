package tanulmanyitervezo.tervezo.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.Models.Semester;
import tanulmanyitervezo.tervezo.services.SemesterService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/semester")
@CrossOrigin(origins = "http://localhost:4200")
public class SemesterController {

    @Autowired
    SemesterService service;

    @GetMapping("/findall")
    public ResponseEntity<List<Semester>> findAll(){
        List<Semester> semesters = service.findAll();
        Collections.sort(semesters);
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Semester> add(@RequestBody Semester semester){
        Semester newSemester = service.addSemester(semester);
        return  new ResponseEntity<>(newSemester, HttpStatus.CREATED);
    }

    @GetMapping("/findcurrent")
    public ResponseEntity<Semester> findCurrent(){
        Semester current = service.findCurrent();
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @PutMapping("/setCurrent/{id}")
    public ResponseEntity<Semester> setCurrent(@PathVariable("id") int id){
        Semester current = service.setCurrent(id);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @GetMapping("/findnext/{id}")
    public ResponseEntity<Semester> findNext(@PathVariable("id") int id){
        Semester next = service.findNext(id);
        return new ResponseEntity<>(next, HttpStatus.OK);
    }

    @GetMapping("/findprevious/{id}")
    public ResponseEntity<Semester> findPrevious(@PathVariable("id") int id){
        Semester previous = service.findPrevious(id);
        return new ResponseEntity<>(previous, HttpStatus.OK);
    }

}
