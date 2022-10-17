package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.Models.Holiday;
import tanulmanyitervezo.tervezo.services.HolidayService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/holiday")
@CrossOrigin(origins = "http://localhost:4200")
public class HolidayController {

    @Autowired
    HolidayService service;

    @GetMapping("/findall")
    public ResponseEntity<List<Holiday>> findAll(){
        List<Holiday> holidays = service.findallHoliday();
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @GetMapping("/findbydate")
    public ResponseEntity<List<Holiday>> findByDate(@RequestBody Date date){
        List<Holiday> holidays = service.findByDate(date);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Holiday> add(@RequestBody Holiday holiday){
        Holiday newHoliday = service.addHoliday(holiday);
        return new ResponseEntity<>(newHoliday, HttpStatus.CREATED);
    }
}