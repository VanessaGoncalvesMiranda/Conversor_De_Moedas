import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoeda {

    public MoedaEscolhida buscaMoeda(String base_code, String target_code) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/a9fa3888e2d84b8b5839c737/pair/" + base_code + "/" + target_code))
                .build();
        //URI buscamoedaconverter = URI.create("https://v6.exchangerate-api.com/v6/a9fa3888e2d84b8b5839c737/pair/" + base_code + "/" + target_code);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Erro ao buscar a moeda. Código de status: " + response.statusCode());
            }

            return new Gson().fromJson(response.body(), MoedaEscolhida.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível converter para a moeda solicitada: " + e.getMessage(), e);

        }
    }
}