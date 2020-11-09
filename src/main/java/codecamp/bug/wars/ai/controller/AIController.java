package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.exceptions.AIScriptNotFoundException;
import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.exceptions.ParseErrorException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
import codecamp.bug.wars.ai.model.CompiledScriptResponse;
import org.springframework.http.HttpStatus;
import codecamp.bug.wars.ai.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codecamp.bug.wars.ai.service.compiler.Compiler;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AIController {
    private AIService aiService;
    private Compiler compiler;

    public AIController(AIService service, Compiler comp) {
        aiService = service;
        compiler = comp;
    }

    @GetMapping("/ai/{id}")
    public ResponseEntity<AIScriptResponse> getAIById(@PathVariable Long id){
        try{
           AIScript retrieved = aiService.getAIById(id);
            return ResponseEntity.ok(new AIScriptResponse(retrieved, null));
        }catch(AIScriptNotFoundException e){
            return new ResponseEntity(new AIScriptResponse( null, e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/ai")
    public ResponseEntity<List<AIScript>> getAllAI() {
        return ResponseEntity.ok(aiService.getAllAI());
    }

    // Json mapper  sets up the key value pairs into a map
    // Then it uses reflection to create the object and then to populate it.
    @PostMapping("/ai")
    public ResponseEntity<AIScriptResponse> createAIScript(@RequestBody AIScript script) {
        try {
            AIScript savedScript = aiService.saveAI(script);
            return ResponseEntity.ok(new AIScriptResponse(savedScript, null));
        } catch (InvalidInputException e) {

            return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (NameUnavailableException e) {

            return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/ai/{id}")
    public ResponseEntity<AIScriptResponse> updateAIScript(@RequestBody AIScript script, @PathVariable Long id){
        try {
            AIScript updatedScript = aiService.updateAI(script, id);
            return ResponseEntity.ok(new AIScriptResponse(updatedScript, null));
        } catch (InvalidInputException e) {
            return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (NameUnavailableException e) {
            return new ResponseEntity(new AIScriptResponse(script, e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/ai/{id}/compile")
    public ResponseEntity<CompiledScriptResponse> compileAIScript(@PathVariable Long id){
        try {
            List<Integer> bytecode = compiler.compile(id);
            return ResponseEntity.ok(new CompiledScriptResponse(id, bytecode, null));
        } catch (AIScriptNotFoundException e) {
            return new ResponseEntity(new CompiledScriptResponse(id, null, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (ParseErrorException e) {
            return new ResponseEntity(new CompiledScriptResponse(id, null, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
