/* Simulation.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa4
 * 5/10/15
 * Determines wait times for certain jobs in a queue
 */

import java.io.*;
import java.util.Scanner;

public class Simulation{

    public static Job getJob(Scanner in){
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
    }

    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter report = null;
        PrintWriter trace = null;
        int m;

        // Check for correct cmd line arguments
        if(args.length < 1){
            System.out.println("Usage: Simulation input_file");
            System.exit(1);
        }

        // Open files and read m jobs from input
        in = new Scanner(new File(args[0]));
        report = new PrintWriter(new FileWriter(args[0]+".rpt"));
        trace = new PrintWriter(new FileWriter(args[0]+".trc"));
        m = Integer.parseInt(in.nextLine());

        // Print info to output files
        report.println("Report file: "+args[0]+".rpt");
        report.println(m+" Jobs:");
        trace.println("Trace file: "+args[0]+".trc");
        trace.println(m+" Jobs:");

        // Initialize backup queue and assign the jobs
        Queue backup = new Queue();
        while(in.hasNextLine()) backup.enqueue(getJob(in));
        
        // Run simulation on n processors
        for(int n=1; n<m; n++){
            Queue[] P = new Queue[n+1];
            int time = 0;
            boolean fin = false;

            // Initialize queue array and assign its jobs
            for(int i=0; i<P.length; i++) P[i] = new Queue();

            // Copy backup into a unprocessed queue
            for(int i=1; i<=backup.length(); i++){
                Queue T = new Queue();
                T.enqueue(backup.peek());
                P[0].enqueue(backup.dequeue());
                backup.enqueue(T.dequeue());
            }

            // If doing more than one processor, resets storage queue
            if(n!=1){
                for(int i=1; i<=P[0].length(); i++){
                    ((Job)P[0].peek()).resetFinishTime();
                    P[0].enqueue((Job)P[0].dequeue());
                }
            }

            // Print info to output files
            if(n==1) report.println(backup+"\n");
            if(n==1) trace.println(backup+"\n");
            if(n==1) report.println(
                "***********************************************************");
            trace.println("*****************************");
            trace.println(n+" processor"+(n==1?(":"):("s:")));
            trace.println("*****************************");

            while(!fin){
                int minIndex = -1;
                int minProc = -1;

                // Determines if all jobs have been completed
                if(P[0].length()==backup.length()){
                    if(((Job)P[0].peek()).getFinish()!=Job.UNDEF) fin = true;
                }

                // Print to trace
                trace.println("time="+time);
                for(int i=0; i<P.length; i++) trace.println(i+": "+P[i]);
                trace.println();

                // Determine which job(s) needs to move
                while(minIndex == -1 && minProc == -1){
                    if(P[0].isEmpty() || ((Job)P[0].peek()).getFinish()!=-1) break;
                    else{
                        minIndex = ((Job)P[0].peek()).getArrival();
                        minProc = 0;
                    }
                }
                for(int i=1; i<P.length; i++){
                    if(P[i].isEmpty()) continue;
                    if(minIndex == -1 && minProc == -1){
                        minIndex = ((Job)P[i].peek()).getFinish();
                        minProc = i;
                    }else if(minIndex>((Job)P[i].peek()).getFinish()){
                        minIndex = ((Job)P[i].peek()).getFinish();
                        minProc = i;
                    }
                }

                time = minIndex;
                // Takes job(s) finished in processor, moves to storage
                if(minProc > 0) P[0].enqueue((Job)P[minProc].dequeue());

                // Takes unprocessed jobs in storage, moves to least full processor
                int minSpot = 1;
                if(minProc == 0 && !P[0].isEmpty()){
                    for(int j=1; j<P.length; j++){
                        if(P[j].length()<P[minSpot].length()){
                            minSpot = j;
                        }
                    }
                    P[minSpot].enqueue((Job)P[0].dequeue());
                }

                // Computes finish time for all processing Jobs next in each queue
                for(int i=1; i<P.length; i++){
                    if(!P[i].isEmpty()){
                        if(((Job)P[i].peek()).getFinish() == Job.UNDEF){
                            ((Job)P[i].peek()).computeFinishTime(time);
                        }
                    }
                }
            }
            // Computes total, maximum, and average wait times for all jobs
            int totalWait = 0, maxWait = 0;
            float averageWait = 0;
            Job J;
            for(int i=1; i<=P[0].length(); i++){
                J = ((Job)P[0].peek());
                P[0].enqueue((Job)P[0].dequeue());
                totalWait += J.getWaitTime();
                if(maxWait<J.getWaitTime()) maxWait = J.getWaitTime();
            }
            averageWait = (float)totalWait/(float)P[0].length();
            report.print(n+" processor"+(n==1?": ":"s: "));
            report.print("totalWait="+totalWait+", maxWait="+maxWait+", averageWait=");
            report.format("%.2f", averageWait);
            report.print("\n");
        }
        // Close files
        in.close();
        report.close();
        trace.close();
    }
}
