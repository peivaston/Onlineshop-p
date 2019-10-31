package no.kristiania.jdbc;


import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMemberDaoTest {

    private JdbcDataSource dataSource = OnlineDatabaseTest.testDataSource();
    @Test
    void shouldListInsertedProjectMembers() throws SQLException {
        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        ProjectMember projectMember = sampleProduct();

        /*dataSource.getConnection().createStatement().executeUpdate(
                "create table products  (id serial primary key, name varchar(1000) not null)"
        );*/


        dao.insert(projectMember);
        assertThat(dao.listAll()).contains(projectMember);

    }

    @Test
    public void shouldSaveAllProjectMembers() throws SQLException {
        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        ProjectMember projectMember = sampleProduct();
        long id = dao.insert(projectMember);
        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(projectMember);
    }

    private ProjectMember sampleProduct() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(sampleProjectMembersName());
    return projectMember;
    }


    private String sampleProjectMembersName() {
        String[] alternatives = {
                "Thomas", "Espen", "Abdi", "Yahya", "Håkon"
        };
        return alternatives[new Random().nextInt(alternatives.length)];
    }








}

