package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
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
    public String getAllAI() {
        return "Hello";

    }

    // Json mapper  sets up the key value pairs into a map
    // Then it uses reflection to create the object and then to populate it.
 @PostMapping("/ai")
 public AIScriptResponse createAIScript(@RequestBody AIScript script){
        aiService.saveAI(script);
        return new AIScriptResponse(script, "Error message");
 }

}
