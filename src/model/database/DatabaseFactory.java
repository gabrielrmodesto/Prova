/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

/**
 *
 * @author gabrielrmodesto
 */
public class DatabaseFactory {
    public static DatabaseMysql getDatabase(String nome){
        if(nome.equals("mysql")){
            return new DatabaseMysql();
        }
        return null;
    }
}
