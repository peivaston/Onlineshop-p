package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ProjectMemberDao extends AbstractDao<ProjectMember> {

    public ProjectMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertObject(ProjectMember projectMember, PreparedStatement statement) throws SQLException {
        statement.setString(1, projectMember.getName());
    }

    @Override
    protected ProjectMember readObject(ResultSet rs) throws SQLException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(rs.getString("name"));

        return projectMember;
    }

    public long insert(ProjectMember projectMember) throws SQLException {
        return insert(projectMember, "insert into project_members (name) values(?)");
    }



    public ProjectMember retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from project_members where id =?")) {
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

        public List<ProjectMember> listAll () throws SQLException {
            return listAll("select * from project_members");
        }
    }

