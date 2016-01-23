package util;

/**
 * Created by Mihai on 1/23/2016.
 */

import com.querydsl.sql.codegen.MetaDataExporter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBGenQueryDSL {


    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://db4free.net:3306/test_automation";
        Connection conn = DriverManager.getConnection(url, "mihai", "mypassword");

        MetaDataExporter exporter = new MetaDataExporter();
        exporter.setNamePrefix("S");
        exporter.setPackageName("TADHDB");
        exporter.setTargetFolder(new File("src/main/java"));
//        exporter.setLowerCase(true);
        exporter.export(conn.getMetaData());

        conn.close();
    }
}
