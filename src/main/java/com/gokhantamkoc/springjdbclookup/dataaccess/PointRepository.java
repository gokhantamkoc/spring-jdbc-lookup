package com.gokhantamkoc.springjdbclookup.dataaccess;

import java.util.List;
import java.util.Optional;

import com.gokhantamkoc.springjdbclookup.entity.Point;

public interface PointRepository {
	
	int count();
	
	int save(Point point);
	
	int update(Point point);
	
	List<Point> findAll();
	
	Optional<Point> findById(Long id);
}
