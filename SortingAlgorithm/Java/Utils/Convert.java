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

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Convert {

	private static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
	private static final Character[] EMPTY_CHAR_OBJECT_ARRAY = new Character[0];
	private static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
	private static final Integer[] EMPTY_INT_OBJECT_ARRAY = new Integer[0];
	private static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
	private static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
	private static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];

	public static Byte[] toByteArray(byte[] a) {
		return toByteArray(a, a.length);
	}

	private static final Byte[] toByteArray(byte[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_BYTE_OBJECT_ARRAY;
		}
		final Byte[] res = new Byte[size];
		int i = 0;
		for (byte v : a) {
			res[i++] = v;
		}
		return res;
	}

	public static void tobyteArray(Byte[] a, byte[] dest) {
		tobyteArray(a, dest, dest.length);
	}

	private static final void tobyteArray(Byte[] a, byte[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (byte v : a) {
			dest[i++] = v;
		}
	}

	public static Character[] toCharacterArray(char[] a) {
		return toCharacterArray(a, a.length);
	}

	private static final Character[] toCharacterArray(char[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_CHAR_OBJECT_ARRAY;
		}
		final Character[] res = new Character[size];
		int i = 0;
		for (char v : a) {
			res[i++] = v;
		}
		return res;
	}

	public static void tocharArray(Character[] a, char[] dest) {
		tocharArray(a, dest, dest.length);
	}

	private static final void tocharArray(Character[] a, char[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (char v : a) {
			dest[i++] = v;
		}
	}

	public static Short[] toShortArray(short[] a) {
		return toShortArray(a, a.length);
	}

	private static final Short[] toShortArray(short[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_SHORT_OBJECT_ARRAY;
		}
		final Short[] res = new Short[size];
		int i = 0;
		for (short v : a) {
			res[i++] = v;
		}
		return res;
	}

	public static void toshortArray(Short[] a, short[] dest) {
		toshortArray(a, dest, dest.length);
	}

	private static final void toshortArray(Short[] a, short[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (short v : a) {
			dest[i++] = v;
		}
	}

	public static Integer[] toIntegerArray(int[] a) {
		return toIntegerArray(a, a.length);
	}

	private static final Integer[] toIntegerArray(int[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_INT_OBJECT_ARRAY;
		}
		final Integer[] res = IntStream.of(a).boxed().toArray(Integer[]::new);
		return res;
	}

	public static void tointtArray(Integer[] a, int[] dest) {
		tointtArray(a, dest, dest.length);
	}

	private static final void tointtArray(Integer[] a, int[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (int v : a) {
			dest[i++] = v;
		}
	}

	public static Long[] toLongArray(long[] a) {
		return toLongArray(a, a.length);
	}

	private static final Long[] toLongArray(long[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_LONG_OBJECT_ARRAY;
		}
		final Long[] res = LongStream.of(a).boxed().toArray(Long[]::new);
		return res;
	}

	public static void tolongArray(Long[] a, long[] dest) {
		tolongArray(a, dest, dest.length);
	}

	private static final void tolongArray(Long[] a, long[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (long v : a) {
			dest[i++] = v;
		}
	}

	public static Float[] toFloatArray(float[] a) {
		return toFloatArray(a, a.length);
	}

	private static final Float[] toFloatArray(float[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_FLOAT_OBJECT_ARRAY;
		}
		final Float[] res = new Float[size];
		int i = 0;
		for (float v : a) {
			res[i++] = v;
		}
		return res;
	}

	public static void toflostArray(Float[] a, float[] dest) {
		toflostArray(a, dest, dest.length);
	}

	private static final void toflostArray(Float[] a, float[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (float v : a) {
			dest[i++] = v;
		}
	}

	public static Double[] toDoubleArray(double[] a) {
		return toDoubleArray(a, a.length);
	}

	private static final Double[] toDoubleArray(double[] a, int size) {
		if (a == null) {
			return null;
		} else if (size == 0) {
			return EMPTY_DOUBLE_OBJECT_ARRAY;
		}
		final Double[] res = DoubleStream.of(a).boxed().toArray(Double[]::new);
		return res;
	}

	public static void todoubleArray(Double[] a, double[] dest) {
		todoubleArray(a, dest, dest.length);
	}

	private static final void todoubleArray(Double[] a, double[] dest, int size) {
		if (a == null) {
			throw new NullPointerException();
		} else if (size == 0) {
			return;
		}
		int i = 0;
		for (double v : a) {
			dest[i++] = v;
		}
	}

}
