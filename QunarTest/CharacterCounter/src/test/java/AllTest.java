import model.FileMessage;
import service.AnalyseFile;

import javax.annotation.Resource;

/**
 * Created by shuxin.qin on 2017/1/3.
 */
public class AllTest {


    @Resource
    private AnalyseFile analyseFile;

    public static void main(String args[])
    {
        FileMessage fileMessage = new AnalyseFile().analyse("是地方好I好伐");
    }
}
