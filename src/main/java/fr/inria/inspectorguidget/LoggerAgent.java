package fr.inria.inspectorguidget;

import spoon.decompiler.SpoonClassFileTransformer;

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

            LOGGER.info("[Agent] Agent in premain method");

            SpoonClassFileTransformer transformer = new SpoonClassFileTransformer(cl -> cl.startsWith("net.sf.latexdraw"), new BinderTransformer(fh));
            inst.addTransformer(transformer);

        } catch (Exception e ){
            LOGGER.severe("can't log in file");
        }

        LOGGER.info("[Agent] Agent done");
    }
}



