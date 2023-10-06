package com.waveware.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class ETFData {
    public static void main(String[] args) throws ParseException {
        String dataPath = "D:\\All\\회사\\00002023\\20230000 원부자재프레임워크\\데이터\\data";
        String jdbcUrl = "jdbc:mysql://192.168.2.81:3306/gvc_data";
        String username = "root";
        String password = "root";

        File dataDirectory = new File(dataPath);
        File[] dataFiles = dataDirectory.listFiles();

        for (File dataFile : dataFiles) {
            if (dataFile.isDirectory()) {
                // ETF명
                String etfName = dataFile.getName();
                File[] yearFolders = dataFile.listFiles();

                for (File yearFolder : yearFolders) {
                    if (yearFolder.isDirectory()) {
                        File[] txtFiles = yearFolder.listFiles((dir, name) -> name.endsWith(".txt"));

                        if (txtFiles != null) {
                            for (File txtFile : txtFiles) {
                                String year = txtFile.getName().substring(0, 8);
                                StringBuilder data = new StringBuilder();
                                try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        data.append(line).append("\n");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                                    insertDataToDatabase(connection, etfName, year, data.toString());
                                    
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }            
        }
        System.out.println("완료");
    }

    private static void insertDataToDatabase(Connection connection, String etfName, String year, String data) {
        // SQL 쿼리 작성
        String sql = "INSERT INTO " + etfName + " (DATE, OPEN, CLOSE, LOW, HIGH) VALUES (?, ?, ?, ?, ?)";
        String[] values = data.split(",");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 데이터 삽입
            preparedStatement.setString(1, year);
            preparedStatement.setDouble(2, Double.parseDouble(values[0]));
            preparedStatement.setDouble(3, Double.parseDouble(values[1]));
            preparedStatement.setDouble(4, Double.parseDouble(values[2]));
            preparedStatement.setDouble(5, Double.parseDouble(values[3]));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // 숫자로 변환할 수 없는 데이터가 있을 경우
            e.printStackTrace();
        }
    }
}
