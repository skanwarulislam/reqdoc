package com.example.app.country.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class RestClient {

  private static final String API_ENDPOINT = System.getenv("API_ENDPOINT");
  private static final String DEFAULT_URL = "https://api.coindirect.com/api/country";
  private final OkHttpClient mClient;

  public RestClient() {
    mClient = new OkHttpClient();
  }

  @NotNull
  public Response getResponseFromCoinDirect(String offset, String maxItems)
      throws URISyntaxException, IOException {

    URI uri = StringUtils.isEmpty(API_ENDPOINT) ? new URI(DEFAULT_URL) : new URI(API_ENDPOINT);
    System.out.println(uri.getHost() + uri.getPort() + uri.getPath());
    URL url = new HttpUrl.Builder()
        .scheme(uri.getScheme())
        .host(uri.getHost())
        .port(uri.getPort() == -1 ? 443 : uri.getPort())
        .addEncodedPathSegments(uri.getPath().substring(1))
        .addQueryParameter("enabled", "true")
        .addQueryParameter("offset", offset)
        .addQueryParameter("max", maxItems)
        .build().url();
    Request request = new Request.Builder()
        .url(url)
        .build();
    return mClient.newCall(request).execute();
  }

}
