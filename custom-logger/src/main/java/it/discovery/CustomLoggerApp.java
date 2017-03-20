package it.discovery;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 1) Разработать свой класс Handler, который сохраняет сообщения в формате JSON
 * и подключить его к JDK Logging. Для этого нужно унаследоваться от класса
 * Handler. Для примера можно взять класс FileHandler.
 *  2) Разработать свой
 * класс, который реализует интерфейс Log(Apache Commons Logging) и сохраняет
 * сообщения в JSON. Подключить его к Apache Commons Logging(как пример можно
 * взять SimpleLog).
 */
public class CustomLoggerApp {
	public static void main(String[] args) throws JsonProcessingException {

		Logger logger = Logger.getLogger(CustomLoggerApp.class.getName());
		Handler handler = new JsonHandler();
		logger.addHandler(handler);
		// remove default console handler from root logger
		Logger rootLogger = Logger.getLogger("");
		for (Handler h : rootLogger.getHandlers()) {
			if (h instanceof ConsoleHandler) {
				rootLogger.removeHandler(h);
			}
		}

		logger.info("Log message from custom logger");

	}
}
