package fr.inria.inspectorguidget;

import spoon.decompiler.TypeTransformer;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtType;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class BinderTransformer implements TypeTransformer {

    private Logger LOGGER = Logger.getLogger(BinderTransformer.class.getName());

    public BinderTransformer() throws IOException {
        LOGGER.addHandler(new FileHandler("AgentLogger.log"));
    }

    @Override
    public boolean accept(CtType type) {

        if ((type instanceof CtInvocation) && type.getSimpleName().endsWith("bind()")){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void transform(CtType type) {
        //TODO : add call .log(LogLevel.BINDING) before .bind()
        LOGGER.info("Modifying invocation : " + type );
    }
}

