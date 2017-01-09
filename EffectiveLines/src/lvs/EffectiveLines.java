package lvs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lvs on 2017-12-28.
 */
public class EffectiveLines {

    public static void main(String args[]){
        if(args.length != 1) {
            System.err.println("Usage: EffectiveLines <fileInputPath>");
            System.exit(1);
        }
        File file = new File(args[0]);
        if(file.exists() && file.isFile()){
            if(file.getName().endsWith(".java")){
                System.out.println(countEffectiveLines(file));
            }else{
                System.err.println("The file is not a java file");
            }
        }else{
            System.err.println("The file is not exist");
        }
    }

    public static int countEffectiveLines(File file){
        int effectiveLinesCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while (null != (line = br.readLine())) {
                line = line.trim();
                if (!line.equals("")) {
                    if (!line.startsWith("//")) {
                        if (!(line.startsWith("/*") && line.endsWith("*/"))) {
                            effectiveLinesCount++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return effectiveLinesCount;
    }
}
