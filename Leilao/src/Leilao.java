import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class Leilao {
    private String idLeilao;
    private String nomeLeilao;
    private String dataInicioLeilao;
    private String dataFimLeilao;
    private String horaInicioLeilao;
    private String horaFimLeilao;
    private boolean statusLeilao;
    private ArrayList<ItemLeilao> itensLeilao;
    

    public Leilao(String idLeilao, String nomeLeilao, String dataInicioLeilao, String dataFimLeilao, boolean statusLeilao) {
        this.idLeilao = idLeilao;
        this.nomeLeilao = nomeLeilao;
        this.dataInicioLeilao = dataInicioLeilao;
        this.dataFimLeilao = dataFimLeilao;
        this.statusLeilao = statusLeilao;
        this.itensLeilao = new ArrayList<>();
    
    }

    public String getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(String idLeilao) {
        this.idLeilao = idLeilao;
    }

    public String getNomeLeilao() {
        return nomeLeilao;
    }

    public void setNomeLeilao(String nomeLeilao) {
        this.nomeLeilao = nomeLeilao;
    }

    public String getDataInicioLeilao() {
        return dataInicioLeilao;
    }

    public void setDataInicioLeilao(String dataInicioLeilao) {
        this.dataInicioLeilao = dataInicioLeilao;
    }

    public String getDataFimLeilao() {
        return dataFimLeilao;
    }

    public void setDataFimLeilao(String dataFimLeilao) {
        this.dataFimLeilao = dataFimLeilao;
    }

    public String getHoraInicioLeilao() {
        return horaInicioLeilao;
    }

    public void setHoraInicioLeilao(String horaInicioLeilao) {
        this.horaInicioLeilao = horaInicioLeilao;
    }

    public String getHoraFimLeilao() {
        return horaFimLeilao;
    }

    public void setHoraFimLeilao(String horaFimLeilao) {
        this.horaFimLeilao = horaFimLeilao;
    }

    public boolean isStatusLeilao() {
        return statusLeilao;
    }

    public void setStatusLeilao(boolean statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    public void mostraInfoLeilao() {
        System.out.println("ID do Leilão: " + idLeilao);
        System.out.println("Nome do Leilão: " + nomeLeilao);
        System.out.println("Data de Início: " + dataInicioLeilao);
        System.out.println("Data de Fim: " + dataFimLeilao);
        System.out.println("Status do Leilão: " + (statusLeilao ? "Ativo" : "Inativo"));
    }

    public Leilao consultarLeilao() {
        return this;
    }

    public boolean registrarLeilao(String nome, String dataInicio, String dataFim) {
        this.nomeLeilao = nome;
        this.dataInicioLeilao = dataInicio;
        this.dataFimLeilao = dataFim;
        return true;
    }

    public boolean iniciarLeilao() {
        this.statusLeilao = true;
        return true;
    }

    public boolean finalizarLeilao() {
        this.statusLeilao = false;
        return true;
    }
   
    public boolean registrarLeilao(java.util.Scanner scanner) {
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
        try (java.io.FileWriter fw = new java.io.FileWriter("leiloes.txt", true);
             java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
            // formato: id,descricao,dataInicio,dataFim,horaInicio,horaFim,status
            bw.write(leilao.getIdLeilao() + "," + leilao.getNomeLeilao() + "," +
                    leilao.getDataInicioLeilao() + "," + leilao.getDataFimLeilao() + "," +
                    leilao.getHoraInicioLeilao() + "," + leilao.getHoraFimLeilao() + "," +
                    leilao.isStatusLeilao());
            bw.newLine();
            System.out.println("Leilão criado com sucesso!");
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar leilão: " + e.getMessage());
            return false;
        }
    }

    // renomeado para listarLeiloes (App espera esse nome)
    public  ArrayList<Leilao> listarLeiloes() {
        ArrayList<Leilao> leiloes = new ArrayList<>();
        try (java.io.FileReader fr = new java.io.FileReader("leiloes.txt");
             java.io.BufferedReader br = new java.io.BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 7) {
                    Leilao l = new Leilao(dados[0], dados[1], dados[2], dados[3], Boolean.parseBoolean(dados[6]));
                    l.setHoraInicioLeilao(dados[4]);
                    l.setHoraFimLeilao(dados[5]);
                    leiloes.add(l);
                } else if (dados.length >= 4) {
                    Leilao l = new Leilao(dados[0], "", dados[1], dados[2], Boolean.parseBoolean(dados[3]));
                    leiloes.add(l);
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // retorna lista vazia
        } catch (java.io.IOException e) {
            System.out.println("Erro ao carregar leilões: " + e.getMessage());
        }
        return leiloes;
    }

    public Leilao buscarPorId(String id) {
        for (Leilao l : listarLeiloes()) {
            if (l.getIdLeilao().equals(id)) return l;
        }
        return null;
    }
}
