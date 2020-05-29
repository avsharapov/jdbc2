package stc.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private DBUtil() {
    }

    public static void renewDatabase() throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbcDB", "postgres", "qwerty");
             Statement statement = connection.createStatement();
        ) {
            statement.execute("-- Database: jdbcDB\n"
                              + "DROP TABLE IF EXISTS manufacturer;"
                              + "\n"
                              + "CREATE TABLE manufacturer (\n"
                              + "    id bigserial primary key,\n"
                              + "    name varchar(100) NOT NULL);"
                              + "\n"
                              + "INSERT INTO manufacturer (name)\n"
                              + "VALUES\n"
                              + "   ('Xiaomi'),\n"
                              + "   ('Micro'),\n"
                              + "   ('Apple'),\n"
                              + "   ('SAMSUNG');"
                              + "\n"
                              + "DROP TABLE IF EXISTS mobile;"
                              + "\n"
                              + "CREATE TABLE mobile (\n"
                              + "    id bigserial primary key,\n"
                              + "    model varchar(100) NOT NULL,\n"
                              + "    price integer NOT NULL,\n"
                              + "    man bigint NOT NULL);"
                              + "\n"
                              + "INSERT INTO mobile (model, price, man)\n"
                              + "VALUES\n"
                              + "   ('P1', 100, 1),\n"
                              + "   ('EDGE', 1, 2),\n"
                              + "   ('FRY1', 1001, 3),\n"
                              + "   ('FRY1', 1002, 3),\n"
                              + "   ('OGO', 10000, 4);"
                              + "\n"
                              + "DROP PROCEDURE IF EXISTS insert_data(integer);"
                              + "\n"
                              + "CREATE OR REPLACE PROCEDURE insert_data(a integer)\n"
                              + "    LANGUAGE SQL\n"
                              + "AS\n"
                              + "    $$\n"
                              + "    UPDATE mobile SET price = price + 1 WHERE id = a\n"
                              + "$$;"
                              + "DROP FUNCTION IF EXISTS multiply(integer);"
                              + "\n"
                              + "CREATE OR REPLACE FUNCTION multiply(a INT) RETURNS INT AS $$\n"
                              + "BEGIN\n"
                              + "    RETURN a * 2;\n"
                              + "END\n"
                              + "$$ LANGUAGE 'plpgsql';");
        }
    }
}
