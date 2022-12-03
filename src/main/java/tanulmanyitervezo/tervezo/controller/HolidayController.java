package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Holiday;
import tanulmanyitervezo.tervezo.services.HolidayService;

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

    @GetMapping("/findbysemster/{id}")
    public ResponseEntity<?> findBySemester(@PathVariable("id") int id){
        try {
            List<Holiday> holidays = service.findBySemester(id);
            return new ResponseEntity<>(holidays, HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található félév");
        }
    }

    @PostMapping("add")
    public ResponseEntity<Holiday> add(@RequestBody Holiday holiday){
        Holiday newHoliday = service.addHoliday(holiday);
        return new ResponseEntity<>(newHoliday, HttpStatus.CREATED);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        try{
            service.deleteHoliday(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található ünnepnap");
        }
    }
    @GetMapping("/isholiday/{date}")
    public ResponseEntity<Boolean> isHoliday(@PathVariable("date") long date){
        Boolean result = service.isHoliday(date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
