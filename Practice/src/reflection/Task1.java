package reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Вам нужно реализовать метод, который принимает два класса и возвращает список
 * одинаковых методов в этих классах. Одинаковый метод - это метод с одинаковым
 * названием, возвращаемым типом и списком параметров. Вам нужно будет искать
 * методы не только в данных классах, но и в их базовых классах, если такие
 * имеются. Метод будет выглядеть следующим образом: public List
 * <Method> findMethods(Class clz1, Class clz2) {
 */
public class Task1 {

	public static List<Method> findMethods(Class<?> clazz1, Class<?> clazz2) {

		List<Method> methodsInClass1 = findAllMethods(clazz1);
		List<Method> methodsInClass2 = findAllMethods(clazz2);

		List<Method> commonMethods = methodsInClass1.stream()
				.filter(x -> methodsInClass2.stream()
						.anyMatch(y -> x.getName().equals(y.getName())
								&& Arrays.equals(x.getParameterTypes(), y.getParameterTypes())
								&& x.getReturnType().equals(y.getReturnType())))
				.collect(Collectors.toList());

		return commonMethods;
	}

	/** find all methods in class and all parents */
	public static <T> List<Method> findAllMethods(Class<T> clazz) {

		List<Method> methods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));

		if (clazz.getSuperclass() != null) {

			methods.addAll(findAllMethods(clazz.getSuperclass()));
			/* TODO remove duplicates */
		}

		return methods;
	}

	public static void main(String[] args) {
		// print methods as " name - parameter types - return type "
		findMethods(ArrayList.class, HashSet.class).stream()
				.forEach(x -> System.out.println(x.getName() + " - "
						+ Arrays.toString(x.getParameterTypes()) + " - " + x.getReturnType()));

	}

}
