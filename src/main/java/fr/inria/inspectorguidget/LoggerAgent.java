package fr.inria.inspectorguidget;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class LoggerAgent {

    private static Logger LOGGER = Logger.getLogger(LoggerAgent.class.getName());

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("TESTING AGENT WITH PRINTLN TO SEE IF IT'S LAUNCHED IN TEST PROJECT");
        //LOGGER.info("[Agent] In premain method");
    }
}
