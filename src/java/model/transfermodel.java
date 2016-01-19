/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controllers.transfer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libs.databaseModel;

/**
 *
 * @author M.A
 */
public class transfermodel {
    public boolean transfer(int sender,int receiver,double value){
        boolean check = false;
        int accountRecord = 0, transferRecord = 0,receiverRecord=0;
        databaseModel connection = null;
        try {
            connection = databaseModel.getConnection();
            connection.autoCommit(false);
            
            String transferSql = "insert into transfer values(null,?,?,?, now());";
            PreparedStatement transferStatement = connection.statement(transferSql);
            transferStatement.setInt(1, sender);
            transferStatement.setInt(2, receiver);
            transferStatement.setDouble(3, value);
            transferRecord = connection.makeRecord(transferStatement);
            
            String accountSql = "update account  set balance = ? where acount_num =?";
            PreparedStatement accountStatement = connection.statement(accountSql);
            ResultSet account = depositeModel.getAccount(sender);
            account.next();
            accountStatement.setDouble(1, +(account.getInt(2)-value));
            accountStatement.setInt(2, sender);
            accountRecord = connection.makeRecord(accountStatement);
            
            String receiverSql = "update account  set balance = ? where acount_num =?";
            PreparedStatement receiverStatement = connection.statement(receiverSql);
            ResultSet receiverAccount = depositeModel.getAccount(receiver);
            receiverAccount.next();
            receiverStatement.setDouble(1, +(receiverAccount.getInt(2)+value));
            receiverStatement.setInt(2, receiver);
            receiverRecord = connection.makeRecord(receiverStatement);

            connection.Commit();
        } catch (SQLException | ClassNotFoundException ex) {
            connection.rollback();
            Logger.getLogger(transfer.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        return ((transferRecord + receiverRecord+accountRecord) >= 2);
        
    }
}
