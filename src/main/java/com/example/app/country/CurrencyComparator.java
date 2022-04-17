package com.example.app.country;

import com.example.app.country.model.Country;
import java.util.Comparator;

public class CurrencyComparator implements Comparator<Country> {

  @Override
  public int compare(Country country1, Country country2) {
    return country1.getCurrency().compareTo(country2.getCurrency());
  }
}
