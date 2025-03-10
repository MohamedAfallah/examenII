package es.madrid.parla.tierno.parla;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerAplicacion {
    Logger logger;

    public LoggerAplicacion(String nombre, boolean append) {
        logger = Logger.getLogger(nombre);
        logger.setUseParentHandlers(false);

        try{
            FileHandler fh = new FileHandler(nombre + ".log", append);
            fh.setFormatter(new SimpleFormatter());

            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        }catch(IOException e){
            System.out.println("Error en la configuracion del log");
        }
    }

    public void log(Level level, String mensaje){
        logger.log(level, mensaje);
    }
}
