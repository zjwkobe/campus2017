import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Airlan on 2016/12/21.
 */
public class Test {

    public static void main(String[]arg0){
        int change;
        String str;
        MostImport imports=new MostImport();
        imports.getCount("MyDiary-5-10");
        ArrayList<String>list=new ArrayList<String>();
        list=MostImport.listString;
        int[]a=new int[list.size()];
        String[]strs=new String[list.size()];
        HashMap<String,String>map=new HashMap<String,String>();
        map=MostImport.map;
        for(int i=0;i<list.size();i++){
            String s=list.get(i);
            String s1=map.get(s);
            a[i]=Integer.parseInt(s1);
            strs[i]=s;
        }
        int i,j,k=0;
        String str2;
        for( i=0;i<list.size();i++) {
            int t = 0;
            for (j = 1; j < list.size()-i; j++) {
                if (a[t] <= a[j])
                    t=j;
            }
            k++;
            change=a[t];
            a[t]=a[j-1];
            a[j-1]=change;
            str2=strs[t];
            strs[t]=strs[j-1];
            strs[j-1]=str2;
          //  System.out.println(t+"");
            System.out.println("第"+(i+1)+"为"+strs[j-1]+"被Import了"+a[j-1]+"次");
            if(k%10==0){
                break;
            }
        }
    }
}

