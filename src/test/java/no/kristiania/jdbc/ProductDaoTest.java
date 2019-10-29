package no.kristiania.jdbc;


import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {

    private JdbcDataSource dataSource = OnlineDatabaseTest.onlineDataSource();
    @Test
    void shouldListInsertedProducts() throws SQLException {
        ProductDao dao = new ProductDao(dataSource);
        Product product = sampleProduct();
        dao.insert(product);
        assertThat(dao.listAll()).contains(product);

    }

    @Test
    public void shouldSaveAllProducts() throws SQLException {
        ProductDao dao = new ProductDao(dataSource);
        Product product = sampleProduct();
        long id = dao.insert(product);
        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(product);
    }

    private Product sampleProduct() {
        Product product = new Product();
        product.setName(sampleProductName());
    return product;
    }


    private String sampleProductName() {
        String[] alternatives = {
                "apples", "bananas", "cocunuts", "dates", "ehhh"
        };
        return alternatives[new Random().nextInt(alternatives.length)];
    }








}

