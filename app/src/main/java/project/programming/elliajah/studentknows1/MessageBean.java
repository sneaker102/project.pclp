package project.programming.elliajah.studentknows1;

public class MessageBean {
    private String possersorName;
    private String msg;
    private String hour;

    public MessageBean() {

    }

    public MessageBean(String possersorName, String msg, String hour) {
        this.possersorName = possersorName;
        this.msg = msg;
        this.hour = hour;
    }

    public void setPossersorName(String possersorName) {
        this.possersorName = possersorName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPossersorName() {
        return possersorName;
    }

    public String getMsg() {
        return msg;
    }

    public String getHour() {
        return hour;
    }
}
