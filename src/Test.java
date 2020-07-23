import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Instance inst=new Instance("C:\\Users\\air\\Desktop\\标签算法\\r104 - 副本.txt");
        DP dp=new DP(inst);
        dp.dp();
    }
}
