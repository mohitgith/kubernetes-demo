package com.learning.codestate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class CodeStateApplication {

	public static final String serverUrl = "http://localhost:9090";

	public static void main(String[] args) {
		SpringApplication.run(CodeStateApplication.class, args);
	}

	public static String requestProcessedData(String url) {
		RestTemplate request = new RestTemplate();
		String result = request.getForObject(url, String.class);
		System.out.println(url);
		return (result);
	}

	@GetMapping("/")
	public static String Hello() {
		return "Hello, I am the Converter.";
	}

	@GetMapping("/codeToState")
	public static String requestCodeData(@RequestParam("code") String code) {
		String state = null;
		String response = requestProcessedData(serverUrl + "/readDataForCode");
		JSONObject jsonObject = new JSONObject(response);
		state = jsonObject.getString(code.toUpperCase());

		if(state == null) {
			state = "No Match Found.";
		}

		return state;

	}

	@GetMapping("/stateToCode")
	public static String requestStateData(@RequestParam("state") String state) {
		String value = "";

		String response = requestProcessedData(serverUrl + "/readDataForState");
		JSONArray jsonArray = new JSONArray(response);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String name = jsonObject.getString("name");

			if(state.equalsIgnoreCase(name)) {
				value = jsonObject.getString("abbreviation");
				break;
			}

			if(value == null) {
				value = "No Match Found.";
			}
		}
		return value;
	}

}
