package api;

import api.DWGraph_DS;
import api.edge_data;

import java.io.Serializable;

public class EdgeData implements edge_data, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int source;
    private int destination;
    private int tag;
    private double weight;
    private String info;

    public EdgeData(int source, int destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.info = "";
        this.tag = 0;
    }

    public EdgeData() {
        this.source =0;
        this.destination = 0;
        this.weight =0;
        this.info = "";
        this.tag = 0;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + getOuterType().hashCode();
//        result = prime * result + destination;
//        result = prime * result + ((info == null) ? 0 : info.hashCode());
//        result = prime * result + source;
//        result = prime * result + tag;
//        long temp;
//        temp = Double.doubleToLongBits(weight);
//        result = prime * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EdgeData)) {
            return false;
        }
        EdgeData other = (EdgeData) obj;


        if (destination != other.destination) {
            return false;
        }
        if (info == null) {
            if (other.info != null) {
                return false;
            }
        } else if (!info.equals(other.info)) {
            return false;
        }
        if (source != other.source) {
            return false;
        }
        if (tag != other.tag) {
            return false;
        }
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public int getSrc() {
        return this.source;
    }

    @Override
    public int getDest() {
        return this.destination;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

}