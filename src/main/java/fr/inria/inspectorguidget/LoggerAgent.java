package fr.inria.inspectorguidget;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class LoggerAgent {

    private static Logger LOGGER = Logger.getLogger(LoggerAgent.class.getName());

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In premain method");
    }
}
