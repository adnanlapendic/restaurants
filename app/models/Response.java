package models;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by lapa on 5/6/17.
 */
public class Response {

    private boolean isError;

    private String message;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public Response(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static JsonNode succsessResponse(String message) {
       return objectMapper.valueToTree(new Response(false, message));
    }

    public static JsonNode errorResponse(String message) {
        return objectMapper.valueToTree(new Response(true, message));
    }
}
