package codecamp.bug.wars.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AIController {
    @GetMapping("/ai")
    public String getAllAI(){
        return "Hello";

    }
 @PostMapping("/ai")
 public String saveAI(){
        return "We save AI";
 }

}
