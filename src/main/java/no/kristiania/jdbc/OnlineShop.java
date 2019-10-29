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

    private OrderDao orderDao;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private PGSimpleDataSource dataSource;
    private ProductDao productDao;

    public OnlineShop() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("onlinebutikk.properties"));

        dataSource = new PGSimpleDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/onlinebutikk");
        dataSource.setUser("onlinebutikk");
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        productDao = new ProductDao(dataSource);
        orderDao = new OrderDao(dataSource);

    }

    public static void main(String[] args) throws IOException, SQLException {
        new OnlineShop().run();
    }

    private void run() throws IOException, SQLException {
        System.out.println("Choose action: create [task] | create [member]");
        String action = input.readLine();

        switch (action.toLowerCase()) {
            case "task":
                insertProjectMember();
                break;
            case "member":
                insertMember();
                break;
        }

        System.out.println("Current tasks " + productDao.listAll());

    }

    private void insertMember() throws IOException, SQLException {
        System.out.println("Please type the name of new task");
        Order order = new Order();
        order.setName(input.readLine());
        orderDao.insert(order);
    }

    private void insertProjectMember() throws IOException, SQLException {
        System.out.println("Please type the name of new task");
        String productName = input.readLine();
        Product product = new Product();
        product.setName(productName);
        productDao.insert(product);
    }

}