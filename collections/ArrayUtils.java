package java_pract.collections;
import java.lang.reflect.Array;

public class ArrayUtils {

    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        if (array == null) {
            return null;
        }
        if (filter == null) {
            throw new IllegalArgumentException("Filter must not be null");
        }

        T[] result = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }

        return result;
    }
}
