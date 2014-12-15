package ser.i.mssql;

import net.sourceforge.jtds.jdbc.Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by esadykov on 26.11.2014.
 */
public class Caller {
    public static void main(String[] args) {
        StringBuilder query = new StringBuilder();

        for(String a : args) { query.append(a).append(' '); }

        Connection c = null;
        CallableStatement cs =null;
        ResultSet rs = null;
        try {
            DriverManager.registerDriver(new Driver());
            c = DriverManager.getConnection("jdbc:jtds:sqlserver://80.237.125.74:4433;user=clinic;password=clinic664000;databaseName=regoin");
            cs = c.prepareCall(query.toString());
            if (cs.execute()) {
                rs = cs.getResultSet();

                //System.out.println(rs.getMetaData().getColumnTypeName(1));
                //System.out.println(cs.getString("@name"));
                while (rs.next()) {
                    try {
                        for(int i = 1; i<=rs.getMetaData().getColumnCount(); i++) {
                            System.out.print(rs.getMetaData().getColumnName(i));
                            System.out.print(' ');
                            System.out.println(new BufferedReader(rs.getCharacterStream(rs.getMetaData().getColumnName(i))).readLine());
                        }
                        System.out.println("--");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (cs != null) try { cs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (c != null)  try { c.close();  } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
