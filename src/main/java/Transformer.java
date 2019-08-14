import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.logging.Logger;

public class Transformer implements ClassFileTransformer {

    private Logger LOGGER = Logger.getLogger(Transformer.class.getName());
    /* The internal form class name of the class to transform */
    private String targetClassName;
    /* The class loader of the class we want to transform */
    private ClassLoader targetClassLoader;

    public Transformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //LOGGER.info("yo");
        return classfileBuffer;
    }
}
