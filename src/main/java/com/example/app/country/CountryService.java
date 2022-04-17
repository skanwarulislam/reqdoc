package com.example.app.country;

import com.example.app.country.client.RestClient;
import com.example.app.country.model.Country;
import com.example.app.country.model.Currency;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

public class CountryService {

  private final RestClient mRestClient = new RestClient();

  public ArrayList<Country> getCountries(String sort, String offset, String maxItems)
      throws Exception {
    Response response = mRestClient.getResponseFromCoinDirect(offset, maxItems);
    return convertResponseToJson(Objects.requireNonNull(response.body()).string(), sort);
  }

  public ArrayList<Currency> getCurrencies(String sort, String offset, String maxItems)
      throws IOException, URISyntaxException {
    Response response = mRestClient.getResponseFromCoinDirect(offset, maxItems);
    return convertResponseToCurrency(Objects.requireNonNull(response.body()).string());
  }


  /**
   * Converts json string to sorted <code>ArrayList<Country></code>ArrayList
   *
   * @param jsonAsString
   * @param sort
   * @return
   * @throws JsonProcessingException
   */

  private ArrayList<Country> convertResponseToJson(@NotEmpty String jsonAsString, String sort)
      throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    Country[] countries = objectMapper.readValue(jsonAsString, Country[].class);
    if (StringUtils.isNotEmpty(sort)) {
      String[] params = sort.split(":");
      if ("currency".equals(params[0]) && "desc".equals(params[1])) {
        return Arrays.stream(countries).sorted(new CurrencyComparator().reversed())
            .collect(Collectors.toCollection(ArrayList::new));
      }
      if ("currency".equals(params[0])) {
        return Arrays.stream(countries).sorted(new CurrencyComparator())
            .collect(Collectors.toCollection(ArrayList::new));
      }
      if ("desc".equals(params[1])) {
        return Arrays.stream(countries).sorted(Comparator.reverseOrder())
            .collect(Collectors.toCollection(ArrayList::new));
      }

    }
    return Arrays.stream(countries).sorted().collect(Collectors.toCollection(ArrayList::new));
  }

  private ArrayList<Currency> convertResponseToCurrency(@NotEmpty String jsonAsString)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    Currency[] currencies = objectMapper.readValue(jsonAsString, Currency[].class);
    return Arrays.stream(currencies).sorted().collect(Collectors.toCollection(ArrayList::new));
  }
}
