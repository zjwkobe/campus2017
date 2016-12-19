import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lihaochuan on 2016/11/23.
 */
public class EffectiveLines {
    public static void main(String[] args) {
        String path="H:/lwmq";
        if(args.length>0) path=args[0];
        System.out.println("有效行："+effectiveLines(new File(path)));

    }

    public static int effectiveLines(File file){
        if(!file.exists()) return 0;
        int count=0;
        if(file.isDirectory()){
            for(File file1:file.listFiles()){
                if(file1.isDirectory()) count+=effectiveLines(file1);
                else count+=EffectiveLinesCount(file1);
            }
        }else count+=EffectiveLinesCount(file);
        return count;
    }

    public static int EffectiveLinesCount(File file){
        int count=0;
        boolean annotation=false;// false:上一行没有/*    true:有/*
        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            for(String str:lines){
                int index=0;
                if(annotation){
                    index=str.indexOf("*/");
                    if(index!=-1){
                        annotation=false;
                        index+=2;
                    }else continue;
                }
                boolean b=false,flag=false;
                for(int i=index;i<str.length();i++){
                    char ch=str.charAt(i);
                    if(ch==' ') continue;
                    if(b) {
                        if(ch=='*'&&(i+1)<str.length()&&str.charAt(i+1)=='/'){
                            b=false;
                            i++;
                        }
                    }else if(ch=='/'){
                        if(str.charAt(i+1)=='*'){
                            b=true;
                            i++;
                        }else break;
                    }else flag=true;
                }
                if(flag) count++;
                annotation=b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

