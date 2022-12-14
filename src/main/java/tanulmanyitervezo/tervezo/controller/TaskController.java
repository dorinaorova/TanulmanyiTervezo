package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Task;
import tanulmanyitervezo.tervezo.services.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@RequestBody Task task, @PathVariable("id") int id){
        try {
            Task newTask = service.addTask(task, id);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található feladat!");
        }
    }

    @GetMapping("/findallbyuser/{id}")
    public ResponseEntity<List<Task>> findAll(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findbyid/{id}/{user_id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id, @PathVariable("user_id") int user_id){
        try {
            Optional<Task> task = service.findById(id, user_id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch(Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Nem található felhasználó!");
        }
    }

    @GetMapping("/findallbyuser/done/{id}")
    public ResponseEntity<List<Task>> findAllDone(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id_Done(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findallbyuser/undone/{id}")
    public ResponseEntity<List<Task>> findAllUndone(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id_Undone(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Task task){
        try {
            Task updatedTask = service.updateTask(task, id);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található feladat!");
        }
    }

    @PutMapping("/setdone/{id}")
    public ResponseEntity<?> setDone(@PathVariable("id") int id){
        try{
        Task updatedTask = service.setDoneTask(id);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található feladat!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") int id){
        try {
            service.deleteTask(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található feladat!");
        }
    }


}

