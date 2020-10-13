package codecamp.bug.wars.ai.controller;

import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.service.AIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AIControllerTest {
    private AIService aiService;
    private AIController aiController;
    @BeforeEach
    public void setup(){
        aiService = Mockito.mock(AIService.class);
        aiController = new AIController(aiService);
    }
    @Test
    public void saveAIScript_ShouldReturnAIScriptWithIdAndOK(){
        // arrange
        AIScript expected = new AIScript(1L, "Meg", "jump jump");

        Mockito.when(aiService.saveAI(Mockito.any())).thenReturn(expected);
        System.out.println(aiService.saveAI(null));
        // act

        // assert

    }

    //save success
    //"An AI Script with that name already exists."
    //
    //"No AI Script name was assigned."
    //
    //"AI Script is required, cannot be empty."
}
