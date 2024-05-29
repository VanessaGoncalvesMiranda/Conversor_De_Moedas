import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HistoricoDeConversoes {

    public void salvarHistorico(List<MoedaEscolhida> conversoesSolicitadas) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter escrita = new FileWriter("historicoDeConversoes.json");
            escrita.write(gson.toJson(conversoesSolicitadas));
            escrita.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}