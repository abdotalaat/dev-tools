package com.abdotalaat.dev.tools.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//http
public class FileReader {

	private static Gson gson = new Gson();

	final static Logger logger = LoggerFactory.getLogger(FileReader.class);

	private FileReader() {
	}

	public static String readFileAsString(String filePath) throws IOException {
		logger.info("Start read file in the following path [{}]", filePath);
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		return content;
	}

	public static JsonObject readFileAsJson(String filePath) throws IOException {
		String content = readFileAsString(filePath);
		JsonElement jelem = gson.fromJson(content, JsonElement.class);
		JsonObject contentObj = jelem.getAsJsonObject();
		return contentObj;
	}

}
