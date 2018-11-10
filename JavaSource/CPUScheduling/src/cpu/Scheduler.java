/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * The Class Scheduler that contains
 * all common properties for all 
 * algorithms that extends this class.
 * It's not necessary to use private
 * variables to avoid code redundancy.
 */
public abstract class Scheduler {
	
	/** The processes. */
	List<Process> processes;
	
	/** The ready queue. */
	Queue<Process> readyQueue;

	/** The clock. */
	int clock;

	
	/**
	 * Instantiates a new scheduler.
	 *
	 * @param processes the processes
	 */
	public Scheduler(List<Process> processes) {
		this.processes = processes;
		readyQueue = new LinkedList<>();
	}
	
	/**
	 * Schedules the processes. First, it sorts them out
	 * according to the arrival times, then iterates them until
	 * all processes are terminated and call schedule method
	 * for the particular type of algorithm (subclass).
	 */
	void schedule() {
		Collections.sort(processes, (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));

		processes.forEach(p -> System.out.println(p));

		while (isNonTerminatedProcess(processes)) {
			readyQueue.addAll(getArrivingProcesses(processes));
			readyQueue.forEach(p -> {
				p.state = State.READY;
				System.out.println(p);
			});

			schedule(readyQueue, processes);
			clock ++;
		}

	}

	/**
	 * Abstract method to allow the subclasses
	 * to override it to provide their own
	 * implementation according to the 
	 * algorithm requirements.
	 *
	 * @param readyQueue the ready queue
	 * @param processes the processes
	 */
	abstract void schedule(Queue<Process> readyQueue, List<Process> processes);

	/**
	 * Gets the arriving processes.
	 *
	 * @param processes the processes
	 * @return the arriving processes
	 */
	List<Process> getArrivingProcesses(List<Process> processes) {
		List<Process> result = new ArrayList<>();
		for (Process p: processes) {
			if (p.state == State.NEW && p.arrivalTime == clock) {
				result.add(p);
			}
		}
		return result;
	}

	/**
	 * Returns the average waiting time.
	 *
	 * @return the double value of avg
	 *         waiting time
	 */
	double avgWaitingTime() {
		if (processes.size() == 0) {
			return 0;
		}
		double sum = 0;

		for (Process p: processes) {
			sum += p.serviceTime - p.arrivalTime;
		}

		return sum / processes.size();
	}

	/**
	 * Calculates the average turnaround time
	 * (waiting time + burst time).
	 *
	 * @return the double
	 */
	double avgTurnaroundTime() {
		if (processes.size() == 0) {
			return 0;
		}
		double sum = 0;

		for (Process p: processes) {
			sum += p.serviceTime - p.arrivalTime + p.getBurstTime();
		}

		return sum / processes.size();
	}

	/**
	 * Checks if there are non terminated process.
	 *
	 * @param processes the processes
	 * @return true, if is non terminated process
	 */
	boolean isNonTerminatedProcess(List<Process> processes) {
		boolean result = false;
		for (Process p: processes) {
			if (p.state != State.TERMINATED) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Checks whether the process can be preempted.
	 * True if there are more than 1 non-terminated
	 * process in the list.
	 *
	 * @param processes the processes
	 * @return true, if successful
	 */
	boolean canBePreempted(List<Process> processes) {
		int count = 0;
		for (Process p: processes) {
			if (p.state != State.TERMINATED) {
				count ++;
			}
		}
		return count > 1;
	}


} // end of class
