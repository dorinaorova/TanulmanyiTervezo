package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.GradeHomework;
import tanulmanyitervezo.tervezo.model.GradeZH;
import tanulmanyitervezo.tervezo.model.ResponseModels.GradeResponseModel;
import tanulmanyitervezo.tervezo.services.GradeService;

import java.util.List;

@RestController
@RequestMapping("/grade")
@CrossOrigin(origins = "http://localhost:4200")
public class GradeController {
    @Autowired
    GradeService service;

    @GetMapping("/findallbyuser/{semesterId}/{userId}")
    public ResponseEntity<List<GradeResponseModel>> findAllByUser(@PathVariable("semesterId") int semesterId, @PathVariable("userId") int userId){
        List<GradeResponseModel> grades = service.findAllByUser_id(userId, semesterId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @PostMapping("/addgrade/zh/{userId}")
    public ResponseEntity<GradeZH> addGrade_ZH(@RequestBody GradeZH grade, @PathVariable("userId") int id){
        try {
            GradeZH newGrade = service.addGrade_ZH(grade, id);
            return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updategrade/zh/{id}")
    public ResponseEntity<GradeZH> updateGrade_ZH(@RequestBody GradeZH grade, @PathVariable("id") int id){
        try{
            GradeZH updatedGrade = service.updateGrade_ZH(grade, id);
            return  new ResponseEntity<>(updatedGrade, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addgrade/homework/{userId}")
    public ResponseEntity<GradeHomework> addGrade_Homework(@RequestBody GradeHomework grade, @PathVariable("userId") int id){
        try{
            GradeHomework newGrade = service.addGrade_Homework(grade, id);
            return  new ResponseEntity<>(newGrade, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updategrade/homework/{id}")
    public ResponseEntity<GradeHomework> updateGrade_Homework(@RequestBody GradeHomework grade, @PathVariable("id") int id){
        try{
            GradeHomework updatedGrade = service.updateGrade_Homework(grade, id);
            return  new ResponseEntity<>(updatedGrade, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
