package org.example;

public class FingerTable {
    int nodeId;
    double start;
    double end;
    String nodePort;

    public int getNodeId() {
        return nodeId;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public String getNodePort() {
        return nodePort;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public void setNodePort(String nodePort) {
        this.nodePort = nodePort;
    }

    public FingerTable(int nodeId, String nodePort, double start, double end) {
        this.nodeId = nodeId;
        this.start = start;
        this.end = end;
        this.nodePort= nodePort;
    }
}
