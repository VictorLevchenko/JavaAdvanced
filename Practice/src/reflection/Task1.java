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

	public static void main(String[] args) {
		// print methods as " name - parameter types - return type "
		findCommonMethods(ArrayList.class, HashSet.class).stream()
				.forEach(x -> System.out.println(x.getName() + " - "
						+ Arrays.toString(x.getParameterTypes()) + " - " + x.getReturnType()));

	}

	/** find all methods in two classes(search all hierarchy) with
	 * the same name, parameter types and return types
	 *
	 * @param clazz1
	 * @param clazz2
	 * @return list of common methods for two classes
	 */
	public static List<Method> findCommonMethods(Class<?> clazz1, Class<?> clazz2) {

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
			
			// remove duplicates(same name, parameter types, return types)
			List<String> signatures = new ArrayList<>();
			List<Method> uniqueMethods = new ArrayList<>();

			for (Method m : methods) {
				String signature = makeMethodSignature(m);
				if (!signatures.contains(signature)) {
					signatures.add(signature);
					uniqueMethods.add(m);
				}
			}
			methods = uniqueMethods;
		}

		return methods;
	}

	private static String makeMethodSignature(Method method) {

		return method.getName() + Arrays.toString(method.getParameterTypes())
				+ method.getReturnType();
	}

}
