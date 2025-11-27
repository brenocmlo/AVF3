import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemLeilao {
    private String idItem;
    private String nomeItem;    
    private String descricaoItem;
    private double lanceMin;
    private boolean itenArrematado;
    private Lance lanceArrematante;
    private ArrayList<Lance> lances;

    public ItemLeilao(String idItem, String nomeItem, String descricaoItem, double lanceMin, boolean itenArrematado, Lance lanceArrematante) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.lanceMin = lanceMin;
        this.itenArrematado = itenArrematado;
        this.lanceArrematante = lanceArrematante;
        this.lances = new ArrayList<>();
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

    public boolean registrarItem(String nome, String descricao, double lanceMin) {
        this.nomeItem = nome;
        this.descricaoItem = descricao;
        this.lanceMin = lanceMin;
        return true;
    }


    public boolean registrarItem() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("ID do Item: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("ID inválido.");
                return false;
            }

            System.out.print("Nome do Item: ");
            String nome = sc.nextLine().trim();
            System.out.print("Descrição do Item: ");
            String descricao = sc.nextLine().trim();
            System.out.print("Lance mínimo (use ponto para decimal): ");
            String lanceStr = sc.nextLine().trim();
            double lance;
            try {
                lance = Double.parseDouble(lanceStr);
            } catch (NumberFormatException e) {
                System.out.println("Valor de lance mínimo inválido.");
                return false;
            }


            this.idItem = id;
            this.nomeItem = nome;
            this.descricaoItem = descricao;
            this.lanceMin = lance;


            try (FileWriter fw = new FileWriter("itens.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(this.idItem + "," + this.nomeItem + "," + this.descricaoItem + "," + this.lanceMin);
                bw.newLine();
            } catch (IOException ioe) {
                System.out.println("Erro ao salvar item: " + ioe.getMessage());
                return false;
            }

            System.out.println("Item registrado com sucesso!");
            return true;
        } finally {
        }
    }

    public void arrematar(Lance lance) {
        if (lance != null && lance.getValorLance() >= this.lanceMin) {
            this.lanceArrematante = lance;
            this.itenArrematado = true;
        }
    }

    public void adicionarLance(Lance lance) {
        if (lance != null) {
            this.lances.add(lance);
        }
    }

    public ArrayList<Lance> listarLances() {
        return this.lances;
    }

    /**
     * Lê o arquivo "itens.txt" e retorna todos os itens encontrados.
     * Suporta dois formatos de linha:
     * 1) com associação ao leilão: leilaoId,idItem,nome,descricao,lanceMin
     * 2) sem associação:          idItem,nome,descricao,lanceMin
     */
    public static ArrayList<ItemLeilao> listarItensLeilao() {
        ArrayList<ItemLeilao> itens = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("itens.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(",");
                try {
                    if (campos.length >= 5) {
                        // formato com id do leilão: leilaoId,idItem,nome,descricao,lanceMin
                        String idItem = campos[1].trim();
                        String nome = campos[2].trim();
                        String descricao = campos[3].trim();
                        double lanceMin = Double.parseDouble(campos[4].trim());
                        itens.add(new ItemLeilao(idItem, nome, descricao, lanceMin, false, null));
                    } else if (campos.length >= 4) {
                        // formato sem associação: idItem,nome,descricao,lanceMin
                        String idItem = campos[0].trim();
                        String nome = campos[1].trim();
                        String descricao = campos[2].trim();
                        double lanceMin = Double.parseDouble(campos[3].trim());
                        itens.add(new ItemLeilao(idItem, nome, descricao, lanceMin, false, null));
                    }
                 } catch (NumberFormatException nfe) {
                    // pular linha com lanceMin inválido
                    continue;
                }
            }
        }catch (IOException e) {
            System.out.println("Erro ao ler itens: " + e.getMessage());
        }
        return itens;
    }

    // busca um item por id nos itens.txt
    public static ItemLeilao buscarPorId(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("itens.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(",");
                try {
                    if (campos.length >= 5) {
                        // formato com leilaoId,idItem,nome,descricao,lanceMin
                        String idItem = campos[1].trim();
                        if (!idItem.equals(id)) continue;
                        String nome = campos[2].trim();
                        String descricao = campos[3].trim();
                        double lanceMin = Double.parseDouble(campos[4].trim());
                        return new ItemLeilao(idItem, nome, descricao, lanceMin, false, null);
                    } else if (campos.length >= 4) {
                        String idItem = campos[0].trim();
                        if (!idItem.equals(id)) continue;
                        String nome = campos[1].trim();
                        String descricao = campos[2].trim();
                        double lanceMin = Double.parseDouble(campos[3].trim());
                        return new ItemLeilao(idItem, nome, descricao, lanceMin, false, null);
                    }
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }
        } catch (IOException e) {
            // arquivo pode não existir
        }
        return null;
    }

    /**
     * Lê o arquivo de lances e retorna apenas os lances deste item (arraylist).
     */
    public ArrayList<Lance> listarLancesArquivo() {
        ArrayList<Lance> resultado = new ArrayList<>();
        // carregar participantes simples
        ArrayList<Participante> participantes = new ArrayList<>();
        try (BufferedReader brp = new BufferedReader(new FileReader("participantes.txt"))) {
            String lp;
            while ((lp = brp.readLine()) != null) {
                String[] dp = lp.split(",");
                if (dp.length >= 1) {
                    String pid = dp[0].trim();
                    String pnome = dp.length > 1 ? dp[1].trim() : pid;
                    String psenha = dp.length > 2 ? dp[2].trim() : "";
                    String pemail = dp.length > 3 ? dp[3].trim() : "";
                    String ptelefone = dp.length > 4 ? dp[4].trim() : "";
                    participantes.add(new Participante(pid, pnome, psenha, pemail, pnome, "", ptelefone));
                }
            }
        } catch (IOException e) {
            // ignore
        }

        try (BufferedReader br = new BufferedReader(new FileReader("lances.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 6) continue;
                String idItem = dados[2].trim();
                if (!idItem.equals(this.idItem)) continue;
                String idLance = dados[0].trim();
                String idParticipante = dados[1].trim();
                double valor;
                try { valor = Double.parseDouble(dados[3].trim()); } catch (NumberFormatException ex) { continue; }
                String data = dados[4].trim();
                String horario = dados[5].trim();

                Participante participante = null;
                for (Participante p : participantes) if (p.getIdParticipante().equals(idParticipante)) { participante = p; break; }
                if (participante == null) participante = new Participante(idParticipante, idParticipante, "", "", idParticipante, "", "");

                Lance lance = new Lance(idLance, participante, valor, horario, data, this);
                resultado.add(lance);
            }
        } catch (IOException e) {
            // ignore
        }
        return resultado;
    }
}