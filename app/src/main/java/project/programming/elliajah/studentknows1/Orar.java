package project.programming.elliajah.studentknows1;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class Orar {
    private int mAn;
    private String mSpecialitate;
    private int mSapt;
    private Map<String,List<OrarOra>> mDayAndClass;






public Orar(){}

    public Orar(int mAn, String mSpecialitate,int mSapt, Map<String, List<OrarOra>> mDayAndClass) {
        this.mAn = mAn;
        this.mSpecialitate = mSpecialitate;
        this.mSapt=mSapt;
        this.mDayAndClass = mDayAndClass;
    }

    public int getMAn() {
        return mAn;
    }

    public void setMAn(int mAn) {
        this.mAn = mAn;
    }

    public String getMSpecialitate() {
        return mSpecialitate;
    }

    public void setMSpecialitate(String mSpecialitate) {
        this.mSpecialitate = mSpecialitate;
    }

    public int getMSapt() {
        return mSapt;
    }

    public void setMSapt(int mSapt) {
        this.mSapt = mSapt;
    }

    public Map<String, List<OrarOra>> getMDayAndClass() {
        return mDayAndClass;
    }

    public void setMDayAndClass(Map<String, List<OrarOra>> mDayAndClass) {
        this.mDayAndClass = mDayAndClass;
        }

    @Override
    public String toString() {
        StringBuilder  test = new StringBuilder();

        List<OrarOra>  luni=mDayAndClass.get("luni");
        List<OrarOra>  marti=mDayAndClass.get("marti");
        List<OrarOra>  miercuri=mDayAndClass.get("miercuri");
        List<OrarOra>  joi=mDayAndClass.get("joi");
        List<OrarOra>  vineri=mDayAndClass.get("vineri");

        for(OrarOra x : luni){
            test.append(x.toString());
        }
        for(OrarOra x : marti){
            test.append(x.toString());
        }
        for(OrarOra x : miercuri){
            test.append(x.toString());
        }
        for(OrarOra x : joi){
            test.append(x.toString());
        }
        for(OrarOra x : vineri){
            test.append(x.toString());
        }
        return "Orar{" +
                "mAn=" + mAn +
                ", mSpecialitate='" + mSpecialitate + '\'' +
                ", mSapt=" + mSapt +
                ", ore si cursuri =" + test.toString() +
                '}';
    }
}
