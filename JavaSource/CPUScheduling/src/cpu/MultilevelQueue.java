/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.List;
import java.util.Queue;

/**
 * The Class MultilevelQueue that encapsulates
 * algorithm which uses 2 queues: RR as the foreground
 * and FCFS as the background.
 * In this case the foreground queue (RR) has absolute
 * priority over the background queue (FCFS).
 */
public class MultilevelQueue extends Scheduler {
	
	/** The foreground. */
	private int foreground;
	
	/** The background. */
	private int background;

	/**
	 * Instantiates a new multilevel queue.
	 *
	 * @param processes the processes
	 * @param foreground the foreground
	 * @param background the background
	 */
	public MultilevelQueue(List<Process> processes, int foreground, int background) {
		super(processes);
		if (foreground + background != processes.size()) {
			throw new IllegalArgumentException("foreground and background values must "
					+ "give in total the size of list of processes!");
		}
		this.foreground = foreground;
		this.background = background;
	}

	/**
	 * Schedules the processes.
	 * This method just calls the 
	 * next overloaded schedule
	 * method and overrides this 
	 * method from the superclass
	 * because we don't need implementation
	 * of this method from the superclass.
	 */
	/*
	 * (non-Javadoc)
	 * @see cpu.Scheduler#schedule()
	 */
	void schedule() {
		schedule(null, null);
	}

	@Override
	void schedule(Queue<Process> readyQueue, List<Process> processes) {
		RR rr = new RR(this.processes.subList(0, foreground), 3);
		rr.schedule();
		FCFS fcfs = new FCFS(this.processes.subList(foreground, foreground + background));
		fcfs.schedule();
		
		System.out.println("\nResults for " + getClass().getSimpleName() + ":");
		System.out.println("Avg waiting time: " + (rr.avgWaitingTime() + fcfs.avgWaitingTime()) / 2);
		System.out.println("Avg turnaround time: " + (rr.avgTurnaroundTime() + fcfs.avgTurnaroundTime()) / 2);
		
	}

} // end 
