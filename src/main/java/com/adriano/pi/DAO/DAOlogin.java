/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adriano.pi.DAO;

import com.adriano.pi.model.ModelLogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author adR
 */
public class DAOlogin {

    // variavel de datasource para a conexão
    private DataSource dataSource;

    // método construtor para passar o dataSource
    public DAOlogin(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<ModelLogin> Consulta() {

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try {
            String SQL = "SELECT * FROM Login";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);

            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();

            // cria a lista de resultados trazidos da tabela
            ArrayList<ModelLogin> lista = new ArrayList<ModelLogin>();

            // enquanto tiverem registros no ResultSet (rs), 
            // vai adicionando na lista
            while (rs.next()) {
                // cria objeto de municipio
                // cada cidade é um objeto
                ModelLogin login = new ModelLogin();

                login.setUser(rs.getString("User"));
                login.setSenha(rs.getString("Senha"));

                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(login);
            }

            // fecha o statement e o datasource
            ps.close();
            dataSource.closeDataSource();

            // retorna os dados consultados
            return lista;
        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar dados " + ex.getMessage());
            //JOptionPane.showMessageDialog(null,"Erro ao recuperar dados!\n"+ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral " + ex.getMessage());
            //JOptionPane.showMessageDialog(null,"Erro geral!\n"+ex.getMessage());
        }

        // fecha o datasource
        dataSource.closeDataSource();

        // caso aconteça alguma coisa
        return null;
    }

}
