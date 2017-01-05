package com.qunar.ryan.hao.charactercounter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

/**
 * Created by ryan.hao on 16-12-30.
 */
public class UtilsTest {

    /**
     * 各种字符范围
     */
    @Test
    public void printASCII() {
        // 中文
        /*for (int i = 19960; i < 19999; i++) {
            char c = (char)i;
            System.out.println(i + ":" + c);
        }
        for (int i = 40860; i < 40899; i++) {
            char c = (char)i;
            System.out.println(i + ":" + c);
        }*/
        // 英文标点
        for (int i = 30; i < 50; i++) {
            char c = (char)i;
            System.out.println(i + ":" + c);
        }
        // 中文标点
        for (int i = 65280; i < 65320; i++) {
            char c = (char)i;
            System.out.println(i + ":" + c);
        }
        for (int i = 12280; i < 12300; i++) {
            char c = (char)i;
            System.out.println(i + ":" + c);
        }
        System.out.println((int)'“');
        System.out.println((int)'”');
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("hello1");
        list.add("hello2");
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        iterator.remove();
        System.out.println(iterator.hasNext());
    }

}
