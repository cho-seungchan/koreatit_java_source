package com.example.ex00.dependency;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Getter
public class Restaurant {
	private final Chef chef;
}
