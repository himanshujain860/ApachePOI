package com.example.writing;

import com.example.models.DummyModel;
import com.example.service.ReadingServiceImpl;
import com.example.service.WritingServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingExcelTest {

    private static ReadingServiceImpl readingService = new ReadingServiceImpl();


    public static void main(String[] args) throws Exception {
        List<Object> list = readingService.getData("/home/himanshu/Documents/generated.xls", DummyModel.class);
        for (Object row : list){
            DummyModel row2 = (DummyModel) row;
            System.out.println(row2);
        }
    }
}
