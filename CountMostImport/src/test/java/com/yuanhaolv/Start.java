package com.yuanhaolv;

import com.yuanhaolv.controller.MostImportController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class Start {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        MostImportController controller = (MostImportController)context.getBean("mostImport");
        TreeMap<String, Integer> bags = controller.mostImport();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(bags.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //因为我要进行逆序排序，所以是o2.compare(o1)
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        if (list.size() < 10){
            System.out.println("There are only " + list.size() + " bags:");
            System.out.println("Times \t\t\t\t Bags");
            for (Map.Entry<String, Integer> entry : list){
                System.out.println(entry.getValue() + "\t\t" + entry.getKey());
            }
        }else{
            System.out.println("Times \t\t\t\t Bags");
            for (int i = 0; i < 10; i++){
                System.out.println(list.get(i).getValue() + "\t\t" + list.get(i).getKey());
            }
        }
    }
}
