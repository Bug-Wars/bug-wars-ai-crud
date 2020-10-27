package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
import org.springframework.http.HttpStatus;
import codecamp.bug.wars.ai.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RestController
public class AIController {
    private AIService aiService;

    public AIController(AIService service){
        aiService = service;
    }

    @GetMapping("/ai")
    public ResponseEntity<List<AIScript>> getAllAI() {
        return ResponseEntity.ok(aiService.getAllAI());
    }

    // Json mapper  sets up the key value pairs into a map
    // Then it uses reflection to create the object and then to populate it.
 @PostMapping("/ai")
 public ResponseEntity<AIScriptResponse> createAIScript(@RequestBody AIScript script){
        try {
            AIScript savedScript = aiService.saveAI(script);
            return ResponseEntity.ok(new AIScriptResponse(savedScript, null));
        } catch (InvalidInputException e){

            return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (NameUnavailableException e){

        return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.CONFLICT);
        }

 }

}
