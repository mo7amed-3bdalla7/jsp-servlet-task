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
public class withdrawModel {

    public ResultSet account;

    public boolean addwithdraw(int withdraw) {
        int accountRecord = 0, withdrawRecord = 0;
        databaseModel connection = null;
        try {
            connection = databaseModel.getConnection();
            connection.autoCommit(false);
            String withdrawSql = "insert into withdraw values(null,?,?, now());";
            PreparedStatement statement = connection.statement(withdrawSql);
            statement.setInt(1, account.getInt(1));
            statement.setInt(2, withdraw);
            withdrawRecord = connection.makeRecord(statement);
            String accountSql = "update account  set balance = ? where acount_num =?";
            PreparedStatement accountStatement = connection.statement(accountSql);
            accountStatement.setInt(1, +(account.getInt(2)-withdraw));
            accountStatement.setInt(2, account.getInt(1));

            System.out.println(accountStatement);
            accountRecord = connection.makeRecord(accountStatement);

            connection.Commit();
        } catch (SQLException | ClassNotFoundException ex) {
            connection.rollback();
            Logger.getLogger(withdrawModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (withdrawRecord + accountRecord >= 2) ? true : false;
    }

}
