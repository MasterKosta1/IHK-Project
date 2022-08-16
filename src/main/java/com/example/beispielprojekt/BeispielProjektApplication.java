package com.example.beispielprojekt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeispielProjektApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication app = new SpringApplication(BeispielProjektApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args);
		
		dbConnection();
		
		webApiConnection("https://api.julitec.com/api/order", "info@colordigital.com", "9466b2d1-8ca2-48a9-b501-7405d68da70b");
		
	}

	public static void dbConnection() throws ClassNotFoundException {

		String databaseURL = "jdbc:ucanaccess://C:\\Users\\Kosta\\Documents\\Database1.accdb";
		int n = 8;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection(databaseURL);
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM Order";
			ResultSet rs = st.executeQuery(sql);
			
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int numberOfColumns = rsmd.getColumnCount();
		    boolean b = rsmd.isSearchable(10);
		    
		    System.out.println(rsmd.getCatalogName(4));

			while (rs.next()) {
				System.out.print(rs.getString(1));
				System.out.println(" " + rs.getString(2));
			}
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	public static void webApiConnection(String web, String n, String pass) {

		try {
			String webPage = web;
			String name = n;
			String password = pass;

			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
			String authStringEnc = new String(authEncBytes);

			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
				System.out.println();
			}
			String result = sb.toString();
			saveFile(result);
			System.out.println("*** BEGIN ***");
			System.out.println(result);
			System.out.println("*** END ***");

		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String saveFile(String result) {
		try {

			if (!result.isEmpty()) {

				byte[] bytes = result.getBytes();
				String s = new String(bytes, StandardCharsets.UTF_8);

				String path = "C:\\Users\\Kosta\\Desktop\\EinKunde.txt";
				File f = new File(path);

				if (f.createNewFile()) {
					System.out.println("File created");
				} else {
					System.out.println("File already exists");
				}

				Files.write(Paths.get(path), s.getBytes());
				return "Datei wurde erstellt";
			} else {
				System.out.println("Die Datei ist leer");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return "Datei wurde nicht erstellt";
	}

}