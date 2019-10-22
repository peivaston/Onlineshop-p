package no.kristiania.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class OnlineshopTest {

    @Test
    void shouldRetrieveStoredProduct() throws SQLException {
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL("jdbc:h2:mem:test");

        datasource.getConnection().createStatement().executeUpdate(
                "create table products (name varchar(100))"
        );


        ProductDao dao = new ProductDao(datasource);
        String productName = pickOne(new String[] { "Apples", "Bananas", "Coconuts", "Dates"});
        dao.insertProduct(productName);
        assertThat(dao.listAll()).contains(productName);
    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];
    }
}
