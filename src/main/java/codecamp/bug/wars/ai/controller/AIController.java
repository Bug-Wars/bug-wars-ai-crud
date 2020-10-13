package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.model.AIScript;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AIController {
    @GetMapping("/ai")
    public String getAllAI(){
        return "Hello";

    }
 @PostMapping("/ai")
 public String saveAI(@RequestBody AIScript script){
        return "save: " + script.toString();
 }

}
