import java.util.ArrayList;
import java.util.List;

public class MetaspaceOOM {

    public static void main(String[] args){

        //保持引用，防止自动垃圾回收
        List<String> list = new ArrayList<>();

        int i = 0;

        while(true){
            //通过intern方法向常量池中手动添加常量
            list.add(String.valueOf(i++).intern());
        }
    }
}
