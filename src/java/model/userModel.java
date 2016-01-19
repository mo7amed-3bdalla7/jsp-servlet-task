/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import libs.databaseModel;

/**
 *
 * @author M.A
 */
public class userModel {

    private long id;
    private String fristName;
    private String lastName;
    private String phone;
    private String email;
    private int age;
    private String password;
    private long accountNum;
    private String photo;

    public userModel() {
        this.id = 0;
    }

    public ResultSet authantication(String email, String password) {

        ResultSet fetchedData = null;
        try {
            databaseModel connection = databaseModel.getConnection();
            PreparedStatement statement = connection.statement("select * from users where email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            fetchedData = connection.fetchData(statement);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fetchedData;

    }

    public String hashPassword(String password) {
        String sault = "m7md";
        String hash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            hash = password + sault;
            md.update(hash.getBytes("UTF-8"));
            byte[] digest = md.digest();
            //System.out.println(hash);
            hash = DatatypeConverter.printHexBinary(digest);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int save() {
        int check = 0;
        if (getId() == 0) {
            try {
                databaseModel connection = databaseModel.getConnection();
                ///// account
                this.accountNum = System.currentTimeMillis() % 1000000000;
                PreparedStatement accounSstatement = connection.statement("insert into account values(?,?,now())");
                accounSstatement.setLong(1, getAccountNum());
                accounSstatement.setLong(2, 0);
                check = connection.makeRecord(accounSstatement);
                accounSstatement.close();
                //// user
                PreparedStatement userStatement = connection.statement("insert into users values(null,?,?,?,?,?,?,?,?)");
                userStatement.setString(1, this.fristName);
                userStatement.setString(2, this.lastName);
                userStatement.setString(3, this.phone);
                userStatement.setString(4, this.email);
                userStatement.setInt(5, this.age);
                userStatement.setString(6, hashPassword(password));
                userStatement.setLong(7, this.accountNum);
                userStatement.setString(8, this.photo);
                check = connection.makeRecord(userStatement);
                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                userStatement.close();
                //System.out.println(userStatement.toString());
                // System.out.println(check);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public ResultSet getHistory(String table, boolean transfer) {
        ResultSet History = null;
        try {
            String Sql = "select * from " + table + " where acount_num = ?;";
            if (transfer) {
                Sql = "select * from transfer where sender = ?;";
            }
            databaseModel connection = databaseModel.getConnection();
            PreparedStatement statement = connection.statement(Sql);
            statement.setLong(1, this.accountNum);
            History = connection.fetchData(statement);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return History;
    }
    /* public ResultSet getWithdrawHistory() {
     ResultSet depositeHistory = null;
     try {
     String depositeSql = "select * from deposit where acount_num = ?;";
     databaseModel connection = databaseModel.getConnection();
     PreparedStatement statement = connection.statement(depositeSql);
     statement.setLong(1, this.accountNum);
     depositeHistory = connection.fetchData(statement);
     } catch (SQLException | ClassNotFoundException ex) {
     Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
     }
     return depositeHistory;
     }
     public ResultSet getTransferHistory() {
     ResultSet TransferHistory = null;
     try {
     String TransferSql = "select * from Transfer where acount_num = ?;";
     databaseModel connection = databaseModel.getConnection();
     PreparedStatement statement = connection.statement(TransferSql);
     statement.setLong(1, this.accountNum);
     TransferHistory = connection.fetchData(statement);
     } catch (SQLException | ClassNotFoundException ex) {
     Logger.getLogger(userModel.class.getName()).log(Level.SEVERE, null, ex);
     }
     return TransferHistory;
     }*/
}
