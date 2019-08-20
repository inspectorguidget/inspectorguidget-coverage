package fr.inria.inspectorguidget;

import spoon.decompiler.TypeTransformer;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class BinderTransformer implements TypeTransformer {

    private Logger LOGGER = Logger.getLogger(BinderTransformer.class.getName());

    public BinderTransformer(FileHandler fh) {
        LOGGER.addHandler(fh);
        LOGGER.info("[Agent] building binder transformer");
    }

    @Override
    public boolean accept(CtType type) {
        LOGGER.info("[Agent] checking type : " + type );
        if ((type instanceof CtClass) &&
                type.getMethodsByName("configureBindings").size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void transform(CtType type) {
        //TODO : add call .log(LogLevel.BINDING) before .bind()
        LOGGER.info("[Agent] Modifying invocation : " + type.getSimpleName() );
    }
}

