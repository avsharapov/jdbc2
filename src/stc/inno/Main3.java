package stc.inno;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main3 {
    public static void main(String[] args) throws SQLException, IOException {
        try (CachedRowSet jdbcRS1 = RowSetProvider.newFactory().createCachedRowSet();
             CachedRowSet jdbcRS2 = RowSetProvider.newFactory().createCachedRowSet();
             JoinRowSet joinRS = RowSetProvider.newFactory().createJoinRowSet();
             Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbcDB", "postgres",
                                                                 "qwerty")) {

            DBUtil.renewDatabase();
            String sql = "SELECT * FROM mobile";
            jdbcRS1.setCommand(sql);
            jdbcRS1.execute(connection);
            String sql2 = "SELECT * FROM manufacturer";
            jdbcRS2.setCommand(sql2);
            jdbcRS2.execute(connection);

            joinRS.addRowSet(jdbcRS1, 4);
            joinRS.addRowSet(jdbcRS2, 1);
            //joinRS.addRowSetListener(new ExampleListener());
            while (joinRS.next()) {
                System.out.print(joinRS.getInt(1)  + "\t");
                System.out.print(joinRS.getString(2)  + "\t");
                System.out.print(joinRS.getInt(3)  + "\t");
                //System.out.print(joinRS.getInt(4)  + "\t");
                System.out.println(joinRS.getString(5));
            }
        }
    }
}
