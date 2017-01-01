

/**
 * Created by Readdy on 2016/12/6.
 * @version 0.0.1 2016/12/06
 * @author weidi.jin
 */
public class EffectiveLines {
    public static void main(String[] args) {
        CoreEffectiveLines core = new CoreEffectiveLines("d:\\Overloading.java");
        int effectiveLines = core.effectiveLinesCounter();
    }
}
