/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adriano.pi.model;

import com.adriano.pi.DAO.DAOlogin;
import com.adriano.pi.DAO.DataSource;

/**
 *
 * @author adR
 */
public class ModelLogin {
    private String user;
    private String senha;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public static boolean isVerificaSenhaUser(String user, String senha) {
         DataSource dataSource = new DataSource();
        DAOlogin loginsDao = new DAOlogin(dataSource);
        
        for (ModelLogin a : loginsDao.Consulta()) {
            if (a.getUser().equals(user) && a.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
