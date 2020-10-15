package codecamp.bug.wars.ai.service;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AIServiceTest {
    AiScriptRepository mockRepository;
    AIService service;

    @BeforeEach
    public void setup() {
        mockRepository = Mockito.mock(AiScriptRepository.class);
        service = new AIService(mockRepository);
    }

    @Test
    public void saveAI_nullInputShouldThrowError() {
        // assert
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            // act
            service.saveAI(null);
        });
        // assert
        assertEquals("AI Script is required, cannot be empty.", exception.getMessage());
    }

    @Test
    public void saveAI_nullScriptShouldThrowError() {
        // assert
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            // act
            service.saveAI(new AIScript(null, "Name", null));
        });
        // assert
        assertEquals("AI Script is required, cannot be empty.", exception.getMessage());
    }

    @Test
    public void saveAI_nullNameShouldThrowError() {
        // assert
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            // act
            service.saveAI(new AIScript(null, null, "jump jump"));
        });
        // assert
        assertEquals("No AI Script name was assigned.", exception.getMessage());
    }

    @Test
    public void saveAI_NameAlreadyExists_ShouldThrowError() {
        // assert
        NameUnavailableException exception = assertThrows(NameUnavailableException.class, () -> {
            // act
            service.saveAI(new AIScript(null, "Meg", "jump jump"));
        });
        // assert
        assertEquals("An AI Script with that name already exists.", exception.getMessage());
    }

}