package it.discovery;

import java.io.PrintStream;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonHandler extends Handler {

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	private static PrintStream out = System.out;

	@Override
	public void publish(LogRecord record) {

		try {
			out.println(mapper.writeValueAsString(record));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub

	}

}
