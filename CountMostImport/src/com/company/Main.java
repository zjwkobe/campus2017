package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        String str="C:\\Users\\ZJW\\IdeaProjects\\CountMostImport\\src\\com\\company";
        CountMostImport count=new CountMostImport(str);
        String mostimportclass=count.getMostImportClassName();
        System.out.println("import最多的类是："+mostimportclass);
    }
}
