package com.example.app.country.deserializer;

import static com.example.app.country.deserializer.JsonParserUtil.getStringValueFromNode;

import com.example.app.country.model.Currency;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CurrencyDeserializer extends StdDeserializer<Currency> {

  public CurrencyDeserializer() {
    super(Currency.class);
  }

  @Override
  public Currency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String name = getStringValueFromNode(node, "name");
    String currency = getStringValueFromNode(node, "defaultCurrency");
    return new Currency(currency, name);
  }


}
