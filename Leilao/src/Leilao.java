import java.util.ArrayList;

public class Leilao {
    private String idLeilao;
    private String nomeLeilao;
    private String dataInicioLeilao;
    private String dataFimLeilao;
    private String horaInicioLeilao;
    private String horaFimLeilao;
    private boolean statusLeilao;
    private ArrayList<ItemLeilao> itensLeilao;
    private ArrayList<Lance> lancesLeilao;

    public Leilao(String idLeilao, String nomeLeilao, String dataInicioLeilao, String dataFimLeilao, boolean statusLeilao) {
        this.idLeilao = idLeilao;
        this.nomeLeilao = nomeLeilao;
        this.dataInicioLeilao = dataInicioLeilao;
        this.dataFimLeilao = dataFimLeilao;
        this.statusLeilao = statusLeilao;
        this.itensLeilao = new ArrayList<>();
        this.lancesLeilao = new ArrayList<>();
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

    public ArrayList<Leilao> listarLeiloes(ArrayList<Leilao> leiloes) {
        return leiloes;
    }

    public void adicionarItem(ItemLeilao item) {
        if (item != null) {
            this.itensLeilao.add(item);
        }
    }

    public void removerItem(ItemLeilao item) {
        this.itensLeilao.remove(item);
    }

    public ArrayList<ItemLeilao> listarItens() {
        return this.itensLeilao;
    }

    public void adicionarLance(Lance lance) {
        if (lance != null) {
            this.lancesLeilao.add(lance);
        }
    }

    public ArrayList<Lance> listarLances() {
        return this.lancesLeilao;
    }
}
