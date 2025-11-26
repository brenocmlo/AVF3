import java.util.ArrayList;

public class Lance {
    private String idLance;
    private Participante participante;
    private ItemLeilao itemLeilao;
    private double valorLance;
    private String dataLance;
    private String horarioLance;

    public Lance(String idLance, Participante participante, double valorLance, String horarioLance, String dataLance, ItemLeilao itemLeilao) {
        this.idLance = idLance;
        this.participante = participante;
        this.valorLance = valorLance;
        this.horarioLance = horarioLance;
        this.dataLance = dataLance;
        this.itemLeilao = itemLeilao;
    }

    public String getIdLance() {
        return idLance;
    }

    public void setIdLance(String idLance) {
        this.idLance = idLance;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public double getValorLance() {
        return valorLance;
    }

    public void setValorLance(double valorLance) {
        this.valorLance = valorLance;
    }

    public String getHorarioLance() {
        return horarioLance;
    }

    public void setHorarioLance(String horarioLance) {
        this.horarioLance = horarioLance;
    }

    public String getDataLance() {
        return dataLance;
    }

    public void setDataLance(String dataLance) {
        this.dataLance = dataLance;
    }

    public ItemLeilao getItemLeilao() {
        return itemLeilao;
    }

    public void setItemLeilao(ItemLeilao itemLeilao) {
        this.itemLeilao = itemLeilao;
    }

    public void mostrarLances(){
        System.out.println("ID Lance: " + idLance);
        System.out.println("Participante: " + participante.getNomeParticipante());
        System.out.println("Valor Lance: " + valorLance);
        System.out.println("Horario Lance: " + horarioLance);
        System.out.println("Data Lance: " + dataLance);
        System.out.println("Item Leilao: " + itemLeilao.getNomeItem());
    }
    public boolean registrarLance(ItemLeilao item, Participante participante, double valorLance) {
        if (valorLance >= item.getLanceMin()) {
            this.itemLeilao = item;
            this.participante = participante;
            this.valorLance = valorLance;
            return true; 
        } else {
            return false; 
        }
    }
    public ArrayList<Lance> listarLances(ArrayList<Lance> lances) {
        return lances;
    }
}
