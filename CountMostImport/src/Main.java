import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by mumu462 on 2017/1/7.
 */
public class Main {
    public static void main(String arg[]) {
        File f = new File("D:\\去哪儿\\hello\\src");
        ArrayList<String> data = new ArrayList<String>();
        LinkedHashSet<String> datatemp=new LinkedHashSet<String>();
        ArrayList datacount = new ArrayList();
        File[] files = f.listFiles();
        try
        {
            getfile(files,data,datacount,datatemp);
        }
         catch (Exception ex)
         {
             ex.printStackTrace();
          }

        int SIZE=10;
        if (data.size()<10)
        {
            SIZE=data.size();
        }
        MinHeapNode[] nodes = new MinHeapNode[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            MinHeapNode temp = new MinHeapNode(Integer.parseInt(datacount.get(i).toString()), i);
            nodes[i] = temp;
        }
        MinHeap heap = new MinHeap(nodes, SIZE);
        for (int i = SIZE; i < datacount.size(); i++) {
            MinHeapNode temp = new MinHeapNode(Integer.parseInt(datacount.get(i).toString()), i);
            if (heap.Compare(heap.getNode(0), temp) == true) {
                heap.Pop();
                heap.Push(temp);
            }
        }
        for (int i = 0; i < SIZE; i++)
        {
            int index=heap.getNode(i).getindex();
            System.out.println(data.get(index));
        }
    }
    public static void getfile(File[] files,ArrayList<String> data,ArrayList datacount,LinkedHashSet<String> datatemp) throws Exception
    {
        for (File file : files) {
            if (file.listFiles() != null) {
                getfile(file.listFiles(), data, datacount,datatemp);
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("")) {
                        continue;
                    }
                    if (line.contains("import") == false) {
                        break;
                    }
                    if (datatemp.contains(line)) {
                        int index = data.indexOf(line);
                        datacount.set(index, Integer.parseInt(String.valueOf(datacount.get(index))) + 1);
                    } else {
                        data.add(line);
                        datatemp.add(line);
                        datacount.add(1);
                    }
                }
                reader.close();
            }
        }
    }
}
