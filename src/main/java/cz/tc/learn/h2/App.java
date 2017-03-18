package cz.tc.learn.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * @author tomas.cejka
 * 
 * @see <a href="http://www.claudiobernasconi.ch/2010/08/17/h2-embedded-java-db-getting-started/">
 * H2: Embedded Java DB: Getting Started</a>
 * @see <a href="http://h2database.com/html/tutorial.html#connection_pool">Using a Connection Pool (official)</a>
 */
public class App {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "");
            try ( Connection con = cp.getConnection(); Statement stmt = con.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS table1 ( user varchar(50) )");
                stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Claudio' )");
                stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Bernasconi' )");
                
                ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
                while (rs.next()) {
                    String name = rs.getString("user");
                    System.out.println(name);
                }
                stmt.executeUpdate( "DROP TABLE table1" );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
