import java.util.*;

public class DP {
    Instance inst;

    public DP(Instance inst){
        this.inst=inst;
    }

    public void dp(){
        ArrayList<ArrayList<Label>>all=new ArrayList<>();
        ArrayList<ArrayList<Label>>not_extend=new ArrayList<>();
        for (int i=0;i<=101;i++){
            all.add(new ArrayList<Label>());
            not_extend.add(new ArrayList<Label>());
        }
        BitSet bs=new BitSet(102);
        bs.set(0);
        Label l=new Label(inst,bs,0,0,0,0);
        l.father=null;
        not_extend.get(0).add(l);
        all.get(0).add(l);
        ArrayList<Integer> cus=new ArrayList<>();
        cus.add(0);
        while (cus.size()>0){
            System.out.println(cus.size());
            int id=cus.get(0);
            for (Label label:not_extend.get(id)){
                for (int i=1;i<102;i++){
                    if (label.extend(i)){
                        System.out.println(id+"可以向"+i+"扩展");
                        Label label1=label.extend1(i);
                        label1.father=label;
                        ArrayList<Label> re=new ArrayList<>();
                        boolean flag=false;
                        for (Label label2:all.get(label1.last_customer)){
                            if(label2.dominate(label1)){
                                flag=true;
                                break;
                            }else if(label1.dominate(label2)){
                                re.add(label2);
                            }
                        }
                        if(!flag){
                            System.out.println("产生新的标签");
                            not_extend.get(i).add(label1);
                            all.get(i).add(label1);
                            not_extend.get(label1.last_customer).removeAll(re);
                            all.removeAll(re);
                        }else {
                            System.out.println("没有产生新标签");
                        }
                        if(not_extend.get(i).size()>0){
                            System.out.println("加入新的标签");
                            if(!cus.contains(i) && i!=101){
                                cus.add(i);
                            }
                        }
                    }
                }
            }
            not_extend.get(id).clear();
            cus.remove(0);
            System.out.println("此时顾客数是"+cus.size());
        }
        System.out.println("寻找最短路的过程结束，下面输出结果：");
        double length=Integer.MAX_VALUE;
        Label min=null;
        for (Label label:not_extend.get(101)){
            if(label.c<length){
                length=label.c;
                min=label;
            }
        }
        System.out.println("找到的最短路是"+length);
        System.out.println("最短路是：");
        Stack<Label>stack=new Stack<>();
        while (min!=null){
            stack.add(min);
            min=min.father;
        }
        while (stack.size()>0){
            Label ll=stack.pop();
            if(stack.size()>0){
                System.out.print(ll.last_customer+"->");
            }else {
                System.out.print(ll.last_customer);
            }

        }
    }
}
