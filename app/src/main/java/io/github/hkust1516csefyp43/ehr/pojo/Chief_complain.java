package io.github.hkust1516csefyp43.ehr.pojo;

/**
 * Created by Louis on 23/11/15.
 */
public class Chief_complain {
    public String id;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
//        return super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append("; ");
        sb.append("name: ");
        sb.append(name);
        return sb.toString();
    }
}
