package com.example.app.country.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Objects;

public class JsonParserUtil {

  public static String getStringValueFromNode(JsonNode baseNode, String property)
      throws IOException {
    JsonNode node = baseNode.get(property);
    if (Objects.isNull(node)) {
      throw new IOException(property + " property is missing.");
    }
    return node.asText();
  }
}
