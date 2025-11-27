import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
        scanner.close();
    }

    private static void menuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== SISTEMA DE LEILÃO ===");
            System.out.println("1. Registrar Participante");
            System.out.println("2. Login Participante");
            System.out.println("3. Criar Leilão");
            System.out.println("4. Listar Participantes");
            System.out.println("5. Listar Leilões");
            System.out.println("6. Registrar Lance");
            System.out.println("7. Listar Lances");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    Participante.registrarInteractive(scanner);
                    break;
                case 2:
                    Participante.loginParticipante(scanner);
                    break;
                case 3:
                    Leilao.registrarLeilao(scanner);
                    break;
                case 4:
                    Participante.listarPtcp();
                    break;
                case 5:
                    listarLeiloes();
                    break;
                case 6:
                    Lance.registrarLance(scanner);
                    break;
                case 7:
                    listarLances();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

   


   

    // private static void listarLances() {
    //     System.out.println("\n--- LANCES REGISTRADOS ---");
    //     java.util.ArrayList<Lance> lances = Lance.listarFromFile();
    //     if (lances.isEmpty()) {
    //         System.out.println("Nenhum lance registrado.");
    //     } else {
    //         for (Lance ln : lances) {
    //             System.out.println("\n--- Lance ---");
    //             System.out.println("ID Lance: " + ln.getIdLance());
    //             System.out.println("Participante: " + ln.getParticipante().getNomeParticipante());
    //             System.out.println("Item: " + ln.getItemLeilao().getNomeItem());
    //             System.out.println("Valor: R$ " + ln.getValorLance());
    //             System.out.println("Data: " + ln.getDataLance());
    //             System.out.println("Horário: " + ln.getHorarioLance());
    //         }
    //     }
    // }
}