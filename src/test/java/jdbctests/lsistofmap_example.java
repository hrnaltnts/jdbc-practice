package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lsistofmap_example {
    String dbburl = "jdbc:oracle:thin:@54.146.84.104:1521:xe";
    String dbUsername= "hr";
    String dbPassword= "hr";


    @Test
    public void MetaDataExample2 () throws SQLException {


        Connection connection = DriverManager.getConnection(dbburl, dbUsername, dbPassword);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id from employees where rownum<6 ");

        //get the resultset object metadata
        ResultSetMetaData rsMetaData=resultSet.getMetaData();

        //Create a List for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //Create first row as Map
        Map<String,Object> row1 = new HashMap<>();
        //MANUAL WAY
       // row1.put("firstname","Steven");
       // row1.put("last_name","King");
       // row1.put("salary",2400);
       // row1.put("job_id", "AD_PRES");

        //AUTOMATE WAY
        //move cursor to first row !!!
        resultSet.next();

        row1.put(rsMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetaData.getColumnName(4),resultSet.getString(4));


        System.out.println("row1.toString() = " + row1.toString());
        //row1.toString() = {JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}


        //move to second row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetaData.getColumnName(4),resultSet.getString(4));


        System.out.println("row2.toString() = " + row2.toString());
        //row2.toString() = {JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena}

        System.out.println(row2.get("SALARY"));// IT SHOULD BE UPPERCASE
        //17000

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven's last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));
        //starts from 0(first row) // second get("...") is come from map !!!
        //King


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
