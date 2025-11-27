import java.util.Scanner;

public class App {
    

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Participante p = new Participante("", "", "", "", "", "", "");
        Leilao l = new Leilao("", "", "", "", false);
        ItemLeilao il = new ItemLeilao("", "", "", 0.0, false, null);
        Lance lance = new Lance("", p, 0.0, "", "", il);

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
                    // corrigido nome do método
                    p.registrarParticipante(scanner);
                    break;
                case 2:
                    p.loginParticipante(scanner);
                    break;
                case 3:
                    l.registrarLeilao(scanner);
                    break;
                case 4:
                    // mostrar participantes lidos do arquivo
                    for (Participante pp : p.listarPtcp()) {
                        pp.mostrarParticipante();
                        System.out.println("-----------------");
                    }
                    break;
                case 5:
                    // método renomeado em Leilao
                    for (Leilao le : l.listarLeiloes()) {
                        le.mostraInfoLeilao();
                        System.out.println("-----------------");
                    }
                    break;
                case 6:
                    // agora é método de instância em Lance
                    lance.registrarLance(scanner);
                    break;
                case 7:
                    // listar e mostrar lances
                    for (Lance ln : lance.listarLances()) {
                        ln.mostrarLances();
                        System.out.println("-----------------");
                    }
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
       
    scanner.close();
    }

    
       
}
