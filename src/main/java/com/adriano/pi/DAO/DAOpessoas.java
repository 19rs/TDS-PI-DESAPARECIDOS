package com.adriano.pi.DAO;

import com.adriano.pi.model.ModelPessoas;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DAOpessoas {

    // variavel de datasource para a conexão
    private DataSource dataSource;

    // método construtor para passar o dataSource
    public DAOpessoas(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // inserção de dados
    public void inserir(ModelPessoas m) {

        // conexão com o BD
        Connection con = dataSource.getConnection();

        // prepara para enviar o comando ao BD
        PreparedStatement ps = null;

        try {
            String SQL = "INSERT INTO Pessoas (nome,CPF,DataNascimento,Idade,Altura,Peso,Residente,Caracteristicas) VALUES (?,?,?,?,?,?,?,?)";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = con.prepareStatement(SQL);
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCpf());
            ps.setString(3, m.getDataNascimento());
            ps.setString(4, m.getIdade());
            ps.setString(5, m.getAtura());
            ps.setString(6, m.getPeso());
            ps.setString(7, m.getResidente());
            ps.setString(8, m.getCaracteristicas());

            // executa a inserção no banco
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao salvar!\n" + ex);
        } finally {
            // fecha o statement e o datasource
            //ps.close();
            dataSource.closeDataSource();
        }
    }

    public ArrayList<ModelPessoas> ConsultaUm(String descricao) {

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try {
            String SQL = "SELECT * from Pessoas where nome like ? ";
            //SELECT p.*, f.nomeFornecedor FROM Produtos p, fornecedores f WHERE (NomeFornecedor LIKE ?) and (p.idFornecedor=f.idFornecedor)"

            // para mandar como uma instrução, precisa usar o Prepared Statement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + descricao + "%");

            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();

            // cria a lista de resultados trazidos da tabela
            ArrayList<ModelPessoas> lista = new ArrayList<ModelPessoas>();

            while (rs.next()) {
                // instancia um objeto
                ModelPessoas cidade = new ModelPessoas();

                // joga dados da lista para o objeto
                cidade.setNome(rs.getString("Nome"));
                cidade.setCpf(rs.getString("CPF"));
                cidade.setDataNascimento(rs.getString("DataNascimento"));
                cidade.setIdade(rs.getString("Idade"));
                cidade.setAtura(rs.getString("Altura"));
                cidade.setPeso(rs.getString("Peso"));
                cidade.setResidente(rs.getString("Residente"));
                cidade.setCaracteristicas(rs.getString("Caracteristicas"));

                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(cidade);
            }

            // fecha o statement e o datasource
            ps.close();
            dataSource.closeDataSource();

            // retorna os dados consultados
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar dados " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral " + ex.getMessage());
        }

        // fecha o statement e o datasource
        dataSource.closeDataSource();

        // caso aconteça alguma coisa
        return null;
    }

    public void removerPeloNome(String nome) {

            // faz a conexão
        Connection con = dataSource.getConnection();

        // prepara para executar no BD
        PreparedStatement ps = null;

        try {
            String SQL = "DELETE FROM Pessoas WHERE (Nome = ?)";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = con.prepareStatement(SQL);
            ps.setString(1, nome);

            // executa a inserção no banco
            ps.executeUpdate();
            ps.close();

           // JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir!\n" + ex);
        } finally {
            // fecha o statement e o datasource
            dataSource.closeDataSource();
        }
    }

}
