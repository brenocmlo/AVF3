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
    
    public boolean registrarParticipante(String nome, String senha, String email, String login, String endereco, String telefone) {
        this.nomeParticipante = nome;
        this.senhaParticipante = senha;
        this.emailParticipante = email;
        this.loginParticipante = login;
        this.enderecoParticipante = endereco;
        this.telefoneParticipante = telefone;
        return true; 
    }
    public ArrayList<Participante> listarParticipantes(ArrayList<Participante> participantes) {
        return participantes;
    }
  
    public Participante loginParticipante(String login, String senha) {
        if (this.loginParticipante.equals(login) && this.senhaParticipante.equals(senha)) {
            return this;
        }
        return null;
    }
       
}
