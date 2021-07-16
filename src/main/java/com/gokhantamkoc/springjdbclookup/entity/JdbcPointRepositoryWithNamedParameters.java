package com.gokhantamkoc.springjdbclookup.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcPointRepositoryWithNamedParameters extends JdbcPointRepository {

	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    public int update(Point point) {
        return namedParameterJdbcTemplate.update(
                "update point set x = :x, y = :y where id = :id",
                new BeanPropertySqlParameterSource(point));
    }

    @Override
    public Optional<Point> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from point where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new Point(
                        		rs.getLong("id"),
                                rs.getFloat("x"),
                                rs.getFloat("y")
                        ))
        );
    }
}
