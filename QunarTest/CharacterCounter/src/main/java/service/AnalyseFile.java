package service;

import model.FileMessage;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by shuxin.qin on 2016/12/26.
 */
public class AnalyseFile {

    public FileMessage analyse(String message) {

        int enCount=0;
        int numCount=0;
        int chCount=0;
        int charCount=0;
        List<String> topCh;
        List<Integer> topCount;
        TreeMap<String,Integer> map =new TreeMap<>();

        int count = message.length();

        for(int i=0;i<count;i++)
        {
            if(message.codePointAt(i)>=48&&message.codePointAt(i)<=57)
            {
                numCount++;
            }
            else if((message.codePointAt(i)>=97&&message.codePointAt(i)<=122)||
                    (message.codePointAt(i)>=65&&message.codePointAt(i)<=90))
            {
                enCount++;
            }
            else if(message.codePointAt(i)>=19968&&message.codePointAt(i)<=40895)
            {
                chCount++;
                //map.
            }
            else
            {
                charCount++;
            }

        }

        FileMessage fileMessage = new FileMessage(enCount,numCount,chCount,charCount);

        return fileMessage;
    }

}
