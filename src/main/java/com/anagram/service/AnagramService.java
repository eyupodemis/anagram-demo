package com.anagram.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anagram.main.AnagramResult;
import com.anagram.main.Application;

@Service
public class AnagramService {

	public List<AnagramResult> anagramResultArray = new ArrayList<>();
	
	public List<AnagramResult> searchAnagrams(List<String> searhedKeywords) {

		for(String searhedKeyword : searhedKeywords) {
			List<String> resultArray = new ArrayList<>();
			AnagramResult anagramResult = new AnagramResult();
			
			// Keryword Changed. e.g. ANAGRAM changed to AAAGMNR
		    String searhedKeywordOrdered = searhedKeyword
										              .chars()
										              .sorted()
										              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
										              .toString();
		
	        int lengthSearhedKeywordOrdered = searhedKeywordOrdered.length();
	
			try { Application.parsedFileArray 
				      .stream()
				      .filter(line -> line != null && line.length() == lengthSearhedKeywordOrdered )
				      .filter(line -> line.chars()
				    		              .sorted()
						                  .collect(StringBuilder::new, StringBuilder::appendCodePoint,  StringBuilder::append)
										  .toString()
								          .equals(searhedKeywordOrdered)
				    		  )
				      .forEach(line -> resultArray.add(line));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			anagramResult.setAnagramKey(searhedKeyword);
			anagramResult.setAnagramResult(resultArray);
			anagramResultArray.add(anagramResult);
		}
		return anagramResultArray;
	}

}