package codecamp.bug.wars.ai.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIScriptTest {
    @Test
    public void firstTest(){
        AIScript script = new AIScript("john", "move");
        AIScript script2 = new AIScript();
        AIScript script3 = script;
        script2.setName("john");
        script2.setScript("move");
        System.out.println(script);
        System.out.println(script2);
        System.out.println(script3);
        System.out.println(script==script3);
        System.out.println(script==script2);
        System.out.println(script.equals(script2));
        System.out.println(script.equals(script3));
    }
}