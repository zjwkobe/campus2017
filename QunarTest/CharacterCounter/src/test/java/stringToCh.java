/**
 * Created by shuxin.qin on 2016/12/26.
 */
public class stringToCh {
    public static void main(String args[])
    {

        //String b=new String(a,0,1);

        String a ="啊";
        String b = "一";

        String c = "asdfghjkl";
        int [] codePoints = new int[1];
        codePoints[0]=c.codePointAt(1);
        String d = new String(codePoints,0,1);

        System.out.println(d);
        System.out.println(c.codePointAt(1));

        System.out.println(a.compareTo(b));

    }
}
