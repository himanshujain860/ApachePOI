package com.example.writing;

import com.example.models.DummyModelVersion2;
import com.example.service.WritingServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WritingExcelTestVersion2 {

    private static List<Object> list;

    private static WritingServiceImpl writingService = new WritingServiceImpl();

    private static void prepareList(){
        list = new ArrayList<>();
        list.add(new DummyModelVersion2(1, "Ramesh", 10, 'A', true, new Date()));
        list.add(new DummyModelVersion2(2, "Suresh", 9, 'D', false, new Date()));
        list.add(new DummyModelVersion2(3, "Hitesh", 12, 'B', true, new Date()));
        list.add(new DummyModelVersion2(4, "Mahesh", 8, 'A', false, new Date()));
        list.add(new DummyModelVersion2(5, "Pankaj", 6, 'E', true, new Date()));
    }

    public static void main(String[] args) throws Exception {
        prepareList();
        writingService.writeExcel(list, "/home/himanshu/Documents/generated.xls", DummyModelVersion2.class);
    }
}
