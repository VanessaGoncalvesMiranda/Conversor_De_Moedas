import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<MoedaEscolhida> conversoesSolicitadas = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        double valor = 0.00;

        List<MoedaEscolhida> conversoesSolicitadas = new ArrayList<>();
        HistoricoDeConversoes historicoDeConversoes = new HistoricoDeConversoes();


        while (opcao != 7) {
            try {

                System.out.println(
                        "************************************************\n" +
                                "Seja bem vindo (a) ao conversor de moeda.\n" +
                                "\n" +
                                "1) Dólar ==> Peso argentino\n" +
                                "2) Peso argentino ==> Dólar\n" +
                                "3) Dólar ==> Real brasileiro\n" +
                                "4) Real brasileiro ==> Dólar\n" +
                                "5) Dólar ==> Peso colombiano\n" +
                                "6) Peso colobiano ==> Dólar\n" +
                                "7) Sair\n" +
                                "Escolha uma opção válida: \n" +
                                "************************************************");

                opcao = scan.nextInt();

                switch (opcao) {

                    case 1:

                        consultarMoeda("USD", "ARS", scan);
                        break;

                    case 2:
                        consultarMoeda("ARS", "USD", scan);
                        break;
                    case 3:
                        consultarMoeda("USD", "BRL", scan);
                        break;
                    case 4:
                        consultarMoeda("BRL", "USD", scan);
                        break;
                    case 5:
                        consultarMoeda("USD", "COP", scan);
                        break;
                    case 6:
                        consultarMoeda("COP", "USD", scan);
                        break;

                    case 7:
                        System.out.println("Encerrando o programa...");
                        break;

                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número separado por vírgula.");
                scan.next();
            }
        }
        scan.close();
    }


    private static void consultarMoeda(String base_code, String target_code, Scanner scan) {
        ConsultarMoeda consultaMoeda = new ConsultarMoeda();
        MoedaEscolhida moedaEscolhida = consultaMoeda.buscaMoeda(base_code, target_code);
        double taxaDeConversao = moedaEscolhida.getConversionRate();

        System.out.println("Digite o valor que deseja converter: ");
        double valor = scan.nextDouble();


        double valorConvertido = (valor * taxaDeConversao);
        System.out.println("Valor " + valor + " " + moedaEscolhida.base_code() +
                " corresponde ao valor final " +
                "de ==> " + valorConvertido + " " + moedaEscolhida.target_code());


        conversoesSolicitadas.add(moedaEscolhida);
        HistoricoDeConversoes historicoDeConversoes = new HistoricoDeConversoes();
        historicoDeConversoes.salvarHistorico(conversoesSolicitadas);
    }
}