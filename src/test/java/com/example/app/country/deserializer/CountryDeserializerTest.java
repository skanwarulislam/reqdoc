package com.example.app.country.deserializer;

import static org.junit.Assert.assertTrue;

import com.example.app.country.model.Country;
import com.example.app.country.model.Document;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class CountryDeserializerTest {

  @Test
  public void testCountryDeserializeWithValidJsonProperties() throws IOException {
    ArrayList<Document> documents = new ArrayList(
        Arrays.asList(new Document(157, "idPassport", "\"ID or Passport", true),
            new Document(158, "idSelfie", "ID Selfie", true),
            new Document(159, "proofOfAddress", "Proof of Address", true)));
    Country expectedCountry = new Country("Afghanistan", "AFN", documents, 1275000);
    String countryAsJson = new String(
        this.getClass().getClassLoader().getResourceAsStream("countries.json").readAllBytes());
    Country[] countries = new ObjectMapper()
        .readValue(countryAsJson, Country[].class);
    assertTrue("Not same country",
        Arrays.stream(countries).findFirst().get().equals(expectedCountry));
  }

  // @Test(expected = JsonMappingException.class)
  @Test(expected = JsonMappingException.class)
  public void testCountryDeserializeWithMissingJsonProperties() throws IOException {
    String countryAsJson = new String(
        this.getClass().getClassLoader().getResourceAsStream("missing_name_country.json")
            .readAllBytes());
    Country[] countries = new ObjectMapper()
        .readValue(countryAsJson, Country[].class);
    System.out.println(Arrays.stream(countries).findFirst().get().getName());
  }

}
