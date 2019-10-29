package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ProductDao extends AbstractDao<Product> {

    public ProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(Product product, PreparedStatement statement) throws SQLException {
        statement.setString(1, product.getName());
    }


    @Override
    protected Product readObject(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString("name"));
        return product;
    }

    public long insert(Product product) throws SQLException {
        return insert(product, "insert into products (name) values(?)");
    }

    public Product retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from products where id =?")) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return readObject(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

        public List<Product> listAll () throws SQLException {
            return listAll("select * from products");
        }
    }

