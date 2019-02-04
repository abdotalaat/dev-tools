package com.abdotalaat.dev.tools.io;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.abdotalaat.dev.tools.utils.Constants;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class FileReaderTest {

	@Test
	public void readFileCheckContentIsNotNullTest() throws IOException {
		String content = FileReader.readFileAsString(this.getClass().getResource(Constants.FILE_READER_PATH).getPath());
		assertNotNull(content);
		assertEquals(content, "success");
	}

	@Test
	public void readJsonFileCheckContentIsNotNullTest() throws IOException {
		String content = FileReader
				.readFileAsString(this.getClass().getResource(Constants.JSON_FILE_READER_PATH).getFile());
		assertNotNull(content);
	}

	@Test
	public void readJsonFileCheckContentIsValidJsonFormatTest() throws IOException {
		String content = FileReader
				.readFileAsString(this.getClass().getResource(Constants.JSON_FILE_READER_PATH).getFile());
		
		String jsonpathCreatorNamePath = "$['tool']['jsonpath']['creator']['name']";
		String jsonpathCreatorLocationPath = "$['tool']['jsonpath']['creator']['location'][*]";
		
		DocumentContext jsonContext = JsonPath.parse(content);
		String jsonpathCreatorName = jsonContext.read(jsonpathCreatorNamePath);
		List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);
		
		
		assertEquals("Jayway Inc.", jsonpathCreatorName);
		assertThat(jsonpathCreatorLocation.toString(), containsString("Malmo"));
		assertThat(jsonpathCreatorLocation.toString(), containsString("San Francisco"));
		assertThat(jsonpathCreatorLocation.toString(), containsString("Helsingborg"));

	}

}
