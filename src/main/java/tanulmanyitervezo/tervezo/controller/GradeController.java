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
        GradeZH newGrade = service.addGrade_ZH(grade, id);
        return  new ResponseEntity<>(newGrade, HttpStatus.CREATED);
    }

    @PutMapping("/updategrade/zh/{id}")
    public ResponseEntity<GradeZH> updateGrade_ZH(@RequestBody GradeZH grade, @PathVariable("id") int id){
        GradeZH updatedGrade = service.updateGrade_ZH(grade, id);
        return  new ResponseEntity<>(updatedGrade, HttpStatus.OK);
    }

    @PostMapping("/addgrade/homework/{userId}")
    public ResponseEntity<GradeHomework> addGrade_Homework(@RequestBody GradeHomework grade, @PathVariable("userId") int id){
        GradeHomework newGrade = service.addGrade_Homework(grade, id);
        return  new ResponseEntity<>(newGrade, HttpStatus.CREATED);
    }

    @PutMapping("/updategrade/homework/{id}")
    public ResponseEntity<GradeHomework> updateGrade_Homework(@RequestBody GradeHomework grade, @PathVariable("id") int id){
        GradeHomework updatedGrade = service.updateGrade_Homework(grade, id);
        return  new ResponseEntity<>(updatedGrade, HttpStatus.OK);
    }
}
