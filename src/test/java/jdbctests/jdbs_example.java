package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbs_example {
    String dbburl = "jdbc:oracle:thin:@54.146.84.104:1521:xe";
    String dbUsername= "hr";
    String dbPassword= "hr";


    @Test
    public  void test1() throws SQLException {


        //create connection
        Connection connection = DriverManager.getConnection(dbburl,dbUsername,dbPassword);
                //choose connection java.sql which already embedded java without need any dependency

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
                                                        //ResultSet.TYPE_SCROLL_INSENSITIVE
                                                        //—> allow us to navigate up and down in query result.
                                                                                            //ResultSet.CONCUR_READ_ONLY
                                                                                            //—> Read only, don’t update the database
        // run query and get the result in result set object

        ResultSet resultSet=statement.executeQuery("Select * from departments");
        
        // how to find many rows we have for query
        // go to last row

        resultSet.last();
        //get the row count
        int rowCount=resultSet.getRow();
        System.out.println("rowCount = " + rowCount);
        //rowCount = 27

        resultSet.beforeFirst();
        //we need move before first row to get full list since we are at he last row right now.

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));

            //Administration
            //Marketing
            //Purchasing
            //Human Resources
            //Shipping
            //IT
            //Public Relations
            //Sales
            //Executive
            //Finance
            //Accounting
            //Treasury
            //Corporate Tax
            //Control And Credit
            //Shareholder Services
            //Benefits
            //Manufacturing
            //Construction
            //Contracting
            //Operations
            //IT Support
            //NOC
            //IT Helpdesk
            //Government Sales
            //Retail Sales
            //Recruiting
            //Payroll
        }
        //--------------------------------------------------------------------------

        resultSet=statement.executeQuery("Select * from regions");


        while(resultSet.next()) {

            System.out.println(resultSet.getString(2));
            //Europe
            //Americas
            //Asia
            //Middle East and Africa
        }





            //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void MetaDataExample () throws SQLException {

        Connection connection = DriverManager.getConnection(dbburl,dbUsername,dbPassword);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );


        ResultSet resultSet=statement.executeQuery("Select * from departments");
        
        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        //dbMetadata.getUserName() = HR
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        //dbMetadata.getDatabaseProductName() = Oracle
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        //dbMetadata.getDatabaseProductVersion() = Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        //dbMetadata.getDriverName() = Oracle JDBC driver
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());
        //dbMetadata.getDriverVersion() = 11.2.0.3.0

        //----------------------------------------------------------

        //get the resultset object metadata
        ResultSetMetaData rsMetadata=resultSet.getMetaData();

        //how many columns we have ?
        int colCount=rsMetadata.getColumnCount();
        System.out.println("colCount = " + colCount);
        //colCount = 4

        //column names
        System.out.println(rsMetadata.getColumnName(1));
        //DEPARTMENT_ID
        System.out.println(rsMetadata.getColumnName(2));
        //DEPARTMENT_NAME

        //print all the column names dynamically
        for (int i = 1; i <=colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
            //DEPARTMENT_ID
            //DEPARTMENT_NAME
            //MANAGER_ID
            //LOCATION_ID

        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
