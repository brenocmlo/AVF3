import java.util.ArrayList;

public class Participante {
    private String idParticipante;
    private String nomeParticipante;
    private String senhaParticipante;
    private String emailParticipante;
    private String loginParticipante;
    private String enderecoParticipante;
    private String telefoneParticipante;

    public Participante(String idParticipante, String nomeParticipante, String senhaParticipante, String emailParticipante, String loginParticipante, String enderecoParticipante, String telefoneParticipante) {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.senhaParticipante = senhaParticipante;
        this.emailParticipante = emailParticipante;
        this.loginParticipante = loginParticipante;
        this.enderecoParticipante = enderecoParticipante;
        this.telefoneParticipante = telefoneParticipante;
    }

    public String getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(String idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public String getSenhaParticipante() {
        return senhaParticipante;
    }

    public void setSenhaParticipante(String senhaParticipante) {
        this.senhaParticipante = senhaParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }
    public void setEmailParticipante(String emailParticipante) {
        this.emailParticipante = emailParticipante;
    }
    public String getLoginParticipante() {
        return loginParticipante;
    }
    public void setLoginParticipante(String loginParticipante) {
        this.loginParticipante = loginParticipante;
    }
    public String getEnderecoParticipante() {
        return enderecoParticipante;
    }
    public void setEnderecoParticipante(String enderecoParticipante) {
        this.enderecoParticipante = enderecoParticipante;
    }
    public String getTelefoneParticipante() {
        return telefoneParticipante;
    }
    public void setTelefoneParticipante(String telefoneParticipante) {
        this.telefoneParticipante = telefoneParticipante;
    }

    public void mostrarParticipante() {
        System.out.println("ID: " + idParticipante);
        System.out.println("Nome: " + nomeParticipante);
        System.out.println("Email: " + emailParticipante);
        System.out.println("Login: " + loginParticipante);
        System.out.println("Endereço: " + enderecoParticipante);
        System.out.println("Telefone: " + telefoneParticipante);
    }      
    public static boolean registrarInteractive(java.util.Scanner scanner) {
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
        try (java.io.FileWriter fw = new java.io.FileWriter("participantes.txt", true);
             java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
            // salvar: id,nome,senha,email,telefone
            bw.write(participante.getIdParticipante() + "," + participante.getNomeParticipante() + "," +
                    participante.getSenhaParticipante() + "," + participante.getEmailParticipante() + "," +
                    participante.getTelefoneParticipante());
            bw.newLine();
            System.out.println("Participante registrado com sucesso!");
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar participante: " + e.getMessage());
            return false;
        }
    }
        public Participante loginParticipante(java.util.Scanner scanner) {
        System.out.println("\n--- LOGIN PARTICIPANTE ---");
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean ok = false;
        for (Participante p : Participante.listarPtcp()) {
            if (p.loginParticipante(usuario, senha) != null) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + p.getNomeParticipante());
                ok = true;
                break;
            }
        }
        if (!ok) System.out.println("Usuário ou senha inválidos!");
    }
    public ArrayList<Participante> listarPtcp() {
        ArrayList<Participante> participantes = new ArrayList<>();
        try (java.io.FileReader fr = new java.io.FileReader("participantes.txt");
             java.io.BufferedReader br = new java.io.BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 5) {
                    Participante p = new Participante(dados[0], dados[1], dados[2], dados[3], dados[1], "", dados[4]);
                    participantes.add(p);
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // retorna lista vazia
        } catch (java.io.IOException e) {
            System.out.println("Erro ao carregar participantes: " + e.getMessage());
        }
        return participantes;
    }

    public Participante buscarPorId(String id) {
        java.util.ArrayList<Participante> participantes = listarPtcp();
        for (Participante p : participantes) {
            if (p.getIdParticipante().equals(id)) return p;
        }
        return null;
    }
       
}
