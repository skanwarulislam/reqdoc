package com.example.app.country.deserializer;

import static org.junit.Assert.assertTrue;

import com.example.app.country.model.Currency;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;

public class CurrencyDeserializerTest {

  @Test
  public void testCurrencyDeserializeWithValidJsonProperties() throws IOException {
    Currency expectedCurrency = new Currency("AFN", "Afghanistan");
    String countryAsJson = new String(
        this.getClass().getClassLoader().getResourceAsStream("countries.json").readAllBytes());
    Currency[] currencies = new ObjectMapper()
        .readValue(countryAsJson, Currency[].class);
    System.out.println(Arrays.stream(currencies).findFirst().get().equals(expectedCurrency));
    assertTrue("Not same ", Arrays.stream(currencies).findFirst().get().equals(expectedCurrency));
  }

  // @Test(expected = JsonMappingException.class)
  @Test(expected = JsonMappingException.class)
  public void testCurrencyDeserializeWithMissingJsonProperties() throws IOException {
    String countryAsJson = new String(
        this.getClass().getClassLoader().getResourceAsStream("missing_name_country.json")
            .readAllBytes());
    Currency[] currencies = new ObjectMapper()
        .readValue(countryAsJson, Currency[].class);
  }
}
