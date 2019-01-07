package project.programming.elliajah.studentknows1;

public class OrarOra {

    private String mOra;
    private String mClass;

    public OrarOra() {
    }


    public OrarOra(String mOra, String mClass) {

        this.mOra = mOra;
        this.mClass = mClass;

    }


    public String getMOra() {
        return mOra;
    }

    public void setMOra(String mOra) {
        this.mOra = mOra;
    }

    public String getMClass() {
        return mClass;
    }

    public void setMClass(String mClass) {
        this.mClass = mClass;
    }

    @Override
    public String toString() {
        return "OrarOra{" +
                "mOra='" + mOra + '\'' +
                ", mClass='" + mClass + '\'' +
                '}';
    }
}
