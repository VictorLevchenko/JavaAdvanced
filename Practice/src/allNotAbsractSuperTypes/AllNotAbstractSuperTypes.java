package allNotAbsractSuperTypes;

import java.io.Externalizable;
import java.io.ObjectInputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class AllNotAbstractSuperTypes {

	public static void main(String[] args) {
		//test for interface
		System.out.println(allNotAbstractBaseClasses(Externalizable.class));
		//test for class
		System.out.println(allNotAbstractBaseClasses(ObjectInputStream.class));

	}

	/** function return all ancestors for class or interface.
	 * for class return only not abstract ancestors 
	 * @param clazz - class or interface
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<Class<T>> allNotAbstractBaseClasses(Class<T> clazz) {
		List<Class<T>> classes = new ArrayList<>();
		Class<?> cl = clazz;
		if (!cl.isInterface()) {
			while ((cl = cl.getSuperclass()) != null) {
				if (!Modifier.isAbstract(cl.getModifiers())) {
					classes.add((Class<T>) cl);
				}
			}
		} else {
			while (cl.getInterfaces().length != 0) {
				classes.add((Class<T>) (cl = cl.getInterfaces()[0]));
			}
		}
		
		return classes;

	}
}
