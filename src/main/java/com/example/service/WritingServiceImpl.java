package com.example.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class WritingServiceImpl {

    public void writeExcel(List<Object> list, String path, Class t) throws IllegalAccessException, IOException {
        Object[][] data = get2DObjectArray(list, t);
        printExcel(data, path, t);
    }

    private void printExcel(Object[][] objects, String path, Class t) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        Row header = sheet.createRow(0);
        createHeaders(header, t, workbook);
        int rowNum = 1;
        Cell cell;
        Row row;
        for (Object[] object : objects) {
            row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : object) {
                cell = row.createCell(colNum++);
                setCellValue(cell, field, workbook);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        workbook.close();
    }

    private CellStyle getCellStyle(XSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        cellStyle.setFont(font);
        return cellStyle;
    }

    private void setCellValue(Cell cell, Object field, XSSFWorkbook workbook) {
        CellStyle cellStyle = getCellStyle(workbook);
        if(field != null){
            if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            } else if (field instanceof Double) {
                cell.setCellValue((Double) field);
            } else if (field instanceof Date) {
                CreationHelper createHelper = workbook.getCreationHelper();
                cellStyle.setDataFormat(
                        createHelper.createDataFormat().getFormat("mm/dd/yyyy hh:mm:ss"));
                cell.setCellValue((Date) field);
            } else if (field instanceof Boolean) {
                cell.setCellValue((Boolean) field);
            } else {
                cell.setCellValue(field.toString());
            }
        }else{
            cell.setCellValue("");
        }
        cell.setCellStyle(cellStyle);
    }

    private void createHeaders(Row row, Class t, Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        int colNum = 0;
        for (Field field : t.getDeclaredFields()) {
            Cell cell = row.createCell(colNum++);
            cell.setCellStyle(style);
            cell.setCellValue(field.getName());
        }
    }

    private Object[][] get2DObjectArray(List<Object> objects, Class t ) throws IllegalAccessException {
        Object[][] sampleArray = new Object[objects.size()][getLengthForClassFieldsArray(t)];
        int count = 0;
        for(Object object : objects){
            sampleArray[count] = getFieldValueArray(object, t);
            count++;
        }
        return sampleArray;
    }

    private Object[] getFieldValueArray(Object object, Class t) throws IllegalAccessException {
        Object[] array = new Object[getLengthForClassFieldsArray(t)];
        int count = 0;
        for (Field field : t.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(object);
            array[count] = value;
            count++;
        }
        return array;
    }

    private int getLengthForClassFieldsArray(Class t){
        return t.getDeclaredFields().length;
    }
}
