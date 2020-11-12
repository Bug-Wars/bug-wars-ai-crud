package codecamp.bug.wars.ai.service.compiler.components;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    Parser parser;

    @BeforeEach
    public void setup() { parser = new Parser(); }

    @Test
    public void parser_shouldReturnEmptyListIfInputEmpty(){
        assertEquals(Collections.emptyList(), parser.parse(Collections.emptyList()));
    }

    @Test
    public void parser_shouldReturnEmptyListIfInputNull(){
        assertEquals(Collections.emptyList(), parser.parse(null));
    }

}