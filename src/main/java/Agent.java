import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Agent {

    private static Logger LOGGER = Logger.getLogger(Agent.class.getName());

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In premain method");
        String className = "TestMath";

        try{
            Transformer transformer = new Transformer(className, Class.forName(className).getClassLoader());
            inst.addTransformer(transformer);
        }catch (Exception e){
            LOGGER.warning("error loading class");
        }

    }

}
