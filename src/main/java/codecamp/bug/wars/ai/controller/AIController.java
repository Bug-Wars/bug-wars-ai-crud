package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AIController {
    private AIService aiService;
    public AIController(AIService service){
        aiService = service;
    }

    @GetMapping("/ai")
    public String getAllAI(){
        return "Hello";

    }
 @PostMapping("/ai")
 public String saveAIScript(@RequestBody AIScript script){
        aiService.saveAI(script);
        return "save: " + script.toString();
 }

}
