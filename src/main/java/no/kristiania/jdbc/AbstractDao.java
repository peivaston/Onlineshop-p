package no.kristiania.jdbc;

import javax.sql.DataSource;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public abstract class AbstractDao<T> {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) { this.dataSource = dataSource;    }

    public long insert(T projectMember, String sql1) throws SQLException {
        try (Connection connection= dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertObject(projectMember, statement);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong(1);

            }
        }
    }

    protected abstract void insertObject(T projectMember, PreparedStatement statement) throws SQLException;

    public List<T> listAll(String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<T> projectMember = new ArrayList<>();
                    while (rs.next()) {
                        projectMember.add(readObject(rs));
                    }
                    return projectMember;
                }
            }
        }
    }

    protected abstract T readObject(ResultSet rs) throws SQLException;

}
