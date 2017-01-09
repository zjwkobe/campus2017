/**
 * Created by George on 2016-11-02.
 */
public class start {
    public static void main(String[] args)
    {
        for(int i = 0; i < 5; ++i)
        {
            for(int j = 0; j < i; ++j)
            {
                System.out.print(" ");
            }
            for(int k = 0; k < 5 - i; ++k)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
