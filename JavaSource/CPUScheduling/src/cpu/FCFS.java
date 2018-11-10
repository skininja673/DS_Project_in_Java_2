/**
Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.List;
import java.util.Queue;

/**
 * The Class FCFS that encapsulates
 * FCFS scheduling algorithm.
 */
public class FCFS extends Scheduler {

	/**
	 * Instantiates a new FCFS.
	 *
	 * @param processes the given processes
	 */
	public FCFS(List<Process> processes) {
		super(processes);
	}

	/* (non-Javadoc)
	 * @see cpu.Scheduler#schedule(java.util.Queue, java.util.List)
	 */
	void schedule(Queue<Process> readyQueue, List<Process> processes) {
		
		while (!readyQueue.isEmpty()) { // While ready queue has at 
			// least 1 ready process to execute.

			Process next = readyQueue.poll(); // Get the next process.

			next.state = State.RUNNING;	// Update state.				

			next.serviceTime = clock; // Assign service time.
			System.out.println(next); // Display 
			
			do {
				clock ++; // Run clock.
				next.executeTime ++; // Run CPU.
				
				SequenceTime st;

				// First, we need to check whether the next time unit is for i/o
				// to indicate waiting.
				if ((st = next.getCurrentSeqTime()) instanceof IOTime) {
					IOTime io = (IOTime)st;
					if (io.getDevice().acquire(next)) { // If success.
						System.out.println(next.id + " acquired device " + io.getDevice().getId());

					} else {
						System.out.println(next.id + " waiting for device " + io.getDevice().getId());

					}
					next.state = State.WAITING; // P is either currently performing I/O on 
					// an I/O device or is currently waiting in an I/O queue.

					System.out.println(next);				
				} else { // If CPU.
					Device dev = next.releaseDevice();
					if (dev != null) {
						System.out.println(next.id + " released device " + dev.getId());
					}
				}

				readyQueue.addAll(getArrivingProcesses(processes));

				if (next.executeTime == next.getBurstTime()) {
					next.state = State.TERMINATED;
					System.out.println(next);
				}

			} while (next.state == State.RUNNING || next.state == State.WAITING);
		} // while
	} 
} // end of class
