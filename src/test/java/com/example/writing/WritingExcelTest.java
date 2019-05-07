package com.example.writing;

import com.example.models.DummyModel;
import com.example.service.WritingServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class WritingExcelTest {

    private static List<Object> list;

    private static WritingServiceImpl writingService = new WritingServiceImpl();

    private static void prepareList(){
        list = new ArrayList<>();
        list.add(new DummyModel(1, "Ramesh", 10, 'A'));
        list.add(new DummyModel(2, "Suresh", 9, 'D'));
        list.add(new DummyModel(3, "Hitesh", 12, 'B'));
        list.add(new DummyModel(4, "Mahesh", 8, 'A'));
        list.add(new DummyModel(5, "Pankaj", 6, 'E'));
    }

    public static void main(String[] args) throws Exception {
        prepareList();
        writingService.writeExcel(list, "/home/himanshu/Documents/generated2.xls", DummyModel.class);
    }
}
