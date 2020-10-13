package codecamp.bug.wars.ai.controller;

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
    @Test
    public void createAIScript_ShouldReturnAIScriptWithIdAndOK(){
        // arrange
        AIScript fakeSavedScript = new AIScript(1L, "Meg", "jump jump");
        Mockito.when(mockAIService.saveAI(Mockito.any())).thenReturn(fakeSavedScript);

        // act
        AIScriptResponse response = aiController.createAIScript(new AIScript(null, "Meg", "jump jump"));

        // assert
        assertEquals(1L, response.getId());
        assertEquals("Meg", response.getName());
        assertEquals("jump jump", response.getScript());
        assertEquals(null, response.getError());
    }

    //save success
    //"An AI Script with that name already exists."
    //
    //"No AI Script name was assigned."
    //
    //"AI Script is required, cannot be empty."
}
