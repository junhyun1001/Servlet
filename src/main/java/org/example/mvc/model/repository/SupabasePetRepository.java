package org.example.mvc.model.repository;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import org.example.mvc.model.entity.Pet;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class SupabasePetRepository implements PetRepository {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final String apiURL;
  private final String apiKey;

  public SupabasePetRepository() {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    apiURL = dotenv.get("SUPABASE_API_URL");
    apiKey = dotenv.get("SUPABASE_API_KEY");
  }

  @Override
  public List<Pet> findAll() {

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("%s/rest/v1/Pet?select=*".formatted(apiURL)))
        .GET()
        .header("apikey", apiKey)
        .header("Authorization", "Bearer %s".formatted(apiKey))
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
      return objectMapper.readValue(response.body(), new TypeReference<>() {
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void save(Pet pet) {
    String payload = objectMapper.writeValueAsString(pet);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("%s/rest/v1/Pet".formatted(apiURL)))
        .POST(HttpRequest.BodyPublishers.ofString(payload))
        .header("apikey", apiKey)
        .header("Authorization", "Bearer %s".formatted(apiKey))
        .header("Content-Type", "application/json")
        .header("Prefer", "return=minimal")
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
      System.out.println(response.statusCode());
      if (response.body().isBlank()) {
        System.out.println(response.body());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
