package utils;

import java.io.IOException;

import com.microsoft.playwright.APIResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiResponseMapper {

  public static JsonNode mapAPIResponse(APIResponse response) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readTree(response.body());
    } catch (IOException e) {
      throw new RuntimeException("Failed to map response",e);
    }
  }
}
