package com.example.app;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

import com.example.app.country.CountryController;
import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;


public class ReqDoc {

  public static void main(String[] args) {
    Javalin.create(config -> {
      config.registerPlugin(getConfiguredOpenApiPlugin());
      config.defaultContentType = "application/json";
      config.enableDevLogging();
      config.enableCorsForAllOrigins();
    }).routes(() -> path("v1/country", () -> {
      get(CountryController::ListCountries);
      path("/currency", () -> {
        get(CountryController::ListCurrencyByCountry);
      });
      path("/list", () -> {
        get(CountryController::ListCountries);
      });
    })).start(7070);
  }

  private static OpenApiPlugin getConfiguredOpenApiPlugin() {
    Info info = new Info().version("1.0").description("User API");
    OpenApiOptions options = new OpenApiOptions(info)
        .activateAnnotationScanningFor("com.example.app")
        .path("/swagger-docs") // endpoint for OpenAPI json
        .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
        .reDoc(new ReDocOptions("/reqdoc")) // endpoint for redoc
        .defaultDocumentation(doc -> {
          doc.json("500", ErrorResponse.class);
          doc.json("503", ErrorResponse.class);
        });
    return new OpenApiPlugin(options);
  }
}
