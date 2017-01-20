package Java.EffectiveLines;

/**
 * Created by hk__lrzy on 2017/1/20.
 */
public class Test {
    public static void main(String []args){
        String filePath = "E:/Test.java";
        int effectiveLines = Analyze.AnalyzeFile(filePath);
        System.out.println(effectiveLines);
    }
}
