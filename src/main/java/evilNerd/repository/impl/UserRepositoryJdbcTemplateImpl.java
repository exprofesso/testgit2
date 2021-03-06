package evilNerd.repository.impl;

import evilNerd.domain.Gender;
import evilNerd.domain.User;
import evilNerd.repository.UserColumns;
import evilNerd.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.security.KeyStore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Slf4j
@Repository
@Primary

public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private static final Logger log = Logger.getLogger(UserRepositoryJdbcTemplateImpl.class);


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> search(String query) {
        return jdbcTemplate.query("select * from m_users where name like ?", new Object[]{query}, this::getUserRowMapper);

        // '\%:query\%'"
    }

    @Override
    public User save(User entity) {
        final String createQuery = "insert into m_users(name, surname, birth_date, gender, created, changed, weight)" +
                "values(:name, :surname, :birth_date, :gender, :created, :changed, :weight);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name",entity.getName());
        params.addValue("surname",entity.getSurname());
        params.addValue("birth_date",entity.getBirthDate());
        params.addValue("gender",entity.getGender().name());
        params.addValue("created",entity.getCreated());
        params.addValue("changed",entity.getChanged());
        params.addValue("weight",entity.getWeight());

        namedParameterJdbcTemplate.update(createQuery,params,keyHolder, new String[]{"id"});
        long createdUserId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findById(createdUserId);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from m_users", this::getUserRowMapper);
    }
    private User getUserRowMapper(ResultSet rs, int i ) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserColumns.ID));
        user.setName(rs.getString(UserColumns.NAME));
        user.setSurname(rs.getString(UserColumns.SURNAME));
        user.setBirthDate(rs.getDate(UserColumns.BIRTH_DATE));
        user.setGender(Gender.valueOf(rs.getString(UserColumns.GENDER)));
        user.setCreated(rs.getTimestamp(UserColumns.CREATED));
        user.setChanged(rs.getTimestamp(UserColumns.CHANGED));
        user.setWeight(rs.getFloat(UserColumns.WEIGHT));
        return user;
    }


    @Override
    public User findById(Long key) {

//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("userId", key);
//
//        return namedParameterJdbcTemplate.queryForObject("select * from m_users where id = :userId", mapSqlParameterSource, this::getUserRowMapper);
        //второй вариат реализации
        return jdbcTemplate.queryForObject("select * from m_users where id = ?", new Object[]{key}, this::getUserRowMapper);


    }

    @Override
    public Optional<User> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public User update(User object) {
        final String updateQuery = "update m_users set name = ?, + " +
                "surname = ?, birth_date = ?, gender = ?, created = ?, " +
                "changed = ?, weight = ?" +
                "where id = ?";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        //        return jdbcTemplate.queryForObject("update m_users " +
//                "set " +
//                "name = ?,  " +
//                "surname = ?,  " +
//                "birth_date = ?,  " +
//                "gender = ?,  " +
//                "created = ?,  " +
//                "changed = ?,  " +
//                "weight = ?  " +
//                "where id = ?", new Object[]{key}, this::getUserRowMapper);

        return null;
    }

    @Override
    public Long delete(User user) {
        return 0L;
    }
}