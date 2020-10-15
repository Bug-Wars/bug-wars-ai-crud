package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.NameUnavailableException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
import codecamp.bug.wars.ai.service.AIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit Test
// We ISOLATE the class that we're testing.
// Which means we test the REAL VERSION of AIController
// BUT EVERYTHING AROUND IT IS FAKE
public class AIControllerTest {
    private AIService mockAIService;
    private AIController aiController;
    @BeforeEach
    public void setup(){
        mockAIService = Mockito.mock(AIService.class);
        aiController = new AIController(mockAIService);
    }

    //Service will have 3 different responses
    // 1. It was Successful at saving and returns saved AIScript
    // 2. It was Not Successful and throws an InvalidInputException. Error 400
    // 3. It was Not Successful and throws a NameUnavailableException. Error 409

    @Test
    public void createAIScript_ShouldReturnAIScriptWithIdAndOKHttpStatus(){
        // arrange
        AIScript postBodyInput = new AIScript(null, "Meg", "jump jump");
        AIScript savedScript = new AIScript(1L, "Meg", "jump jump");
        AIScriptResponse expected = new AIScriptResponse(savedScript, null);
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenReturn(savedScript);

        // act
        AIScriptResponse response = aiController.createAIScript(postBodyInput);

        // assert
        assertEquals(expected, response);
    }

    @Test
    public void createAIScript_ShouldReturn400IfAIScriptRejectedByService(){
        // arrange
        AIScript postBodyInput = new AIScript(null, null, "jump jump");
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenThrow(new InvalidInputException("Name cannot be null"));

        // act
        AIScriptResponse response = aiController.createAIScript(postBodyInput);

        // assert
        assertEquals(postBodyInput, response.getAi());
        assertEquals("Name cannot be null", response.getError());
    }

    @Test
    public void createAIScript_ShouldReturn409IfServiceThrowsNameNotAvailable(){
        // arrange
        AIScript postBodyInput = new AIScript(null, "John", "jump jump");
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenThrow(new NameUnavailableException("Name not available"));

        // act
        AIScriptResponse response = aiController.createAIScript(postBodyInput);

        // assert
        assertEquals(postBodyInput, response.getAi());
        assertEquals("Name not available", response.getError());
    }

    // 2 responses
    // 1. empty list
    // 2. list with scripts

    @Test
    public void getAllAI_ShouldReturnEmptyListIfEmpty(){
        // arrange
        List<AIScript> expected = new ArrayList<>();
        Mockito.when(mockAIService.getAllAI()).thenReturn(expected);

        // act
        List<AIScript> response = aiController.getAllAI();

        // assert
        assertEquals(response, expected);
    }

    @Test
    public void getAllAI_ShouldReturnListOfAIScripts(){
        // arrange
        List<AIScript> expected = new ArrayList<>();
        expected.add(new AIScript(1L, "Meg", "jump jump"));
        expected.add(new AIScript(2L, "Kellsey", "move"));
        Mockito.when(mockAIService.getAllAI()).thenReturn(expected);

        // act
        List<AIScript> response = aiController.getAllAI();

        // assert
        assertEquals(response, expected);

    }

    //save success
    //"An AI Script with that name already exists."
    //
    //"No AI Script name was assigned."
    //
    //"AI Script is required, cannot be empty."
}
