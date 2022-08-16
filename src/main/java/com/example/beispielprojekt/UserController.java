package com.example.beispielprojekt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class UserController {

	
	@GetMapping("/summe")
	public int summe(@RequestParam List<String> zahl) {
		int value = 0;
		for (int i = 0; i < zahl.size(); i++) {
			value += Integer.parseInt(zahl.get(i));
		}
		return value;
	}

	@PostMapping("/upload")
	public int summieren(@RequestParam("eineDatei") MultipartFile datei) throws IOException {
		int result = 0;
		try {
			if (!datei.isEmpty()) {
				byte[] bytes = datei.getBytes();
				String s = new String(bytes, StandardCharsets.UTF_8);

				String[] zahlenString = s.split("\\s");
				int[] zahlen = new int[zahlenString.length];
				for (int i = 0; i < zahlen.length; i++) {
					zahlen[i] = Integer.parseInt(zahlenString[i]);
					result += zahlen[i];
				}
			}
		} catch (Exception e) {
		}
		if (!datei.isEmpty()) {
			return result;
		} else {
			return 0;
		}

	}
	@GetMapping("personalData/upload2")
	public String kundenDaten2(@RequestParam(value = "email") String datei) throws IOException{
		System.out.println(datei);
		return "";
	}
	@PostMapping("/personalData/upload")
	public String kundenDaten(@RequestBody String datei) throws IOException {
		try {

			if (!datei.isEmpty()) {
				
//				//byte[] bytes = datei.getBytes();
//				//String s = new String(bytes, StandardCharsets.UTF_8);
//
//				String path = "C:\\Users\\Kosta\\Desktop\\kundendaten.txt";
//				File f = new File(path);
//
//				if (f.createNewFile()) {
//					System.out.println("File created");
//				} 
//				else {
//					System.out.println("File already exists");
//				}
//				
//				Files.write(Paths.get(path), s.getBytes());
				return "Datei wurde erstellt";
			} else {
				System.out.println("Die Datei ist leer");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return "Datei wurde nicht erstellt";
	}
	
	@GetMapping("/addUser")
	public String sendForm(User user) {
		return "addUser";
	}
	

	@PostMapping("/addUser")
	public String processForm(User user) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("C:\\Users\\Kosta\\Desktop\\kundenDaten.json"), user);
		return "User wurde erstellt";
	}
}