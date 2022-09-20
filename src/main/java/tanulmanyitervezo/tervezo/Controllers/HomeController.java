package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @GetMapping("/helo")
    public String helo(){
        return "ehhez be kellett jelentkezni";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
