package services;

import models.FormModel;
import models.Lookup;
import models.ModelResponse;

import java.util.ArrayList;
import java.util.List;


public class DeploySolution {
    private ProcessBuilder processBuilder = new ProcessBuilder();
    private FormModel fm ;
    private String framework ; // TODO : change its type to framework enum
    public DeploySolution(ModelResponse modelResponse) {
        this.fm = modelResponse.getFormModel() ;
        this.framework = modelResponse.getFramework() ;
        //this.processBuilder.inheritIO();
        this.processBuilder.redirectErrorStream(true);
        System.out.println(fm);
        List<String> list = new ArrayList<>();

        /*list.add("echo Running container");
        list.add("docker run --rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/data faroukdrira/ligra:1.1") ;
        list.add("echo applying "+fm.getAlgorithm());
        list.add("docker exec deploy-solution /usr/bin/time -v ./algorithms/" + Lookup.lookup[Lookup.LIGRA][fm.getAlgorithmCode(fm.getAlgorithm())]+ " -rounds 0 data/"+fm.getFileName());
        list.add("echo Stopping container");
        list.add("docker stop deploy-solution") ;*/

        /*
        list.add("echo Running container");
        list.add("docker run --rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/home/pfa/data faroukdrira/pfa-graphchi:1.0") ;
        list.add("echo applying "+fm.getAlgorithm());
        list.add("docker exec deploy-solution bash -c 'cd /home/pfa &&  GraphChi/graphChi-binaries/" + Lookup.lookup[Lookup.GRAPH_CHI][fm.getAlgorithmCode(fm.getAlgorithm())]+ " --nshards=2 filetype edgelist file  data/"+fm.getFileName()+"'");
        list.add("echo Stopping container");
        list.add("docker stop deploy-solution") ;
        */

       /*
        list.add("echo Running container");
        list.add("docker run " + "--rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/home/pfa/data faroukdrira/x-stream:1.0") ;
        list.add("echo applying "+fm.getAlgorithm());
        list.add("docker exec deploy-solution bash -c 'cd /home/pfa && ./benchmark_driver -g "+ fm.getFileName() +" -b "+ Lookup.lookup[Lookup.X_STREAM][fm.getAlgorithmCode(fm.getAlgorithm())]+ " -a -p 16 "+"'");
        list.add("echo Stopping container");
        list.add("docker stop deploy-solution") ;
       */

        list.add("echo Running container");
        list.add("docker run " + "--rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/home/pfa/data benrhayemracem/mmap") ;
        list.add("echo applying "+fm.getAlgorithm());
        list.add("docker exec deploy-solution bash -c 'cd /home/pfa && ./benchmark_driver -g "+ fm.getFileName() +" -b "+ Lookup.lookup[Lookup.X_STREAM][fm.getAlgorithmCode(fm.getAlgorithm())]+ " -a -p 16 "+"'");
        list.add("echo Stopping container");
        list.add("docker stop deploy-solution") ;

        // TODO FAROUK : update commands to new docker images of graphchi & xstream
        // TODO FAROUK : handle conversion !!!!!!!!!!!!!
        switch (framework){
            case "LIGRA" :
                list.set(1,"docker run --rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/data faroukdrira/ligra:1.1");
                list.set(3,"docker exec deploy-solution /usr/bin/time -v ./algorithms/" + Lookup.lookup[Lookup.LIGRA][fm.getAlgorithmCode(fm.getAlgorithm())]+ " -rounds 0 data/"+fm.getFileName());
                break ;
            case "GRAPH_CHI" :
                list.set(1,"docker run --rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/home/pfa/data faroukdrira/pfa-graphchi:1.0") ;
                list.set(3,"docker exec deploy-solution bash -c 'cd /home/pfa &&  GraphChi/graphChi-binaries/" + Lookup.lookup[Lookup.GRAPH_CHI][fm.getAlgorithmCode(fm.getAlgorithm())]+ " --nshards=2 filetype edgelist file  data/"+fm.getFileName()+"'");
                break ;
            case "MMAP" :
                // TODO RACEM : c'est claire
                break ;
            case "X_STREAM" :
                list.set(1,"docker run " + "--rm -dt  --name deploy-solution -v "+fm.getVolumePath() +":/home/pfa/data faroukdrira/x-stream:1.0") ;
                list.set(3,"docker exec deploy-solution bash -c 'cd /home/pfa && ./benchmark_driver -g "+ fm.getFileName() +" -b "+ Lookup.lookup[Lookup.X_STREAM][fm.getAlgorithmCode(fm.getAlgorithm())]+ " -a -p 16 "+"'");
            default:
                break ;
        }

        this.processBuilder.command("bash","-c", String.join(";",list));

    }

    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    public void setProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

}
