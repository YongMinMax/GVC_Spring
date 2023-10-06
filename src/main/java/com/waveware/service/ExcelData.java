package com.waveware.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelData {
	

    public static void main(String[] args) throws IOException {
        try {
            String fileUrl = "D:\\All\\회사\\00002023\\20230000 원부자재프레임워크\\데이터\\HS, MTI, SITC, 품목명 INDEX (최종).xlsx";
            FileInputStream file = new FileInputStream(fileUrl);
            IOUtils.setByteArrayMaxOverride(Integer.MAX_VALUE);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);


            String jdbcUrl = "jdbc:mysql://192.168.2.81:3306/gvc_data";
            String username = "root";
            String password = "root";

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

                Iterator<Row> rowIterator = sheet.iterator();
                if (rowIterator.hasNext()) {
                    rowIterator.next(); // 
                }
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                  
                    String column1 = getCellValue(row.getCell(0));
                    String column2 = getCellValue(row.getCell(1));
                    String column3 = getCellValue(row.getCell(2));
                    String column4 = getCellValue(row.getCell(3));


                    insertDataIntoDatabase(connection, column1, column2, column3,column4);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // cell 데이터 
    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                DecimalFormat decimalFormat = new DecimalFormat("#");
                return decimalFormat.format(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                return "";
            default:
                throw new IllegalStateException("Unexpected value: " + cell.getCellType());
        }
    }
    private static void insertDataIntoDatabase(Connection connection, String column1, String column2, String column3, String column4) {
       
        if (!isValueExists(connection, "code_hs", column1) || 
            !isValueExists(connection, "code_mti", column2) ||
            !isValueExists(connection, "code_sitc", column3)) {
            return; 
        }

        String title = column4;
        int maxTitleLength = 255;
        title = title.substring(0, Math.min(title.length(), maxTitleLength));
        
        String sql = "INSERT INTO code_map (hs, mti, sitc, title) VALUES (?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, column1);
            preparedStatement.setString(2, column2);
            preparedStatement.setString(3, column3);
            preparedStatement.setString(4, title);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValueExists(Connection connection, String tableName, String value) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE code = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}