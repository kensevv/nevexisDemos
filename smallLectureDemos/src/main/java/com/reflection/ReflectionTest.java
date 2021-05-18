package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
	public static void main(String[] args) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {  
		try {
			Car car = Car.class.getDeclaredConstructor().newInstance();

			StringBuilder result = new StringBuilder();
			for (Method m : car.getClass().getMethods()) {
				result.append("Method: ");
				result.append(m.getName());
				result.append(m.getReturnType().getName());
				result.append(" With parameters type: ");
				Class<?>[] params = m.getParameterTypes();
				for (int i = 0; i < params.length; i++) {
					result.append(params[i].getTypeName());
					result.append("\n");
				}
			}
			for (Field f : car.getClass().getFields()) {
				result.append("Fields: ");
				result.append(f.getName());
				result.append("Type:");
				result.append(f.getType().getName());
				result.append("\n");
			}
			System.out.println(result);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}