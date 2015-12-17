package gao.fu.model;

import com.fasterxml.jackson.annotation.JsonView;

public class MessageScale {
    public interface Common {
    }

    public interface WithToken extends Common {
    }

    public interface WithDate extends Common {
    }

    private String token;

    private String name;

    private long size;

    private long count;

    private String date;

    @JsonView(WithToken.class)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonView(WithToken.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(Common.class)
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @JsonView(Common.class)
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @JsonView(WithDate.class)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageScale{" +
                "size=" + size +
                ", count=" + count +
                ", date='" + date + '\'' +
                '}';
    }
}
