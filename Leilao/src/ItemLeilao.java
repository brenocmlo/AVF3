public class ItemLeilao {
    private String idItem;
    private String nomeItem;    
    private String descricaoItem;
    private double lanceMin;
    private boolean itenArrematado;
    private Lance lanceArrematante;
public ItemLeilao(String idItem, String nomeItem, String descricaoItem, double lanceMin, boolean itenArrematado, Lance lanceArrematante) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.lanceMin = lanceMin;
        this.itenArrematado = itenArrematado;
        this.lanceArrematante = lanceArrematante;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public double getLanceMin() {
        return lanceMin;
    }

    public void setLanceMin(double lanceMin) {
        this.lanceMin = lanceMin;
    }

    public boolean isItenArrematado() {
        return itenArrematado;
    }

    public void setItenArrematado(boolean itenArrematado) {
        this.itenArrematado = itenArrematado;
    }

    public Lance getLanceArrematante() {
        return lanceArrematante;
    }

    public void setLanceArrematante(Lance lanceArrematante) {
        this.lanceArrematante = lanceArrematante;
    }
}
