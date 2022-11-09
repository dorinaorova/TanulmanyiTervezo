package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Task;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.Repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    @Autowired
    UserService userService;

    public Task addTask(Task task, int id){
        User user = userService.findById(id).get();
        task.setDone(false);
        task.setUser(user);
        return repository.save(task);
    }

    public Task findById(int id){
        Task task = repository.findById(id).get();
        return task;
    }

    public List<Task> findAllByUser_id(int id){
        return repository.findByUser_Id(id);
    }

    public List<Task> findAllByUser_id_Done(int id){
        List<Task> tasks = findAllByUser_id(id);
        ArrayList<Task> done = new ArrayList<>();
        for (Task t: tasks){
            if(t.isDone()) done.add(t);
        }
        return done;
    }
    public List<Task> findAllByUser_id_Undone(int id){
        List<Task> tasks = findAllByUser_id(id);
        ArrayList<Task> undone = new ArrayList<>();
        for (Task t: tasks){
            if(!t.isDone()) undone.add(t);
        }
        return undone;
    }

    public Task updateTask(Task task, int id){
        Task t= repository.findById(id).get();
        if(task.getDate()!=0) t.setDate(task.getDate());
        if(task.getDescription()!=null) t.setDescription(task.getDescription());
        if(task.getName()!=null) t.setName(task.getName());
        return repository.save(t);
    }

    public Task setDoneTask(int id){
        Task task = repository.findById(id).get();
        task.setDone(true);
        return repository.save(task);
    }

    public void deleteTask(int id){
        repository.deleteById(id);
    }

}
