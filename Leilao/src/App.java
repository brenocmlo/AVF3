import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static final String ARQUIVO_PARTICIPANTES = "participantes.txt";
    private static final String ARQUIVO_LEILOES = "leiloes.txt";
    private static final String ARQUIVO_LANCES = "lances.txt";
    
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Participante> participantes = new ArrayList<>();
    static ArrayList<Leilao> leiloes = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();
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
            System.out.println("6. Simular Leilão Completo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    registrarParticipante();
                    break;
                case 2:
                    loginParticipante();
                    break;
                case 3:
                    criarLeilao();
                    break;
                case 4:
                    listarParticipantes();
                    break;
                case 5:
                    listarLeiloes();
                    break;
                // case 6:
                //     simularLeilaoCompleto();
                //     break;
                case 0:
                    salvarDados();
                    continuar = false;
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void registrarParticipante() {
        System.out.println("\n--- REGISTRAR PARTICIPANTE ---");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Participante participante = new Participante(id, nome, senha, email, usuario, endereco, telefone);
        participantes.add(participante);
        salvarParticipante(participante);
        System.out.println("Participante registrado com sucesso!");
    }

    private static void loginParticipante() {
        System.out.println("\n--- LOGIN PARTICIPANTE ---");
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Participante p : participantes) {
            if (p.loginParticipante(usuario, senha) != null) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + p.getNomeParticipante());
                return;
            }
        }
        System.out.println("Usuário ou senha inválidos!");
    }

    private static void criarLeilao() {
        System.out.println("\n--- CRIAR LEILÃO ---");
        System.out.print("ID do Leilão: ");
        String id = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data Início (DD/MM/YYYY): ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data Fim (DD/MM/YYYY): ");
        String dataFim = scanner.nextLine();
        System.out.print("Hora Início (HH:MM): ");
        String horaInicio = scanner.nextLine();
        System.out.print("Hora Fim (HH:MM): ");
        String horaFim = scanner.nextLine();

        Leilao leilao = new Leilao(id, descricao, dataInicio, dataFim, false);
        leilao.setHoraInicioLeilao(horaInicio);
        leilao.setHoraFimLeilao(horaFim);
        leiloes.add(leilao);
        salvarLeilao(leilao);
        System.out.println("Leilão criado com sucesso!");
    }

    private static void listarParticipantes() {
        System.out.println("\n--- PARTICIPANTES REGISTRADOS ---");
        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante registrado.");
            return;
        }
        for (Participante p : participantes) {
            System.out.println("ID: " + p.getIdParticipante() + " | Nome: " + p.getNomeParticipante() + 
                             " | Email: " + p.getEmailParticipante() + " | Usuário: " + p.getNomeParticipante());
        }
    }

    private static void listarLeiloes() {
        System.out.println("\n--- LEILÕES REGISTRADOS ---");
        if (leiloes.isEmpty()) {
            System.out.println("Nenhum leilão registrado.");
            return;
        }
        for (Leilao l : leiloes) {
            System.out.println("ID: " + l.getIdLeilao() + " | Descrição: " + l.getDataInicioLeilao());
        }
    }
    
    private static void salvarParticipante(Participante p) {
        try (FileWriter fw = new FileWriter(ARQUIVO_PARTICIPANTES, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(p.getIdParticipante() + "," + p.getNomeParticipante() + "," + 
                    p.getEmailParticipante() + "," + p.getTelefoneParticipante());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar participante: " + e.getMessage());
        }
    }

    private static void salvarLeilao(Leilao l) {
        try (FileWriter fw = new FileWriter(ARQUIVO_LEILOES, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(l.getIdLeilao() + "," + l.getDataInicioLeilao() + "," + 
                    l.getDataFimLeilao() + "," + l.isStatusLeilao());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar leilão: " + e.getMessage());
        }
    }

    private static void salvarLance(Lance l) {
        try (FileWriter fw = new FileWriter(ARQUIVO_LANCES, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(l.getIdLance() + "," + l.getValorLance() + "," + 
                    l.getDataLance() + "," + l.getHorarioLance());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar lance: " + e.getMessage());
        }
    }

    
    private static void carregarDados() {
        carregarParticipantes();
        carregarLeiloes();
    }

    private static void carregarParticipantes() {
        try (FileReader fr = new FileReader(ARQUIVO_PARTICIPANTES);
             BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 4) {
                    Participante p = new Participante(dados[0], dados[1], "senha", dados[2], dados[1], "", dados[3]);
                    participantes.add(p);
                }
            }
            System.out.println("Participantes carregados: " + participantes.size());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de participantes não encontrado. Criando novo...");
        } catch (IOException e) {
            System.out.println("Erro ao carregar participantes: " + e.getMessage());
        }
    }

    private static void carregarLeiloes() {
        try (FileReader fr = new FileReader(ARQUIVO_LEILOES);
             BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 4) {
                    Leilao l = new Leilao(dados[0], "", dados[1], dados[2], Boolean.parseBoolean(dados[3]));
                    leiloes.add(l);
                }
            }
            System.out.println("Leilões carregados: " + leiloes.size());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de leilões não encontrado. Criando novo...");
        } catch (IOException e) {
            System.out.println("Erro ao carregar leilões: " + e.getMessage());
        }
    }

    private static void salvarDados() {
        System.out.println("Salvando dados...");
        
    }
}