/**
 * Created by mumu462 on 2017/1/7.
 */
public class MinHeapNode {
    private int value;
    private int index;
    MinHeapNode()
    {}

    MinHeapNode(int value,int index)
    {
        this.value = value;
        this.index=index;
    }
    public int getValue()
    {
        return value;
    }
    public int getindex()
    {
         return index;
    }
}
