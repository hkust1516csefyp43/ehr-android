package io.github.hkust1516csefyp43.ehr.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Louis on 4/12/15.
 */
public class Status {
    @SerializedName("node")
    public String node;
    @SerializedName("npm")
    public String npm;
    @SerializedName("running_for")
    public String running_for;
    @SerializedName("port")
    public int port;
    @SerializedName("query_count")
    public int query_count;

    public Status(String node, String npm, String running_for, int port, int query_count) {
        this.node = node;
        this.npm = npm;
        this.running_for = running_for;
        this.port = port;
        this.query_count = query_count;
    }

    @Override
    public String toString() {
        return "Status{" +
                "node='" + node + '\'' +
                ", npm='" + npm + '\'' +
                ", running_for='" + running_for + '\'' +
                ", port=" + port +
                ", query_count=" + query_count +
                '}';
    }



    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getRunning_for() {
        return running_for;
    }

    public void setRunning_for(String running_for) {
        this.running_for = running_for;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getQuery_count() {
        return query_count;
    }

    public void setQuery_count(int query_count) {
        this.query_count = query_count;
    }


}
