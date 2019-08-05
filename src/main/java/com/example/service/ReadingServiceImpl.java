package com.example.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class ReadingServiceImpl {

    public List<Object> getData(String path, Class model) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        FileInputStream excelFile = new FileInputStream(new File(path));
        List<Object> list = new ArrayList<>();
        Sheet sheet;
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            sheet = workbook.getSheetAt(0);
            List<String> headers = getHeaders(sheet);
            Iterator<Row> rowIterator = sheet.iterator();
            Object objectForRow;
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                objectForRow = model.newInstance();
                if(currentRow.getRowNum()!=0){
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    Cell currentCell;
                    while (cellIterator.hasNext()) {
                        currentCell = cellIterator.next();
                        String key = headers.get(currentCell.getAddress().getColumn());
                        Object value = getValueFromCell(currentCell);
                        Field currentField = model.getDeclaredField(key);
                        boolean ifFieldAccessible = currentField.isAccessible();
                        currentField.setAccessible(true);
                        setFieldValue(currentField, objectForRow, value);
                        currentField.setAccessible(ifFieldAccessible);
                    }
                    list.add(objectForRow);
                }
            }
        }

        return list;
    }

    private void setFieldValue(Field field, Object object, Object value) throws IllegalAccessException {
        if(field.getType() == int.class || field.getType() == Integer.class){
            field.set(object, Double.valueOf(value.toString()).intValue());
        } else if(field.getType() == float.class || field.getType() == Float.class){
            field.set(object, Double.valueOf(value.toString()).floatValue());
        } else if(field.getType() == char.class || field.getType() == Character.class){
            field.set(object, value.toString().charAt(0));
        } else if(field.getType() == boolean.class || field.getType() == Boolean.class){
            field.set(object, Boolean.valueOf(value.toString()));
        } else if(field.getType() == Date.class){
            field.set(object, new Date());
        } else {
            field.set(object, value);
        }
    }

    private Object getValueFromCell(Cell cell){
        Object value;
        if (cell.getCellType() == CellType.FORMULA) {
            value = cell.getCellFormula();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            value = cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            value = cell.getNumericCellValue();
        } else {
            value = cell.getStringCellValue();
        }
        return value;
    }

    private List<String> getHeaders(Sheet sheet){
        List<String> headers = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        Iterator<Cell> cellIterator = headerRow.iterator();
        Cell cell;
        while (cellIterator.hasNext()) {
            cell = cellIterator.next();
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }
}
