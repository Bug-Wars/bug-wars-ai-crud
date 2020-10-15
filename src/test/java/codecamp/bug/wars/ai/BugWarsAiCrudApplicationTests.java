package codecamp.bug.wars.ai;

import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BugWarsAiCrudApplicationTests {

	@Autowired
	AiScriptRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void canFindRecordByName(){
		AIScript script1 = repository.save(new AIScript(null, "Meg", "jump jump"));
		AIScript script2 = repository.save(new AIScript(null, "Kellsey", "move move"));
		AIScript script3 = repository.save(new AIScript(null, "Lolita", "turnRight turnLeft"));
		System.out.println(script1);
		System.out.println(script2);
		System.out.println(script3);
		System.out.println("output:" +repository.findByNameIgnoreCase("Meg"));
		System.out.println("output:"+ repository.findByNameIgnoreCase("MEG"));
	}

}
