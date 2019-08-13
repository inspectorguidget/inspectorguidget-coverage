import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Agent {

    private static Logger LOGGER = Logger.getLogger(Agent.class.getName());

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In premain method");

        String className = "Main";
        transformClass(className,inst);
    }


    private static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls;
        ClassLoader targetClassLoader;

        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception ex) {
            LOGGER.severe("Class [{}] not found with Class.forName");
        }
    }

    private static void transform (Class<?> clazz, ClassLoader classLoader, Instrumentation inst){
        MainTransformer mainTransformer = new MainTransformer(clazz.getName(), classLoader);
        inst.addTransformer(mainTransformer, true);
        try {
            inst.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Transform failed for: [" + clazz.getName() + "]", ex);
        }
    }

}
