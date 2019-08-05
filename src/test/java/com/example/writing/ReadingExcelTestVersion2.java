package com.example.writing;

import com.example.models.DummyModelVersion2;
import com.example.service.ReadingServiceImpl;

import java.util.List;

public class ReadingExcelTestVersion2 {

    private static ReadingServiceImpl readingService = new ReadingServiceImpl();


    public static void main(String[] args) throws Exception {
        List<Object> list = readingService.getData("/home/himanshu/Documents/generated.xls", DummyModelVersion2.class);
        for (Object row : list){
            DummyModelVersion2 row2 = (DummyModelVersion2) row;
            System.out.println(row2);
        }
    }
}
