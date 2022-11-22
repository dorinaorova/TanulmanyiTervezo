package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.ResponseModels.TimetableResponseModel;
import tanulmanyitervezo.tervezo.services.TimetableService;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@CrossOrigin(origins = "http://localhost:4200")
public class TimetableController {
    @Autowired
    TimetableService service;

    @GetMapping("finddaily/{id}/{day}")
    public ResponseEntity<List<TimetableResponseModel>> findDailyTimeTable(@PathVariable("id") int id, @PathVariable("day") String day){
        List<TimetableResponseModel> timetable = service.findAllForDay(id, day);
        return new ResponseEntity<>(timetable, HttpStatus.OK);
    }
}
