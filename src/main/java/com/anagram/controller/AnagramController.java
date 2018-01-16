package com.anagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anagram.main.AnagramResult;
import com.anagram.service.AnagramService;

@RestController
@RequestMapping
public class AnagramController {

	@Autowired
	AnagramService anagramService;

	@GetMapping("/Anagram/{searchKeywords}")
	public List<AnagramResult> getAnagramResult(@PathVariable("searchKeywords") List<String> searchKeywords) {
		
		return anagramService.searchAnagrams(searchKeywords);

	}

}
