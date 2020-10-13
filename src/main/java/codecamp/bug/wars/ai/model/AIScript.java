package codecamp.bug.wars.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AIScript {

    private String name;
    private String script;

//    public AIScript(){}
//
//    public AIScript(String name, String script) {
//        this.name = name;
//        this.script = script;
//    }
//
//    @Override
//    public String toString() {
//        return "AIScript{" +
//                "name='" + name + '\'' +
//                ", script='" + script + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AIScript aiScript = (AIScript) o;
//        return Objects.equals(name, aiScript.name) &&
//                Objects.equals(script, aiScript.script);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, script);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getScript() {
//        return script;
//    }
//
//    public void setScript(String script) {
//        this.script = script;
//    }
}
