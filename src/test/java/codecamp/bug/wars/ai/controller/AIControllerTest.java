package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.model.AIScriptResponse;
import codecamp.bug.wars.ai.service.AIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void createAIScript_ShouldReturnAIScriptWithIdAndOK(){
        // arrange
        AIScript fakeSavedScript = new AIScript(1L, "Meg", "jump jump");
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenReturn(fakeSavedScript);

        // act
        AIScriptResponse response = aiController.createAIScript(new AIScript(null, "Meg", "jump jump"));

        // assert
        assertEquals(fakeSavedScript, response.getAi());
        assertEquals(null, response.getError());
    }

    @Test
    public void createAIScript_ShouldReturn400IfAIScriptRejectedByService(){
        // arrange
        AIScript input = new AIScript(null, null, "jump jump");
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenThrow(new InvalidInputException("Name cannot be null"));

        // act
        AIScriptResponse response = aiController.createAIScript(input);

        // assert
        assertEquals(input, response.getAi());
        assertEquals("Name cannot be null", response.getError());
    }

    //save success
    //"An AI Script with that name already exists."
    //
    //"No AI Script name was assigned."
    //
    //"AI Script is required, cannot be empty."
}
