package models;

import enums.AlgorithmEnum;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
public class FormModel {

    private String fileName = "";
    private String volumePath = "";

    public String getVolumePath() {
        return volumePath;
    }

    public void setVolumePath(String volumePath) {
        this.volumePath = volumePath;
    }

    private long nodes = 0;
    private long edges = 0;
    private AlgorithmEnum algorithm = AlgorithmEnum.CONNECTED_COMPONENTS;
    private int iterations = 1;
    private long fileSize = 0; // in megabytes
    private long ramSize = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize()/(1000*1000*1000); 
    private int cpuNb = 0; 


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public FormModel() {

    }

    public FormModel(String fileName, String volumePath, long nodes, long edges, AlgorithmEnum algorithm,
            int iterations, long fileSize , long ramSize , int cpuNb) {
        this.fileName = fileName;
        this.volumePath = volumePath;
        this.nodes = nodes;
        this.edges = edges;
        this.algorithm = algorithm;
        this.iterations = iterations;
        this.fileSize = fileSize;
        this.ramSize=ramSize;
        this.cpuNb=cpuNb;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filePath) {
        this.fileName = filePath;
    }

    public long getNodes() {
        return nodes;
    }

    public void setNodes(long nodes) {
        this.nodes = nodes;
    }

    public long getEdges() {
        return edges;
    }

    public void setEdges(long edges) {
        this.edges = edges;
    }
    public long getRamSize() {
        return ramSize;
    }

    public void setRamSize(long ramSize) {
        this.ramSize = ramSize;
    }
    public int getCpuNb() {
        return cpuNb;
    }

    public void setCpuNb(int cpuNb) {
        this.cpuNb = cpuNb;
    }

    public AlgorithmEnum getAlgorithm() {
        return algorithm;
    }

    // TODO : probably move it to Lookup class
    public int getAlgorithmCode(AlgorithmEnum algorithm) {
        switch (algorithm) {
            case BFS:
                return 0 ;
            case PAGE_RANK:
                return 1 ;
            case CONNECTED_COMPONENTS:
                return 2 ;
            case TRIANGLE_COUNTING:
                return 3 ;
            default:
                System.err.println("Invalid Input");
                System.exit(1);
                break ;
        }
        return -1 ;
    }

    public void setAlgorithm(AlgorithmEnum algorithm) {
        this.algorithm = algorithm;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public String toString() {
        return "FormModel [fileName=" + fileName + ", volumePath=" + volumePath + ", nodes=" + nodes + ", edges="
                + edges + ", algorithm=" + algorithm + ", iterations=" + iterations + ", fileSize=" + fileSize + ", ramSize=" +ramSize +", cpuNb=" +cpuNb+ "]";
    }

   

}
