package main.model;

public class Login {
    //-----===| ATRIBUTOS |===-----//
    private Long cpf;
    private String nivelAcesso;

    //-----===| CONSTRUTOR |===-----//
    public Login() {}

    public Login(Long cpf, String nivelAcesso) {
        this.cpf = cpf;
        this.nivelAcesso = nivelAcesso;
    }

    //-----===| MÉTODOS |===-----//
    public Long getCpf(){
        return cpf;
    }
    public void setCpf(Long cpf){
        this.cpf = cpf;
    }

    public String getNivelAcesso(){
        return nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }
}