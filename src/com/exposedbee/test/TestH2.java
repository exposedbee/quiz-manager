

import java.sql.*;

public class TestH2 {
    public static void  main(String[] args)throws SQLException{
        Connection connection= DriverManager.getConnection("jdbc:h2:~/test","sa","");

        PreparedStatement statement= connection.prepareStatement("Select * from PERSON");
        ResultSet rs= statement.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));

        }
        //TODO : extract the content of the following queries :
        boolean b;
//		SELECT * FROM PERSON ORDER BY WEIGHT DESC;
//		SELECT * FROM PERSON WHERE NAME LIKE 'Q%'
//		select age as ages, count(*) as count from person group by age;
        System.out.println("SELECT * FROM PERSON WHERE NAME LIKE 'Q%");
        statement= connection.prepareStatement("drop table test3 ");
        try {
            b = statement.execute();
            System.out.println("b" + b);
        }
        catch (SQLException se){
            System.out.println("execution failed \n "+se);
        }
        statement= connection.prepareStatement("Create table test3 as (SELECT * From Person)");
        try {
            b = statement.execute();
            System.out.println("b" + b);
        }
        catch (SQLException se){
            System.out.println("execution failed \n "+se);
        }

        System.out.println("SELECT * FROM PERSON WHERE NAME LIKE 'Q%");
        statement= connection.prepareStatement("SELECT * FROM PERSON WHERE NAME LIKE ?");
        statement.setString(1,"Q%");
        rs= statement.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        System.out.println("\n Order by weight");
        String ss="SELECT * FROM PERSON ORDER BY WEIGHT DESC";
        statement= connection.prepareStatement(ss);
        rs= statement.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }

        System.out.println("\n Order by age");
        statement= connection.prepareStatement("SELECT AGE as ages, count(*) as count FROM PERSON Group BY age");
        rs= statement.executeQuery();

        while(rs.next()){
            System.out.print(rs.getInt(1));
            System.out.println(":"+rs.getInt(2));
        }

    }
}
