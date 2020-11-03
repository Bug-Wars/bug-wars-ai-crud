package codecamp.bug.wars.ai.service;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {
    private final AiScriptRepository repository;

    public AIService(AiScriptRepository repository) {
        this.repository = repository;
    }

    public AIScript saveAI(AIScript script) {
        validateScript(script);

        script.setName(script.getName().trim());
        AIScript dbScript = repository.findByNameIgnoreCase(script.getName());
        if (dbScript != null) {
            throw new NameUnavailableException("An AI Script with that name already exists.");
        }

        return repository.save(script);
    }

    public AIScript updateAI(AIScript script, Long id){
        validateScript(script);

        script.setName(script.getName().trim());
        script.setId(id);
        AIScript dbScript = repository.findByNameIgnoreCase(script.getName());
        if (dbScript != null && dbScript.getId() != id) {
            throw new NameUnavailableException("An AI Script with that name already exists.");
        }

        return repository.save(script);
    }

    public List<AIScript> getAllAI() {
        return repository.findAll();
    }

    private void validateScript(AIScript script){
        if (script == null || StringUtils.isBlank(script.getScript())) {
            throw new InvalidInputException("AI Script is required, cannot be empty.");
        } else if (StringUtils.isBlank(script.getName())) {
            throw new InvalidInputException("No AI Script name was assigned.");
        }
    }
}
