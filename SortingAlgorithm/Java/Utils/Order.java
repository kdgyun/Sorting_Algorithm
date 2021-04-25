package Utils;

/**
 * 
 * @author kdgyun
 * 
 * @version 1.1.0
 * @since 1.1.0
 *
 * {@link https://st-lab.tistory.com}
 * {@link https://github.com/kdgyun}
 *
 */

import java.util.Comparator;

public class Order {

	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> reverseOrder() {
		return (Comparator<T>) ReverseComparator.REVERSE;
	}

	private static class ReverseComparator implements Comparator<Comparable<Object>> {

		private static final ReverseComparator REVERSE = new ReverseComparator();

		@Override
		public int compare(Comparable<Object> o1, Comparable<Object> o2) {
			return o2.compareTo(o1);
		}
	}

}
