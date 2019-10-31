package no.kristiania.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMemberTest {

private JdbcDataSource dataSource = OnlineDatabaseTest.testDataSource();
    @Test
    void shouldFindSavedMembers() throws SQLException {

        ProjectMember member = new ProjectMember();
        member.setName("Test");
        ProjectMemberDao dao = new ProjectMemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);
    }

}
