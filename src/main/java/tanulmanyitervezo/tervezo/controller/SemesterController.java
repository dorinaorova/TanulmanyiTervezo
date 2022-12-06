package tanulmanyitervezo.tervezo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.services.SemesterService;

import java.util.Optional;

@RestController
@RequestMapping("/semester")
@CrossOrigin(origins = "http://localhost:4200")
public class SemesterController {

    @Autowired
    SemesterService service;

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

    @PutMapping("/setcurrent/{id}")
    public ResponseEntity<?> setCurrent(@PathVariable("id") int id){
        try {
            Semester current = service.setCurrent(id);
            return new ResponseEntity<>(current, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található félév");
        }
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

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Optional<Semester>> findById(@PathVariable("id") int id){
        Optional<Semester> semester = service.findById(id);
        return new ResponseEntity<>(semester, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Semester semester){
        try{
            Semester updated = service.update(id, semester);
            return  new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található félév");
        }
    }

}
