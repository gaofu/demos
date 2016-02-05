package gao.fu.javascript;

/**
 * Demonstrate an auto-increment pattern for the _id field.
 * Created by gaofu on 16-2-5.
 */
public class User {

    private long id;

    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
