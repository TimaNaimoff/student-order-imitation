package edu.javacourse.studentorder.dao;

import org.junit.BeforeClass;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DbInit {

    public static void startUp() throws Exception {
        URL url1 = DctionaryDaoImplTest.class.getClassLoader()
                .getResource("student_project.sql");
        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String query = str1.stream().collect(Collectors.joining());

        URL url2 = DctionaryDaoImplTest.class.getClassLoader().
                getResource("data_fuller.sql");
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        String query2 = str2.stream().collect(Collectors.joining());

        try (Connection con = ConnectionBuilder.getConnection();
             Statement stat = con.createStatement()) {
            stat.executeUpdate(query);
            stat.executeUpdate(query2);
        }


    }

}
