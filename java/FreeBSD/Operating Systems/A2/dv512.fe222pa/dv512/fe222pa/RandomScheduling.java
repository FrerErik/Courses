package dv512.fe222pa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class RandomScheduling {
  List<ProcessorSim> registry = new ArrayList<>();
  int totalSimulationTime = 0;
  
	
	public static class ScheduledProcess {
		int processId;
		int burstTime;
		int arrivalMoment;
		
		// The total time the process has waited since its arrival
		int totalWaitingTime;
		
		// The total CPU time the process has used so far
		// (when equal to burstTime -> the process is complete!)
		int allocatedCpuTime;

		public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
			this.processId = processId;
			this.burstTime = burstTime;
			this.arrivalMoment = arrivalMoment;
		}
		
		// ... add further fields and methods, if necessary
	}
		
	// Random number generator that must be used for the simulation
	Random rng;

	// ... add further fields and methods, if necessary
		
	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}
	
	public void reset() {
    
    totalSimulationTime = 0;
    registry.clear();
		// TODO - remove any information from the previous simulation, if necessary
	}
	
	public void runNewSimulation(final boolean isPreemptive, final int timeQuantum,
	    final int numProcesses,
		final int minBurstTime, final int maxBurstTime,
		final int maxArrivalsPerTick, final double probArrival) {
		reset();
    
    
    int tick = 0;
    boolean simulation = true;
    List<ProcessorSim> processors = new ArrayList<>();
    
    int id = 0;
    boolean tqb = false;
    int tqc = 0;
    
    
    // for (int i = 0; i < numProcesses; i++) {
    //   int randomnum = rng.nextInt((maxBurstTime - minBurstTime)) + minBurstTime;

    //   processors.add(new ProcessorSim(id, randomnum, 0));

    // { (0)}
    // { (0) , (1)}
    // { (0) , (1), (2)}
    //   id += 1;
    // }
    // random process picked
    int index = rng.nextInt(11);
    while (simulation) {
      
      tick += 1;
      // chance to add a new process
      double arrivalChance = rng.nextDouble();
    
      
      if (processors.size() <= 9) {
        if (arrivalChance <= probArrival) {

          int addProcess = rng.nextInt(2) + 1;

          if (processors.size() == 9) {
            addProcess = 1;
          } 
          
            for (int i = 0; i < addProcess; i++) {
              processors.add(new ProcessorSim(id, rng.nextInt((maxBurstTime - minBurstTime)) + minBurstTime, tick));
              id += 1;
            }
          }

        }
      
      

      // method produces a valid index so its not outside of the range.
      // p = 0,  i = 0 - 10
      if (processors.size() < 1) {
        ;
      } else {
      
        while (true) {
          if (index > processors.size() - 1) {
            index = rng.nextInt(11);
          } else {
            break;
          }
        }
        
        if (isPreemptive) {
          tqc++;
          if(tqc == timeQuantum) {
            tqc = 0;
            tqb = true;
          }
        }

        if (tqb) {
          index = rng.nextInt(11);
          tqb = false;
          while (true) {
            if (index > processors.size() - 1) {
              index = rng.nextInt(11);
            } else {
              break;
            }
          }
        }


        // current process is given one second.
        ProcessorSim process = processors.get(index);
        if (process.getProcessingTime() < process.getBurstTime()) {
          process.setProcessingTime(process.getProcessingTime() + 1);
        } else {
          index = rng.nextInt(11);
        }

        // if the processing time is equal to the burst time we will consider the process done and hence added to the registry
        for (ProcessorSim processor : processors) {
          if (processor.getProcessingTime() == processor.getBurstTime()) {
            if (!registry.contains(processor)) {
              processor.setTotalWaitingTime(tick - (processor.getArrivalTime()));
              registry.add(processor);
            }
            
          }
        }

        if (registry.size() == 10) {
          simulation = false;
          totalSimulationTime = tick;
        }

      }

    
      
      
      //
      
      
      

      

      
    }

    // we start with 10 processes 
    // we add either 0, 1 or 2 processes after a tick is completed
    // each process starts with a burst value between 1 and 10
    // simulation ends when all processes have been processed
    // create value between 0 and 1 0.1 0.3
    // if the value = or < to 0.75 then create a new process 
    // once you create a process pick a random value between 1 and 2
    // that will determine to total amount of new processes added
    // a process is done whenever the tick is equal to bursttime

		// TODO:
		// 1. Run a simulation as a loop, with one iteration considered as one unit of time (tick)
		// 2. The simulation should involve the provided number of processes "numProcesses"
		// 3. The simulation loop should finish after the all of the processes have completed
		// 4. On each tick, a new process might arrive with the given probability (chance)
		// 5. However, the max number of new processes per tick is limited
		// by the given value "maxArrivalsPerTick"
		// 6. The burst time of the new process is chosen randomly in the interval
		// between the values "minBurstTime" and "maxBurstTime" (inclusive)

		// 7. When the CPU is idle and no process is running, the scheduler
		// should pick one of the available processes *at random* and start its execution
		// 8. If the preemptive version of the scheduling algorithm is invoked, then it should 
		// allow up to "timeQuantum" time units (loop iterations) to the process,
		// and then preempt the process (pause its execution and return it to the queue)
		
		// If necessary, add additional fields (and methods) to this class to 
		// accomodate your solution

		// Note: for all of the random number generation purposes in this method,
		// use "this.rng" !
	}
	
	public void printResults() {
		// TODO:
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
    int totalWaitingTime = 0;
    int averageTime = 0;
    for (ProcessorSim processor: registry) {
      int id = processor.getId();
      int burstTime = processor.getBurstTime();
      
      int arrivalTime = processor.getArrivalTime();
      totalWaitingTime = (processor.getTotalWaitingTime());


      System.out.print(" id: " + id + " burstTime: " + burstTime + " arrivalTime: " + arrivalTime + " totalWaitingTime: " + totalWaitingTime + "\n");
      
    }
    System.out.println("--------------------------------");
    System.out.println("Execution time: " + totalSimulationTime + "\n" + "--------------------------------");
    for (ProcessorSim processor: registry) {
      averageTime += processor.getTotalWaitingTime();
    }
    System.out.println("Average waiting time: " + averageTime/10); 
    
	}
		
	
	public static void main(String args[]) {
		// TODO: replace the seed value below with your birth date, e.g., "20001001"
		final long rngSeed = 20001124;  
		
		
		// Do not modify the code below — instead, complete the implementation
		// of other methods!
		RandomScheduling scheduler = new RandomScheduling(rngSeed);
		
		final int numSimulations = 5;
		
		final int numProcesses = 10;
		final int minBurstTime = 2;
		final int maxBurstTime = 10;
		final int maxArrivalsPerTick = 2;
		final double probArrival = 0.75;
		
		final int timeQuantum = 2; 

		boolean[] preemptionOptions = {false, true};

		for (boolean isPreemptive: preemptionOptions) {

			for (int i = 0; i < numSimulations; i++) {
				System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
					+ " simulation #" + i);

				scheduler.runNewSimulation(
					isPreemptive, timeQuantum,
					numProcesses,
					minBurstTime, maxBurstTime,
					maxArrivalsPerTick, probArrival);

				System.out.println("Simulation results:"
					+ "\n" + "----------------------");	
				scheduler.printResults();

				System.out.println("\n");
			}
		}		
		
	}
	
}