package com.abdotalaat.dev.tools.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FileReader {

	private static Gson gson = new Gson();

	private FileReader() {
	}

	public static String readFileAsString(String filePath) throws IOException {
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
