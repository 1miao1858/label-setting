import java.util.BitSet;

public class Label {
    Instance inst;
    BitSet bs;
    int q;
    double t;
    double c;
    int last_customer;
    Label father;

    public Label(Instance inst,BitSet bs,int q,double t,double c,int last_customer){
        this.inst=inst;
        this.bs=bs;
        this.q=q;
        this.t=t;
        this.c=c;
        this.last_customer=last_customer;
    }

    public void setQ(int q){
        this.q=q;
    }

    public void setT(int t){
        this.t=t;
    }

    public void  setC(int c){
        this.c=c;
    }

    public void setLast_customer(int last_customer){
        this.last_customer=last_customer;
    }

    public void setBs(int n){
        this.bs.set(n);
    }

    public void setFather(Label father){
        this.father=father;
    }

    public boolean dominate(Label other){
        for (int i=0;i<101;i++){
            if(bs.get(i) && !other.bs.get(i)){
                return false;
            }
        }
        if(this.q>other.q){
            return  false;
        }
        if(this.t>other.t){
            return  false;
        }
        if (this.c>other.c){
            return false;
        }
        if(this.last_customer!=other.last_customer){
            return  false;
        }
        if(bs.equals(other) && q==other.q && t==other.t && c==other.c && last_customer==other.last_customer){
            return false;
        }
        return true;
    }

    public boolean extend(int j){
        if(bs.get(j)){
            return false;
        }
        if(q+inst.d[j]>inst.capacity){
            return false;
        }
        if (t+inst.s[last_customer]+inst.time[last_customer][j]>inst.b[j]){
            return  false;
        }
        return true;
    }

    public Label extend1(int j){
        BitSet bitSet=(BitSet) bs.clone();
        bitSet.set(j);
        Label label1=new Label(inst,bitSet,q+inst.d[j],t+inst.s[last_customer]+inst.time[last_customer][j],c+inst.reduced_cost[last_customer][j],j);
        return label1;
    }


}
