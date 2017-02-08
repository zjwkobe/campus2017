import java.util.ArrayList;

/**
 * Created by mumu462 on 2017/1/7.
 */
public class MinHeap{

    private ArrayList<MinHeapNode> data;
    MinHeap(MinHeapNode[] node,int size) {
        data=new ArrayList<MinHeapNode>();
        for (int i = 0; i < size; i++)
        {
           this.data.add(node[i]);
        }
        for (int i = (size - 2) / 2; i >= 0; i--)
        {
            _AdjustDown(i);
        }
    }
    public void Pop()
    {
        MinHeapNode temp=data.get(0);
        data.set(0,data.get(data.size() - 1));
        data.set(data.size() - 1,temp);
        data.remove(data.size() - 1);
        _AdjustDown(0);
    }
   public void Push(MinHeapNode x)
    {
        data.add(x);
        _AdjustUp(data.size()-1);
    }

    private void _AdjustUp(int child)
    {
        int parent = (child-1)/2;
        while (child > 0)
        {
            if (Compare(data.get(child),data.get(parent)))
            {
                MinHeapNode temp=data.get(parent);
                data.set(parent,data.get(child));
                data.set(child, temp);
                child = parent;
                parent = (child - 1) / 2;
            }
            else
            {
                break;
            }
        }
    }

   private void _AdjustDown(int size)
   {
       int parent = size;
       int child = parent * 2 + 1;
       while (child<data.size())
       {
           if (child + 1 < data.size() && Compare(data.get(child + 1) , data.get(child)))
           {
               child++;
           }
           if (Compare( data.get(child), data.get(parent)))
           {
               MinHeapNode temp=data.get(parent);
               data.set(parent,data.get(child));
               data.set(child,temp);
               parent = child;
               child = parent * 2 + 1;
           }
           else
           {
               break;
           }
       }
   }
  public boolean Compare(MinHeapNode t1,MinHeapNode t2)
  {
      return t1.getValue()<t2.getValue();
  }
    public MinHeapNode getNode(int index)
    {
        return data.get(index);
    }
}
