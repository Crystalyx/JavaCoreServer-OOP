package com.faceless.sql;
import java.sql.*;

public class Database{
    public void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/Passwords"; // /Passwords это имя базы данных
        String username = "root";
        String password = "1111";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(url, username, password)) { // подключаемся к бд
            System.out.println("We're connected");
            //Тут можно взаимодействовать с ней, добавлять таблицы, заполнять их, получать значения и тд
        }
    }
}