package Model;

import java.time.LocalDate;

public class Cliente extends Pessoa {

    private double limite;
    private String email;

    public Cliente() {
    }

    public Cliente(double limite, String email, int id, String nome, String cpf, LocalDate dt_nas) {
        super(id, nome, cpf, dt_nas);
        this.limite = limite;
        this.email = email;
    }

    public Cliente(double limite, String email, String nome, String cpf, LocalDate dt_nas) {
        super(nome, cpf, dt_nas);
        this.limite = limite;
        this.email = email;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "limite=" + limite + ", email=" + email + '}';
    }
    

}
