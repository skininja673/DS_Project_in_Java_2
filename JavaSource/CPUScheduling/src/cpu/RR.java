/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.List;
import java.util.Queue;

/**
 * The Class RR that encapsulates
 * the Round Robin algorithm. Used
 * also for MultilevelFeedbackQueue.
 */
public class RR extends Scheduler {

	/** The quantum. */
	private final int QUANTUM;

	/** The next for feedback. */
	private Scheduler nextForFeedback;

	/**
	 * Instantiates a new RR.
	 *
	 * @param processes the processes
	 * @param QUANTUM the quantum
	 */
	public RR(List<Process> processes, int QUANTUM) {
		super(processes);
		this.QUANTUM = QUANTUM;
	}

	/**
	 * This constructor instantiates RR for the
	 * FeedbackMultilevelQueue and initializes it
	 * by the next queue.
	 * 
	 * @param processes the list of processes
	 * @param QUANTUM the given quantum
	 * @param nextForFeedback the next queue
	 */
	public RR(List<Process> processes, int QUANTUM, Scheduler nextForFeedback) {
		this(processes, QUANTUM);
		this.nextForFeedback = nextForFeedback;
	}


	/* (non-Javadoc)
	 * @see cpu.Scheduler#schedule(java.util.Queue, java.util.List)
	 */
	@Override
	void schedule(Queue<Process> readyQueue, List<Process> processes) {
		while (!readyQueue.isEmpty()) {

			Process next = readyQueue.poll();

			next.state = State.RUNNING;						

			next.serviceTime = clock;
			System.out.println(next);

			do {
				clock ++;
				next.executeTime ++; // ????

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
				if (next.executeTime % QUANTUM == 0 && canBePreempted(processes)) { 
					// Then this process should be preempted.

					next.state = State.READY;
					next.arrivalTime = clock;
					System.out.println(next);
				}
				if (next.executeTime == next.getBurstTime()) {
					next.state = State.TERMINATED;
					System.out.println(next);
				}
				if (next.state == State.READY) {
					if (nextForFeedback == null) { // If just for RR.
						readyQueue.add(next); // Add non-terminated process back to this queue.
					} else { // If for MultilevelFeedbackQueue.
						nextForFeedback.readyQueue.add(next); // Add non-terminated process
						// to the next scheduler for MFQ (ready queue).
						
						nextForFeedback.processes.add(next); // Add non-terminated process
						// to the next scheduler for MFQ (list of processes).
						
						processes.remove(next); // Remove process from the list.
						
						nextForFeedback.clock = clock;
					}
				}
			} while (next.state == State.RUNNING || next.state == State.WAITING);
		} // while
	}
} // end of class
