package codecamp.bug.wars.ai.repository;

import codecamp.bug.wars.ai.model.AIScript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiScriptRepository extends JpaRepository<AIScript, Long> {
    AIScript findByNameIgnoreCase(String name);
}

// To create a database repository you need 3 things

// From Spring Initializer you need to select
// - JPA Dependency
// - Database Driver  (MySql, PostGres, Oracle, etc.)
// - If you want In-memory Database (fake) then you get H2 too.

// 1. You need to make the Repository interface (this one)
// 2. Setup your model so it can be converted to a table Schema - @Entity
// 3. YOu need to setup a database!