package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.exceptions.InvalidLabelException;
import codecamp.bug.wars.ai.exceptions.ParseErrorException;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LabelValidatorTest {
    LabelValidator labelValidator;

    @BeforeEach
    public void setup(){
        labelValidator = new LabelValidator();
    }

    @Test
    public void mapLabels_shouldReturnEmptyListIfGivenEmptyList(){
        assertEquals(Collections.emptyList(), labelValidator.mapLabels(Collections.emptyList()));
    }

    @Test
    public void mapLabels_shouldReturnEmptyListIfGivenNull(){
        assertEquals(Collections.emptyList(), labelValidator.mapLabels(null));
    }

    @Test
    public void mapLabels_shouldReturnEmptyListIfNoLabels(){
        List<LineOfTokens> input = Arrays.asList(new LineOfTokens(1, ""));
        assertEquals(Collections.emptyList(), labelValidator.mapLabels(input));
    }

    @Test
    public void mapLabels_shouldReturnListWithSingleLabel(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, ":START")
        );
        List<String> expected = Arrays.asList("START");
        assertEquals(expected, labelValidator.mapLabels(input));
    }

    @Test
    public void mapLabels_shouldReturnListWithLabelFromLineWithMultipleCommands(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, ":START", "attack", "move")
        );
        List<String> expected = Arrays.asList("START");
        assertEquals(expected, labelValidator.mapLabels(input));
    }

    @Test
    public void mapLabels_shouldReturnListWithLabelsFromMultipleLines(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, ":START", "goto", "fight"),
                new LineOfTokens(2, ":FIGHT", "attack"),
                new LineOfTokens(3, ":PURSUE", "turnLeft", "move")
        );
        List<String> expected = Arrays.asList("START", "FIGHT", "PURSUE");
        assertEquals(expected, labelValidator.mapLabels(input));
    }

    @Test
    public void mapLabels_shouldThrowErrorNotAllCaps(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, ":START", "goto", "fight"),
                new LineOfTokens(2, ":fight", "attack"),
                new LineOfTokens(3, ":PURSUE", "turnLeft", "move")
        );

        InvalidLabelException exception = assertThrows(InvalidLabelException.class, () -> {
            // act
            labelValidator.mapLabels(input);
        });

        assertEquals("labels must only consist of capital letters and numbers", exception.getMessage());
    }
}