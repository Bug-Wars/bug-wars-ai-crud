package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
import codecamp.bug.wars.ai.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AIController {
    private AIService aiService;
    public AIController(AIService service){
        aiService = service;
    }

    @GetMapping("/ai")
    public List<AIScript> getAllAI() {
        return aiService.getAllAI();
    }

    // Json mapper  sets up the key value pairs into a map
    // Then it uses reflection to create the object and then to populate it.
 @PostMapping("/ai")
 public AIScriptResponse createAIScript(@RequestBody AIScript script){
        try {
            AIScript savedScript = aiService.saveAI(script);
            return new AIScriptResponse(savedScript, null);
        } catch (InvalidInputException | NameUnavailableException e){
            return new AIScriptResponse(script, e.getMessage());
        }

 }

}
