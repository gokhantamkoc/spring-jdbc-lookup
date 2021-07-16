package com.gokhantamkoc.springjdbclookup.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gokhantamkoc.springjdbclookup.dataaccess.PointRepository;

@Repository
public class JdbcPointRepository implements PointRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate
                .queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public int save(Point point) {
		return jdbcTemplate.update(
                "insert into point (x, y) values(?,?)",
                point.getX(), point.getY());
	}

	@Override
	public int update(Point point) {
		return jdbcTemplate.update(
				"update point set x = ?, y = ? where id = ?",
                point.getX(), point.getY(), point.getId());
	}

	@Override
	public List<Point> findAll() {
		return jdbcTemplate.query(
                "select * from point",
                (rs, rowNum) ->
                        new Point(
                                rs.getLong("id"),
                                rs.getFloat("x"),
                                rs.getFloat("y")
                        )
        );
	}

	@Override
	public Optional<Point> findById(Long id) {
		return jdbcTemplate.queryForObject(
                "select * from point where id = ?",
                (rs, rowNum) ->
                        Optional.of(
                        		new Point(
		                        		rs.getLong("id"),
		                                rs.getFloat("x"),
		                                rs.getFloat("y")
		                        )
        		),
                new Object[] {id}
        );
	}
	
}
