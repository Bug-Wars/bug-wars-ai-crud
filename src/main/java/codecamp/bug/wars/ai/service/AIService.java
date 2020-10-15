package codecamp.bug.wars.ai.service;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    private final AiScriptRepository repository;


    public AIService(AiScriptRepository repository) {
         this.repository = repository;
    }

    public AIScript saveAI(AIScript script){
        if (script == null || script.getScript() == null){
            throw new InvalidInputException("AI Script is required, cannot be empty.");
        } else if (script.getName() == null){
            throw new InvalidInputException("No AI Script name was assigned.");
        }

        AIScript dbScript = repository.findByNameIgnoreCase(script.getName());
        if (dbScript != null){
            throw new NameUnavailableException("An AI Script with that name already exists.");
        }

        return null;
    }

    public List<AIScript> getAllAI(){
        return null;
    }
}
