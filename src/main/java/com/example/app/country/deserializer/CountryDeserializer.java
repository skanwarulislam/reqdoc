package com.example.app.country.deserializer;

import static com.example.app.country.deserializer.JsonParserUtil.getStringValueFromNode;

import com.example.app.country.model.Country;
import com.example.app.country.model.Document;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;

public class CountryDeserializer extends StdDeserializer<Country> {

  public CountryDeserializer() {
    super(Country.class);
  }

  @Override
  public Country deserialize(@NotNull JsonParser jsonParser,
      @NotNull DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String name = getStringValueFromNode(node, "name");
    String currency = getStringValueFromNode(node, "defaultCurrency");
    JsonNode options = node.get("options");
    int maxWithdrawalsInt = 0;
    if (options != null) {
      JsonNode maxWithdrawal = options.get("withdrawalMaximum");
      if (maxWithdrawal != null) {
        maxWithdrawalsInt = maxWithdrawal.asInt();
      }
    }
    JsonNode documents = node.get("documents");
    ArrayList<Document> requiredDocs = new ArrayList<>();
    if (documents != null && documents.isArray()) {
      documents.forEach(doc -> {
        int id = doc.get("id").asInt();
        String code = doc.get("code").asText();
        String description = doc.get("description").asText();
        boolean required = doc.get("required").asBoolean();
        Document document = new Document(id, code, description, required);
        requiredDocs.add(document);
      });
    }
    return new Country(name, currency, requiredDocs, maxWithdrawalsInt);
  }
}
