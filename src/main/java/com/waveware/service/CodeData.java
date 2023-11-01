package com.waveware.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CodeData {

	static String JDBCURL = "jdbc:mysql://192.168.2.81:3306/gvc_data";
	static String USERNAME = "root";
	static String PASSWORD = "root";
	
	  public static void main(String[] args) {
	        try {
	            System.out.println("start");

	            String[] urls = {
	                    "https://stat.kita.net/stat/kts/statCode/ItemAllCodeListWorker.screen?event_udap=Search&searchType=SHEET&pageNum=1&listCount=2455&item_type=MTI",
	                    "https://stat.kita.net/stat/kts/statCode/ItemAllCodeListWorker.screen?event_udap=Search&searchType=SHEET&pageNum=1&listCount=30420&item_type=HS",
	                    "https://stat.kita.net/stat/kts/statCode/ItemAllCodeListWorker.screen?event_udap=Search&searchType=SHEET&pageNum=1&listCount=3821&item_type=SITC"
	            };

	            String[] titleNames = {"code_mti", "code_hs", "code_sitc"};

	            for (int i = 0; i < urls.length; i++) {
	                String url = urls[i];
	                String titleName = titleNames[i];

	                String xmlContent = fetchXmlContent(url);
	                parseXml(xmlContent, titleName);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("end");
	    }

	    private static String fetchXmlContent(String url) throws Exception {
	        URLConnection connection = new URL(url).openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder xmlContent = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            xmlContent.append(inputLine);
	        }
	        in.close();
	        return xmlContent.toString();
	    }

    private static void parseXml(String xmlData,String titleName) {
       
        List<String> dataNum = new ArrayList<>();
        List<String> dataValue = new ArrayList<>();
        
        try {
            // XML 파서 설정
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML 파싱
            Document document = builder.parse(new InputSource(new StringReader(xmlData)));

            // "TR" 요소 가져오기
            NodeList trList = document.getElementsByTagName("TR");

            for (int i = 0; i < trList.getLength(); i++) {
                Element trElement = (Element) trList.item(i);
                NodeList tdList = trElement.getElementsByTagName("TD");

                if (tdList.getLength() > 0) {
                    Element tdElementNum = (Element) tdList.item(0);
                    Element tdElementValue = (Element) tdList.item(1);
                    
                    String tdNum = tdElementNum.getTextContent().trim();
                    String tdValue = tdElementValue.getTextContent().trim();
                    
                    dataNum.add(tdNum);
                    dataValue.add(tdValue);
                }
            }
           
           try (Connection connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD)) {
                insertDataToDatabase(connection,titleName,dataNum,dataValue);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

    
    private static void insertDataToDatabase(Connection connection, String titleName, List<String> dataNum, List<String> dataValue) {
        // SQL 쿼리 작성
        String sql = "INSERT INTO " + titleName + "(code, title) VALUES (?, ?) ON DUPLICATE KEY UPDATE title = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 데이터 삽입
            for (int i = 0; i < dataNum.size(); i++) {
                String title = dataValue.get(i);
                int maxTitleLength = 255;
                title = title.substring(0, Math.min(title.length(), maxTitleLength));
                
                preparedStatement.setString(1, dataNum.get(i));
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, title);
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}




