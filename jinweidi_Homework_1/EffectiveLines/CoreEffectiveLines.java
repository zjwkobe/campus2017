import java.io.File;
import java.util.Scanner;

/**
 * Created by Readdy on 2016/12/6.
 * @version 0.0.3 2017/01/01
 * @author weidi.jin
 */
public class CoreEffectiveLines {
    private String filePath = "";
    private int effectiveLinesCount = 0;

    public CoreEffectiveLines(String aFilePath) {
        this.filePath = aFilePath;
    }

    private boolean isBlankLine(String aLine) {
        if (aLine.trim().length() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isSingleComment(String aLine){
        if (aLine.trim().startsWith("//")) {
            return true;
        }

        return false;
    }

    public int effectiveLinesCounter() {
        File file = new File(this.filePath);
        Scanner javaSrc;

        try {
            javaSrc = new Scanner(file);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("[ Error ] File \""+file.getName()+"\" not found!");
            System.out.println("The following are Stack msg:");
            e.printStackTrace();
            return -1;
        }

        while (true) {

            if ( javaSrc.hasNextLine() ) {
                String lineBuffer = javaSrc.nextLine();

                if (!isBlankLine(lineBuffer) && !isSingleComment(lineBuffer)) {
                    ++effectiveLinesCount;
                }

            } else {
                break;
            }
        }

        System.out.println("total: " + effectiveLinesCount + " effective lines");

        return effectiveLinesCount;
    }
}
