package java_pract.collections;

@FunctionalInterface
public interface Filter<T> {
    T apply(T o);
}