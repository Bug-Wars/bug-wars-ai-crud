package codecamp.bug.wars.ai.service.compiler.components;

import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizerTest {
    Tokenizer tokenizer;

    @BeforeEach()
    public void setup(){
        tokenizer = new Tokenizer();
    }

    @Test
    public void tokenizer_shouldReturnEmptyListForNullScript(){
        assertEquals(Collections.emptyList(), tokenizer.tokenize(null));
    }

    @Test
    public void tokenizer_shouldReturnEmptyListForEmptyString(){
        assertEquals(Collections.emptyList(), tokenizer.tokenize(""));
    }

    @Test
    public void tokenizer_shouldReturnEmptyListForWhitespace(){
        assertEquals(Collections.emptyList(), tokenizer.tokenize("      \t      "));
    }

    @Test
    public void tokenizer_shouldReturnEmptyListForComment(){
        assertEquals(Collections.emptyList(), tokenizer.tokenize("#hello"));
    }

    @Test
    public void tokenizer_shouldReturnSingleToken(){
        String input = "      attack      ";
        assertEquals(Arrays.asList(new LineOfTokens(1, "attack")), tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldNotTokenizeComment(){
        String input = "      attack      #hello";
        assertEquals(Arrays.asList(new LineOfTokens(1, "attack")), tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnLineWithMultipleTokens(){
        String input = ":START \t goto START";
        List<LineOfTokens> expected = Arrays.asList(new LineOfTokens(1, ":START", "goto", "START"));
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnSlashSlashTAsToken(){
        String input = ":START \t goto START \\t";
        LineOfTokens expectedLine = new LineOfTokens(1, ":START", "goto", "START", "\\t");
        List<LineOfTokens> expected = Arrays.asList(expectedLine);
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldStripOuterWhitespaceAndReturnTokens(){
        String input = "\t  ifEnemy 5\t START  \t ";
        LineOfTokens expectedLine = new LineOfTokens(1, "ifEnemy", "5", "START");
        List<LineOfTokens> expected = Arrays.asList(expectedLine);
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnSeparateLineOfTokensForSeparateLines() {
        String input = "ifEnemy 5 START\ngoto START";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, "ifEnemy", "5", "START"),
                new LineOfTokens(2, "goto", "START")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnSeparateLineOfTokensForSeparateLinesWithWhitespace() {
        String input = "        ifEnemy 5 START     \n      \tgoto START        \t";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, "ifEnemy", "5", "START"),
                new LineOfTokens(2, "goto", "START")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnSeparateLineOfTokensForSeparateLinesWithComments() {
        String input = "        ifEnemy 5 START     \nturnLeft #ignore   \n   \tgoto START    #ignore this    \t";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, "ifEnemy", "5", "START"),
                new LineOfTokens(2, "turnLeft"),
                new LineOfTokens(3, "goto", "START")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldIgnoreMultipleEmptyLines() {
        String input = "        ifEnemy 5 START     \n \n   \t\t\n     \n turnLeft #ignore  \n\n \n   \tgoto START    #ignore this    \t";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, "ifEnemy", "5", "START"),
                new LineOfTokens(5, "turnLeft"),
                new LineOfTokens(8, "goto", "START")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldIgnoreMultipleCommentOnlyLines() {
        String input = "\t ifEnemy 5 START \n\n turnLeft #ignore \n\t#comment\t\n #comment2 \n\tgoto START\t#ignore this ";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, "ifEnemy", "5", "START"),
                new LineOfTokens(3, "turnLeft"),
                new LineOfTokens(6, "goto", "START")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }

    @Test
    public void tokenizer_shouldReturnCommandsOnOneLineInArray(){
        //arrange
        String input = ":START\t\tifEnemy ATTACK \\t\n\t\t\tifEnemy 10 PURSUE \\n\n        \tturnLeft\n        \tgoto START\n            #hello\n:ATTACK\t\tattack #\\test\\newtest\n\t\t\tgoto START\n            \n:PURSUE\t\tmove\n\t\t\tgoto START\n            \n            \n\n\n";
        List<LineOfTokens> expected = Arrays.asList(
                new LineOfTokens(1, Arrays.asList(":START", "ifEnemy", "ATTACK", "\\t")),
                new LineOfTokens(2, Arrays.asList("ifEnemy", "10", "PURSUE", "\\n")),
                new LineOfTokens(3, Arrays.asList("turnLeft")),
                new LineOfTokens(4, Arrays.asList("goto", "START")),
                new LineOfTokens(6, Arrays.asList(":ATTACK", "attack")),
                new LineOfTokens(7, Arrays.asList("goto", "START")),
                new LineOfTokens(9, Arrays.asList(":PURSUE", "move")),
                new LineOfTokens(10, Arrays.asList("goto", "START"))
        );

        //act
        List<LineOfTokens> result = tokenizer.tokenize(input);

        //assert
        assertEquals(expected, result);
    }


}
