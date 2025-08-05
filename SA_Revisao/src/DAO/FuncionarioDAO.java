package DAO;

import Conexao.Conexao;
import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {

    public static void create(Funcionario funcionario) throws SQLException {

        String sql = "INSERT INTO Funcionario(nome,dt_nascimento,cpf,salario,cargo ) values (?,?,?,?,?)";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

        LocalDate dt_nasc = funcionario.getDt_nas();

        ps.setString(1, funcionario.getNome());
        ps.setDate(2, java.sql.Date.valueOf(dt_nasc));
        ps.setString(3, funcionario.getCpf());
        ps.setDouble(4, funcionario.getSalario());
        ps.setString(5, funcionario.getCargo());

        ps.executeUpdate();
        ps.close();

    }

    public static void remove(int idFuncionario) throws SQLException {
        String sql = "DELETE FROM funcionario where id = ?";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ps.setInt(1, idFuncionario);

        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("funcionario deletado");
        }

        ps.close();

    }

    public static void getFuncionario() throws SQLException{
        String sql = "SELECT * FROM Funcionario";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            java.sql.Date dtNascimento = rs.getDate("dt_nascimento");
            String cpf = rs.getString("cpf");
            double salario = rs.getDouble("salario");
            String cargo = rs.getString("cargo");

            System.out.println("ID: " + id + ", Nome: " + nome + ", Data Nascimento: " + dtNascimento
                    + ", CPF: " + cpf + ", Salário: " + salario + ", Cargo: " + cargo);
        }

        rs.close();
        ps.close();

    }
      public static void getFuncionarioALL(int idFuncioanrio) throws SQLException{
        String sql = "SELECT * FROM Funcionario where id = ?";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ps.setInt(1, idFuncioanrio);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            java.sql.Date dtNascimento = rs.getDate("dt_nascimento");
            String cpf = rs.getString("cpf");
            double salario = rs.getDouble("salario");
            String cargo = rs.getString("cargo");

            System.out.println("ID: " + id + ", Nome: " + nome + ", Data Nascimento: " + dtNascimento
                    + ", CPF: " + cpf + ", Salário: " + salario + ", Cargo: " + cargo);
        }

        rs.close();
        ps.close();

    }
      
    
    public void AtualiarFuncionario(int id,String nome,double salario,String cargo) throws SQLException{
        StringBuilder sql = new StringBuilder("UPDATE funcionario SET");
        List<Object> params = new ArrayList<>();
        
        
        if(nome != null){
            sql.append("nome = ?, ");
            params.add(nome);
        }
        if(salario <=0){
            sql.append("salario = ?, ");
            params.add(salario);
        }
          if(cargo != null){
            sql.append("cargo = ?, ");
            params.add(cargo);
        }
        
        if(params.isEmpty()){
            System.out.println("Nenhum campo para atualizar.");
            return;
        }
        sql.setLength(sql.length() - 2);
        
        sql.append("WHERE ID = ?");
        params.add(id);
        
        try(PreparedStatement ps = Conexao.getConnection().prepareStatement(sql.toString())){
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i+1, params.get(i));
            }
            int linhas = ps.executeUpdate();
            System.out.println("Registros atualizados: "+linhas);
        }
    }
    

}
