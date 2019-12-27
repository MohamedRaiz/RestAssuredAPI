package Log4JTraining;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {


    //So in order to use the Log4J jars, we need to define the manager here. In the brackets, we put the class name.
    private static Logger logger = LogManager.getLogger(Demo.class);
    public static void main(String[] args) {

        logger.debug("I am Debugging");
        int i = 2;

        if (i > 0) {
            logger.trace("This is Lvl 1");
            logger.info("This is lvl 2");
            logger.debug("This is lvl 3");
            logger.warn("This is lvl 4");
            logger.error("This is lvl 5");
            logger.fatal("This is lvl 6");
        }


    }
}
