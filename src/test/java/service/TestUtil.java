package service;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Utility class for testing REST controllers.
 */
public final class TestUtil {

	public static byte[] toJson(Object object) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		// support Java 8 date time apis
		mapper.registerModule(new JavaTimeModule());
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}
