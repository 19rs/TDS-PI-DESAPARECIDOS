
package com.adriano.pi.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    // variáveis para a conexão
    private String hostname;
    private int    porta;
    private String database;
    private String username;
    private String password;
    
    // variável de conexão
    private Connection connection;
    
    // pedido/abre de conexão
    public DataSource(){
        try{
            // seta valores nas variáveis de conexão
            hostname = "db4free.net";
            porta = 3306;
            database = "adriano2607";
            username = "adriano2607";
            password = "369258147";
            
            // string de conexão
            String url = "jdbc:mysql://"+hostname+":"+porta+"/"+database+"?useTimezone=true&serverTimezone=UTC";
            
            // registrar o driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // faz a conexão
            connection = DriverManager.getConnection(url, username, password);
            
           System.out.println("Conectou!");
        }
        catch (SQLException ex){
            System.err.println("ERRO na conexão "+ex.getMessage());
        }
        catch (Exception ex){
            System.err.println("ERRO geral "+ex.getMessage());
        }
    }
    
    // pega a conexão ativa
    public Connection getConnection(){
        return this.connection;
    }
    
    // fechamento da conexão
    public void closeDataSource(){
        try{
            connection.close();
        }
        catch (Exception ex){
            System.err.println("ERRO ao desconectar "+ex.getMessage());
        }
    }
    
}
