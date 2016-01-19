/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class depositeModel {

    public ResultSet account;

    public boolean addDeposite(int deposit) {
        int accountRecord = 0, depositeRecord = 0;
        databaseModel connection = null;
        try {
            connection = databaseModel.getConnection();
            connection.autoCommit(false);
            String depositeSql = "insert into deposit values(null,?,?, now());";
            PreparedStatement statement = connection.statement(depositeSql);
            statement.setInt(1, account.getInt(1));
            statement.setInt(2, deposit);
            depositeRecord = connection.makeRecord(statement);
            String accountSql = "update account  set balance = ? where acount_num =?";
            PreparedStatement accountStatement = connection.statement(accountSql);
            accountStatement.setInt(1, +(deposit + account.getInt(2)));
            accountStatement.setInt(2, account.getInt(1));

            System.out.println(accountStatement);
            accountRecord = connection.makeRecord(accountStatement);

            connection.Commit();
        } catch (SQLException | ClassNotFoundException ex) {
            connection.rollback();
            Logger.getLogger(depositeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (depositeRecord + accountRecord >= 2) ? true : false;
    }

    public static ResultSet getAccount(long AccountNum) {
        ResultSet fetchData = null;
        try {
            databaseModel connection = databaseModel.getConnection();
            String sql = "select * from account where acount_num = " + AccountNum;
            PreparedStatement statement = connection.statement(sql);
            fetchData = connection.fetchData(statement);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(depositeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fetchData;
    }

}
