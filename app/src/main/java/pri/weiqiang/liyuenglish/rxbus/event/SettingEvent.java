package pri.weiqiang.liyuenglish.rxbus.event;

public class SettingEvent {

    private int msg;

    public SettingEvent(int msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SettingEvent{" +
                "msg=" + msg +
                '}';
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
