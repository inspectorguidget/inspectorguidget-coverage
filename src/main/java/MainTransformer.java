import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class MainTransformer implements ClassFileTransformer {

    private Logger LOGGER = Logger.getLogger(MainTransformer.class.getName());
    private String targetClassName;
    private ClassLoader targetClassLoader;

    public MainTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        byte[] byteCode = classfileBuffer;
        String finalTargetClassName = this.targetClassName.replaceAll("\\.", "/");
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (className.equals(finalTargetClassName) && loader.equals(targetClassLoader)) {

            LOGGER.info("[Agent] Transforming class Main");
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName);
                CtMethod m = cc.getDeclaredMethod("sum");

                StringBuilder startBlock = new StringBuilder();
                startBlock.append("LOGGER.info(\"getting out of method sum\")");
                m.insertBefore(startBlock.toString());

                StringBuilder endBlock = new StringBuilder();
                endBlock.append("LOGGER.info(\"getting out of method sum\")");
                m.insertAfter(endBlock.toString());

                byteCode = cc.toBytecode();
                cc.detach();

            } catch (NotFoundException | CannotCompileException | IOException e) {
                LOGGER.severe("Exception" + e);
            }
        }
        return byteCode;
    }
}
