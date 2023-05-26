package services;

public class DeploySolution {
    private ProcessBuilder processBuilder = new ProcessBuilder();

    public DeploySolution() {
        String command =  "echo Runnning container ;" 
       + "docker run --rm -dt  --name deploy-solution -v /home/racem/GL4/PFA/test-graphs:/root -e GRAPH_NAME=web-Google.txt racembenrhayem/mmap ;"
       + "echo Applying ConnectedComponent ; "
       + "docker exec deploy-solution  /usr/bin/time -v java -jar mmap.jar ConnectedComponent web-Google.txt.bin 5105039 ;"
       + "echo Stopping container ;"
        + "docker stop deploy-solution"
       ;

       this.processBuilder.command("bash", "-c", command);

    }

    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    public void setProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

}
