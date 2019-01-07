package project.programming.elliajah.studentknows1;

public class Student {

    private String mEmail;
    private String mPass;
    private String mFac;
    private String mSpec;
    private int mAn;

    public Student() {
    }

    public Student( String mEmail, String mPass, String mFac, String mSpec,int mAn) {

        this.mEmail = mEmail;
        this.mPass = mPass;
        this.mFac = mFac;
        this.mSpec = mSpec;
        this.mAn=mAn;
    }

    public String getMEmail() {
        return mEmail;
    }

    public void setMEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getMPass() {
        return mPass;
    }

    public void setMPass(String mPass) {
        this.mPass = mPass;
    }

    public String getMFac() {
        return mFac;
    }

    public void setMFac(String mFac) {
        this.mFac = mFac;
    }

    public String getMSpec() {
        return mSpec;
    }

    public void setMSpec(String mSpec) {
        this.mSpec = mSpec;
    }

    public int getMAn() {
        return mAn;
    }

    public void setMAn(int mAn) {
        this.mAn = mAn;
    }
}
