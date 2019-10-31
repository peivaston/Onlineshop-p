package no.kristiania.jdbc;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

public class OnlineShop {

    private ProjectMemberDao memberDao;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private PGSimpleDataSource dataSource;
    private ProjectMemberDao projectMemberDao;

    public OnlineShop() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/task-manager.properties"));

        dataSource = new PGSimpleDataSource();

        dataSource.setUrl(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        projectMemberDao = new ProjectMemberDao(dataSource);
        memberDao = new ProjectMemberDao(dataSource);

    }


/*    private OrderDao orderDao;
    private BufferedReader input = new BufferedReader((new InputStreamReader(System.in)));
    private PGSimpleDataSource dataSource;
    private ProductDao productDao;*/

    public static void main(String[] args) throws IOException, SQLException {
        new OnlineShop().run();

    }


    private void run() throws IOException, SQLException {
        System.out.println("Type in ProjectMember: Type in members]");
        String action = input.readLine();

        switch (action.toLowerCase()) {
            case "project_members":
                insertMember();
                System.out.println("Current members " + projectMemberDao.listAll());
                break;

        }

    }


    private void insertMember() throws IOException, SQLException {
        System.out.println("Please type the name of new members");
        String productName = input.readLine();
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(productName);
        projectMemberDao.insert(projectMember);
    }
}




