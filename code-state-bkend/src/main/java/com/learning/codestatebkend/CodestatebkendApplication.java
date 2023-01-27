package com.learning.codestatebkend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CodestatebkendApplication {

	final static String serverUrl1 = "states_hash.json";
	
	final static String serverUrl2 = "states_titlecase.json";

	public static void main(String[] args) {
		SpringApplication.run(CodestatebkendApplication.class, args);
	}

	public static String requestProcessedData(int urlid) {
		// String serverUrl = null;
		// if(urlid == 1) {
		// 	serverUrl = serverUrl1;
		// } else if (urlid == 2) {
		// 	serverUrl = serverUrl2;
		// } else {
		// 	return "ERROR";
		// }

		// RestTemplate request = new RestTemplate();
		// String result = request.getForObject(serverUrl, String.class);
		// System.out.println(serverUrl);
		// return (result);

		String result = null;
        if(urlid == 1){
        	try {
        		
        		Resource resource = new ClassPathResource(serverUrl1);
        	       
                InputStream dbAsStream = resource.getInputStream();
                
        		System.out.println("Server URL: "+serverUrl1);
        		
        		  StringBuilder textBuilder = new StringBuilder();
        		    try (Reader reader = new BufferedReader(new InputStreamReader
        		      (dbAsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
        		        int c = 0;
        		        while ((c = reader.read()) != -1) {
        		            textBuilder.append((char) c);
        		        }
        		    }
                
        		result =textBuilder.toString();
                   
                } catch (Exception e) {
					System.out.println(e.getMessage());
                	// logger.error("[ERROR] : [CUSTOM_LOG] : "+e.getMessage(), e);
                }
        }else if (urlid == 2){
        	try {
        		Resource resource = new ClassPathResource(serverUrl2);
     	       
                InputStream dbAsStream = resource.getInputStream();
                
        		System.out.println("Server URL: "+serverUrl2);
        		
        		  StringBuilder textBuilder = new StringBuilder();
        		    try (Reader reader = new BufferedReader(new InputStreamReader
        		      (dbAsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
        		        int c = 0;
        		        while ((c = reader.read()) != -1) {
        		            textBuilder.append((char) c);
        		        }
        		    }
                
        		result =textBuilder.toString();
                
             } catch (Exception e) {
				System.out.println(e.getMessage());
            	//  logger.error("[ERROR] : [CUSTOM_LOG] : "+e.getMessage(), e);
             }
        }else{
            return "ERROR";
        }

		return (result);
	}

	@GetMapping("/")
	public static String Hello() {
		return "Hello, I am DataReader.";
	}

	@GetMapping("/readDataForCode")
	public static String requestCodeData() {
		return requestProcessedData(1);
	}

	@GetMapping("/readDataForState")
	public static String requestStateData() {
		return requestProcessedData(2);
	}
}
