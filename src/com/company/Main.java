package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String user = "root";
        String pass = "lifecell";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(url, user,pass);
            Statement statement = connection.createStatement()){
            statement.execute("drop table if exists books");
            statement.addBatch("create table if not exists books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(20) NOT NULL, author CHAR(20) NOT NULL , PRIMARY KEY (id))");
            statement.addBatch("insert into books (name,author) values ('Kolobok', 'Vova')");
            statement.addBatch("insert into books (name,author) values ('Abetka', 'Sasha')");
            statement.addBatch("insert into books (name,author) values ('Bykvar', 'Vlad')");
            statement.executeBatch();
            ResultSet resultSet = statement.executeQuery("select * from books");
            while(resultSet.next()){
                System.out.println("book`s name = " + resultSet.getString("name") + "this author is = " + resultSet.getString("author"));
            }

            }
    }
}