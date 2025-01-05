package com.example.app.domain.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Search {
	
	String type;
	String keyWord;

	public List<String> getTypes(){
		return new ArrayList<>(Arrays.asList(type.split("")));
	}
}
