/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

/**
 * The Class Process that encapsulates
 * a single process.
 */
class Process {
		
		/** The id. */
		String id;
		
		/** The arrival time. */
		int arrivalTime;
		
		/** The service time. */
		int serviceTime;
		
		/** The execute time. Initially
		 * is 0 and is incremented along 
		 * with the clock. */
		int executeTime;
		
		/** The state. */
		State state;
		
		/** The first SequenceTime object in the linked list. */
		SequenceTime seqTimeFirst;
		
		/** The priority. */
		int priority;
		
		/**
		 * Instantiates a new process.
		 *
		 * @param id the id
		 * @param seqTimeFirst the seq time first
		 */
		public Process(String id, SequenceTime seqTimeFirst) {
			this.id = id;
			this.seqTimeFirst = seqTimeFirst;
			state = State.NEW;
		}

		/**
		 * Second constructor to add the arrival time.
		 *
		 * @param id the id
		 * @param seqTimeFirst the seq time first
		 * @param arrivalTime the arrival time
		 */
		public Process(String id, SequenceTime seqTimeFirst, int arrivalTime) {
			this(id, seqTimeFirst);
			this.arrivalTime = arrivalTime;
		}
		
		/**
		 * Third constructor to add the priority.
		 *
		 * @param id the id
		 * @param seqTimeFirst the seq time first
		 * @param arrivalTime the arrival time
		 * @param priority the priority
		 */
		public Process(String id, SequenceTime seqTimeFirst, int arrivalTime, int priority) {
			this(id, seqTimeFirst, arrivalTime);
			this.priority = priority;
		}

		/**
		 * This method calculates the burst timeUnit
		 * for process. Burst timeUnit means the total
		 * timeUnit units needed for the sequence
		 * base (only base, not i/o as it's base
		 * scheduling).
		 * 
		 * @return the burst timeUnit in timeUnit units
		 */
		int getBurstTime() {
			int result = 0;
			SequenceTime st = seqTimeFirst;
			while (st != null) { // Loop through the all items in sequence
				// until the next item is null. For example, we have the 
				// following sequence: cpu1 -> io1 -> cpu -> io2, in which
				// the next item after cpu1 is io1, the next item after io1
				// is cpu, the next item after cpu is io2, but the next
				// item after io2 is null, so the last item is io2.
				
				if (st instanceof CPUTime) {
				result += st.TIME_UNIT;
				}

				st = st.next;
			}
			return result;
		}
		
		/**
		 * Release device.
		 *
		 * @return the device
		 */
		Device releaseDevice() {
			Device result = null;
			SequenceTime st = seqTimeFirst;
			while (st != null) {
				if (st instanceof IOTime) {
					if (((IOTime)st).getDevice().release(this)) {
					result = ((IOTime)st).getDevice();
					}
					break;
				}
				st = st.next;
			}
			return result;
		}

		/**
		 * Method to see what actually
		 * type of time is being
		 * performed: cpu or i/o.
		 *
		 * @return the current seq time
		 */
		SequenceTime getCurrentSeqTime() {
			SequenceTime result = null;
			int curTime = 0;
			SequenceTime st = seqTimeFirst;
			while (st != null) {
				curTime += st.TIME_UNIT;
				if (executeTime <= curTime) {
					result = st;
					break;
				}
				st = st.next;
			}
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return id + ", arrivalTime=" + arrivalTime + ", serviceTime=" + serviceTime + ", " + state;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Process other = (Process) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}


	} // end of class
