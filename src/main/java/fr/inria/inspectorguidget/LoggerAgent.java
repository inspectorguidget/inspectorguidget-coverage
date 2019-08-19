package fr.inria.inspectorguidget;

import com.sun.jdi.event.ExceptionEvent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerAgent {

    private static Logger LOGGER = Logger.getLogger(LoggerAgent.class.getName());

    public static void premain(String agentArgs, Instrumentation inst){

        try{
            FileHandler fh = new FileHandler("AgentLogger.log");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.ALL);
            LOGGER.addHandler(fh);
        } catch (Exception e ){
            LOGGER.severe("can't log in file");
        }

        LOGGER.info("[Agent] In premain method");
    }
}



