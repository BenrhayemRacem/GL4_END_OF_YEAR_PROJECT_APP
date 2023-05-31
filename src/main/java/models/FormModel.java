package models;

import com.sun.management.OperatingSystemMXBean;
import enums.AlgorithmEnum;
import java.lang.management.ManagementFactory;
public class FormModel {

  private String fileName = "";
  private String volumePath = "";

  public String getVolumePath() { return volumePath; }

  public void setVolumePath(String volumePath) { this.volumePath = volumePath; }

  private long graph_nodes = 0;
  private long graph_edges = 0;
  private AlgorithmEnum algo = AlgorithmEnum.CONNECTED_COMPONENTS;
  private int iterations = 1;
  private long graph_size = 0; // in megabytes
  private long ram =
      ((OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean())
          .getTotalPhysicalMemorySize() /
      (1000 * 1000 * 1000);
  private int cpu = 0;

  public long getFileSize() { return graph_size; }

  public void setFileSize(long fileSize) { this.graph_size = fileSize; }

  public FormModel() {}

  public FormModel(String fileName, String volumePath, long nodes, long edges,
                   AlgorithmEnum algorithm, int iterations, long fileSize,
                   long ramSize, int cpuNb) {
    this.fileName = fileName;
    this.volumePath = volumePath;
    this.graph_nodes = nodes;
    this.graph_edges = edges;
    this.algo = algorithm;
    this.iterations = iterations;
    this.graph_size = fileSize;
    this.ram = ramSize;
    this.cpu = cpuNb;
  }

  public String getFileName() { return fileName; }

  public void setFileName(String filePath) { this.fileName = filePath; }

  public long getNodes() { return graph_nodes; }

  public void setNodes(long nodes) { this.graph_nodes = nodes; }

  public long getEdges() { return graph_edges; }

  public void setEdges(long edges) { this.graph_edges = edges; }
  public long getRamSize() { return ram; }

  public void setRamSize(long ramSize) { this.ram = ramSize; }
  public int getCpuNb() { return cpu; }

  public void setCpuNb(int cpuNb) { this.cpu = cpuNb; }

  public AlgorithmEnum getAlgorithm() { return algo; }

  // TODO : probably move it to Lookup class
  public int getAlgorithmCode(AlgorithmEnum algorithm) {
    switch (algorithm) {
    case BFS:
      return 0;
    case PAGE_RANK:
      return 1;
    case CONNECTED_COMPONENTS:
      return 2;
    case TRIANGLE_COUNTING:
      return 3;

    default:
      System.err.println("Invalid Input");
      System.exit(1);
      break;
    }
    return -1;
  }

  public void setAlgorithm(AlgorithmEnum algorithm) { this.algo = algorithm; }

  public int getIterations() { return iterations; }

  public void setIterations(int iterations) { this.iterations = iterations; }

  @Override
  public String toString() {
    return "FormModel [fileName=" + fileName + ", volumePath=" + volumePath +
        ", graph_nodes=" + graph_nodes + ", graph_edges=" + graph_edges +
        ", algo=" + algo + ", iterations=" + iterations +
        ", graph_size=" + graph_size + ", ram=" + ram + ", cpu=" + cpu + "]";
  }
}
