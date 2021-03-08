package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dburl = "jdbc:oracle:thin:@54.146.84.104:1521:xe";
        String dbUsername= "hr";
        String dbPassword= "hr";


        //create connection
        Connection connection = DriverManager.getConnection(dburl,dbUsername,dbPassword);
                //choose connection java.sql which already embedded java without need any dependency

        //create statement object
        Statement statement = connection.createStatement();

        // run query and get the result in resultset object

        //ResultSet resultSet=statement.executeQuery("Select * from regions");

        ResultSet resultSet=statement.executeQuery("Select * from departments");


        /* step  by step version
        //move pointer to first row because it choose first name row as default
        resultSet.next();

        //get information with column name
        System.out.println(resultSet.getString("region_name"));
        //Europe

        //get information with column index(starts from 1)
        System.out.println(resultSet.getString(2));
        //Europe
        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));
        //1-Europe

        //move pointer to second row
        resultSet.next();

        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));
        //2-Americas

         */

        while(resultSet.next()){
            //System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));
            //1-Europe
            //2-Americas
            //3-Asia
            //4-Middle East and Africa
            System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2)+"-"+resultSet.getString(3));
            //10-Administration-200
            //20-Marketing-201
            //30-Purchasing-114
            //40-Human Resources-203
            //50-Shipping-121
            //60-IT-103
            //...

        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
