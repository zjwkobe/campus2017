package com.yuanhaolv.service;

import com.yuanhaolv.api.IFileLoader;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("fileLoader")
public class FileLoaderImpl implements IFileLoader {
    public StringBuffer loadContent(File file) throws FileNotFoundException, IOException {
        StringBuffer result = new StringBuffer();
        FileReader reader = null;
        BufferedReader bf = null;
        try{
            reader = new FileReader(file);
            bf = new BufferedReader(reader);

            String temp = null;
            while ((temp = bf.readLine()) != null){
                result.append(temp + "\n");
            }
        }catch (FileNotFoundException e){
            System.out.println("Sorry the file you want to statistic is not exist!");
        }catch (IOException e){
            System.out.println("Sorry unexpected error happened!");
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
                if (bf != null){
                    bf.close();
                }
            }catch (Exception e){
                System.out.println("Sorry unexpected error happened during close the file");
            }
        }
        return result;
    }
}
