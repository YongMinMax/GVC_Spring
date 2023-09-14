package com.wavewear.csvReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
	
	 public static void main(String[] args) {
	        CSVReader csvReader = new CSVReader();
	        List<List<String>> csvList = csvReader.readCSV();

	    }

	    public List<List<String>> readCSV() {
	        List<List<String>> csvList = new ArrayList<List<String>>();
	        File csv = new File("D:\\workspace\\Spring\\testFolder\\user.csv");
	        BufferedReader br = null;
	        String line = "";

	        try {
	            br = new BufferedReader(new FileReader(csv));
	            while ((line = br.readLine()) != null) { 
	                List<String> aLine = new ArrayList<String>();
	                String[] lineArr = line.split(","); 
	                aLine = Arrays.asList(lineArr);
	                csvList.add(aLine);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (br != null) { 
	                    br.close();
	                }
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return csvList;
	    }
	}

