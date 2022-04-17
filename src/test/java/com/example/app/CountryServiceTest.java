package com.example.app;

import static org.junit.Assert.assertEquals;

import com.example.app.country.CountryService;
import com.example.app.country.model.Country;
import java.util.ArrayList;
import org.junit.Test;

public class CountryServiceTest {

  private final CountryService mCountryService = new CountryService();

  @Test
  public void test_get_getCountries_ascending_order() throws Exception {
    ArrayList<Country> countries = mCountryService.getCountries("name:asc", "0", "5");
    assertEquals("Number of returned counties are not same", countries.size(), 5);
    //We can compare the whole object but for now just checking the country name.
    assertEquals("First country need to be Afghanistan", "Afghanistan", countries.get(0).getName());
  }

  @Test
  public void test_get_getCountries_with_valid_arguments() throws Exception {
    ArrayList<Country> countries = mCountryService.getCountries("name:desc", "0", "10");
    assertEquals("Number of returned counties are not same", countries.size(), 10);
    assertEquals("First country need to be Argentina", "Argentina", countries.get(0).getName());

  }

  @Test
  public void test_get_getCountries_by_currency_in_ascending_order() throws Exception {
    ArrayList<Country> countries = mCountryService.getCountries("currency:asc", "0", "10");
    assertEquals("Number of returned counties are not same", countries.size(), 10);
    System.out.println();
    assertEquals("First country need to be Argentina", "Afghanistan", countries.get(0).getName());
    assertEquals("First country currency should be null ", "AFN", countries.get(0).getCurrency());

  }

  @Test
  public void test_get_getCountries_by_currency_in_descending_order() throws Exception {
    ArrayList<Country> countries = mCountryService.getCountries("currency:desc", "0", "10");
    assertEquals("Number of returned counties are not same", countries.size(), 10);
    System.out.println();
    assertEquals("First country need to be Argentina", "Albania", countries.get(0).getName());
    // This is weird to test null as string which we get from backend may be change the deserializer to interpret all this
    // null string values as N/A?
    assertEquals("First country currency should be null ", "null", countries.get(0).getCurrency());

  }
}
