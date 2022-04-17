package com.example.app;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.app.country.CountryController;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import org.apache.http.HttpStatus;
import org.junit.Test;

public class CountryControllerTest {

  private Context ctx = mock(Context.class);

  @Test
  public void country_list_with_user_given_query_parameters() throws Exception {
    when(ctx.queryParam("sort")).thenReturn("name:asc");
    when(ctx.queryParamAsClass("offset", Integer.class))
        .thenReturn(Validator.create(Integer.class, "0", "offset"));
    when(ctx.queryParamAsClass("max", Integer.class))
        .thenReturn(Validator.create(Integer.class, "15", "max"));
    CountryController.ListCountries(ctx);
    verify(ctx).status(HttpStatus.SC_OK);
  }

  @Test
  public void country_list_with_invalid_sort_value_params() throws Exception {
    when(ctx.queryParam("sort")).thenReturn("foo:asc");
    CountryController.ListCountries(ctx);
    verify(ctx).status(HttpStatus.SC_BAD_REQUEST);
  }


  @Test
  public void country_list_with_default_param_values() throws Exception {
    when(ctx.queryParam("sort")).thenReturn(null);
    when(ctx.queryParamAsClass("offset", Integer.class))
        .thenReturn(Validator.create(Integer.class, null, "offset"));
    when(ctx.queryParamAsClass("max", Integer.class))
        .thenReturn(Validator.create(Integer.class, null, "max"));
    CountryController.ListCountries(ctx);
    verify(ctx).status(HttpStatus.SC_OK);

  }


}
