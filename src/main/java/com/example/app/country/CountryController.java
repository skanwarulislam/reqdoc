package com.example.app.country;

import com.example.app.country.model.Country;
import com.example.app.country.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

public class CountryController {

  private static final CountryService mCountryService = new CountryService();

  public static void ListCountries(Context ctx) {
    String sort = ctx.queryParam("sort");
    if (StringUtils.isNotEmpty(sort) && (!sort.startsWith("name") && !sort
        .startsWith("currency"))) {
      ctx.status(HttpStatus.SC_BAD_REQUEST);
      ctx.json(
          "{\"message\": \"Sort query parameter value is not valid, try <filed>:<sortOrder>\"}");
      return;
    }
    Integer offset = ctx.queryParamAsClass("offset", Integer.class)
        .getOrDefault(0); // validate value
    Integer maxItems = ctx.queryParamAsClass("max", Integer.class)
        .getOrDefault(10); // validate v alue
    ArrayList<Country> countries;
    try {
      countries = mCountryService.getCountries(sort, offset.toString(), maxItems.toString());
      ObjectMapper mapper = new ObjectMapper();
      ctx.contentType(ContentType.APPLICATION_JSON);
      ctx.status(HttpStatus.SC_OK);
      ctx.result(mapper.writeValueAsString(countries));
    } catch (Exception exception) {
      ctx.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      ctx.json(exception.getMessage());
    }

  }

  public static void ListCurrencyByCountry(Context ctx) throws Exception {
    ArrayList<Currency> currencies = mCountryService.getCurrencies("currency:asc", "0", "400");
    ObjectMapper mapper = new ObjectMapper();
    ctx.contentType(ContentType.APPLICATION_JSON);
    ctx.status(HttpStatus.SC_OK);
    ctx.result(mapper.writeValueAsString(currencies));
  }
}
