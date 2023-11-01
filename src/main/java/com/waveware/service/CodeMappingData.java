package com.waveware.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeMappingData
{

	static String JDBCURL = "jdbc:mysql://192.168.2.81:3306/gvc_data";
	static String USERNAME = "root";
	static String PASSWORD = "root";

	public static void main(String[] args)
	{

		String url_format = "https://stat.kita.net/stat/kts/statCode/ItemCnvCodeListWorker.screen?event_udap=Search&onepagerow=100&pageNum=%d&listCount=100&CTR_GB=&HS_YN=&MTI_YN=&SITC_YN=&item_type=SITC&sYear=2023&stCode=%d";


		for (int code = 0; code <= 9; code++)
		{
			for (int page = 1; ; page++)
			{
				final String URL = String.format(url_format, page, code);

				try
				{
					String xml = fetchXmlContent(URL);
					List<Data> dataList = parseXml(xml);

					if( dataList.size() == 0) break;

					try (Connection connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD)) {
						insertDataToDatabase(connection,dataList);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
				catch (Exception e)
				{
					throw new RuntimeException(e);
				}

			}
		}


	}

	private static String fetchXmlContent(String url) throws Exception
	{
		URLConnection connection = new URL(url).openConnection();
		System.out.println(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder xmlContent = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			xmlContent.append(inputLine);
		}
		in.close();
		return xmlContent.toString();
	}

	private static List<Data> parseXml(String xml) throws ParserConfigurationException, IOException, SAXException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		NodeList trList = document.getElementsByTagName("ROW");
		List<Data> list = new ArrayList<>();

		for (int i = 0; i < trList.getLength(); i++)
		{
			Element trElement = (Element) trList.item(i);
			String[] data = trElement.getTextContent().trim().split("\t\t\t");
			list.add(new Data(data));
		}
		return list;
	}

	static class Data
	{
		private String hs;
		private String mti;
		private String sitc;
		private String name;
		private String indu;
		private String use;

		public Data(String[] data)
		{
			hs = data[0];
			mti = data[1];
			sitc = data[2];
			name = data[3];
			indu = data[4];
			use = data[5];
		}


	}



	private static void insertDataToDatabase(Connection connection,List<Data>dataList)
	{
		// SQL 쿼리 작성
		StringBuilder sql = new StringBuilder("INSERT INTO code_map VALUES (?,?,?,?,?,?)");

		for(int i = 1 ; i < dataList.size() ;i++){
			sql.append(",(?,?,?,?,?,?)");
		}

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString()))
		{
			// 데이터 삽입
			int c = 1;
			for(int i = 0 ; i < dataList.size() ;i++){
				Data d = dataList.get(i);
				preparedStatement.setString(c++, d.hs);
				preparedStatement.setString(c++, d.mti);

				preparedStatement.setString(c++, d.sitc);
				preparedStatement.setString(c++, d.name);
				preparedStatement.setString(c++, d.indu);
				preparedStatement.setString(c++, d.use);
			}
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}




