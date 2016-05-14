package com.qb.findwork.data;


import java.util.ArrayList;
import java.util.List;

public class ListData {
    public static List<Person> personList;
    public static List<Work> workList;
    public static List<Work> workWorkList=new ArrayList<>();
    public static List<Work> mankWorkList=new ArrayList<>();
    public static void getWork(){
        for (Work work:workList){
            String type=work.getType();
            if(type.equals("1")){
                workWorkList.add(work);
            }else {
                mankWorkList.add(work);
            }
        }

    }
}
