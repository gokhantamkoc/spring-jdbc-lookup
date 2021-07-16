package com.gokhantamkoc.springjdbclookup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
	
	private Long id;
	private Float x;
	private Float y;
}
