import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
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

    
    public static boolean registrarLance(java.util.Scanner scanner) {
        System.out.println("\n--- REGISTRAR LANCE ---");
        System.out.print("ID do Lance: ");
        String idLance = scanner.nextLine();
        System.out.print("ID do Participante: ");
        String idParticipante = scanner.nextLine();
        System.out.print("ID do Item: ");
        String idItem = scanner.nextLine();
        System.out.print("Valor do Lance: ");
        double valorLance = 0;
        try {
            valorLance = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return false;
        }
        System.out.print("Data do Lance (DD/MM/YYYY): ");
        String dataLance = scanner.nextLine();
        System.out.print("Horário do Lance (HH:MM): ");
        String horarioLance = scanner.nextLine();

        Participante participante = Participante.buscarPorId(idParticipante);
        ItemLeilao item = ItemLeilao.buscarPorId(idItem);

        if (participante == null) {
            System.out.println("Participante não encontrado!");
            return false;
        }
        if (item == null) {
            System.out.println("Item não encontrado!");
            return false;
        }

        Lance lance = new Lance(idLance, participante, valorLance, horarioLance, dataLance, item);
        if (!lance.registrarLance(item, participante, valorLance)) {
            System.out.println("Lance inválido! Valor abaixo do mínimo do item.");
            return false;
        }

        try (FileWriter fw = new FileWriter("lances.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(lance.getIdLance() + "," + lance.getParticipante().getIdParticipante() + "," +
                    lance.getItemLeilao().getIdItem() + "," + lance.getValorLance() + "," +
                    lance.getDataLance() + "," + lance.getHorarioLance());
            bw.newLine();
            System.out.println("Lance registrado com sucesso!");
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar lance: " + e.getMessage());
            return false;
        }
    }

    public  ArrayList<Lance> listarLance() {
        ArrayList<Lance> lista = new ArrayList<>();
        try (java.io.FileReader fr = new java.io.FileReader("lances.txt");
             java.io.BufferedReader br = new java.io.BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(",");
                if (d.length >= 6) {
                    String idLance = d[0].trim();
                    String idParticipante = d[1].trim();
                    String idItem = d[2].trim();
                    double valor = Double.parseDouble(d[3].trim());
                    String data = d[4].trim();
                    String horario = d[5].trim();
                    Participante p = Participante.buscarPorId(idParticipante);
                    ItemLeilao item = ItemLeilao.buscarPorId(idItem);
                    if (p == null) p = new Participante(idParticipante, idParticipante, "", "", idParticipante, "", "");
                    if (item == null) item = new ItemLeilao(idItem, idItem, "", 0.0, false, null);
                    lista.add(new Lance(idLance, p, valor, horario, data, item));
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // nenhum lance ainda
        } catch (java.io.IOException e) {
            System.out.println("Erro ao carregar lances: " + e.getMessage());
        }
        return lista;
    }
}
