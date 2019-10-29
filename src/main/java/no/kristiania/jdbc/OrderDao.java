package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends AbstractDao<Order> {

    //private List<Order> orders = new ArrayList<>();



    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(Order order, PreparedStatement statement) throws SQLException {
        statement.setString(1, order.getName());
    }


    //public List<Order> listAll(String sql) { return orders; }

    @Override
    protected Order readObject(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setName(rs.getString("name"));
        return order;
    }

    public void insert(Order product) throws SQLException {
        insert(product);
    }


    public List<Order> listAll() throws SQLException {
        return listAll("Select * from orders");
    }



    }





