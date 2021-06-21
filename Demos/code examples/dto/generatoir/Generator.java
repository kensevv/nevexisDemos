package com.frantishex.lwr.generator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.frantishex.lwr.controller.dto.PersonDTO;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;

public class Generator {

	private static final String DTO_PACKAGE = "com.frantishex.lwr.controller.dto";
	private JCodeModel model;
	private JPackage pack;
	private Map<Class<?>, JDefinedClass> mappedTypes;

	public Generator() {
		model = new JCodeModel();
	}

	/**
	 * Generates a dto package in the current project containing the auto generated
	 * DTO classes
	 * 
	 * @param packages
	 *            String[] base packages to be scanned
	 * @throws NoSuchFieldException
	 * @throws SecurityException 
	 */
	public void generate(String... packages) throws NoSuchFieldException, SecurityException {
		pack = model._package(DTO_PACKAGE);
		mappedTypes = new HashMap<>();

		for (String packageName : packages) {
			try {
				map(packageName);
			} catch (JClassAlreadyExistsException e) {
				System.out.println("ERROR : " + e.getMessage());
			}
		}

		initTypes();

		try {
			finish();
		} catch (IOException e) {
			System.out.println("ERROR : " + e.getMessage());
		}
	}

	public Set<Class<?>> map(String prefix) throws JClassAlreadyExistsException {
		List<ClassLoader> classLoadersList = new ArrayList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());
		Reflections reflections = new Reflections(
				new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
						.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
						.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(prefix))));

		Set<Class<?>> allTypes = reflections.getSubTypesOf(Object.class);
		Set<Class<?>> typesWithDTO = new HashSet<>();
		for (Class<?> type : allTypes) {
			if (type.getAnnotation(DTO.class) != null) {
				mappedTypes.put(type, pack._class(type.getSimpleName() + "DTO"));
			}
		}

		return typesWithDTO;
	}

	/**
	 * Creates the fields and methods.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private void initTypes() throws NoSuchFieldException, SecurityException {
		for (Class<?> type : mappedTypes.keySet()) {
			JDefinedClass currentClass = mappedTypes.get(type);
			if (type.getSuperclass() != Object.class) {
				if (mappedTypes.get(type.getSuperclass()) != null) {
					currentClass._extends(mappedTypes.get(type.getSuperclass()));
				}
			}

			for (Field field : type.getDeclaredFields()) {
				if (isExcluded(type, field.getName())) {
					continue;
				}

				JFieldVar variable = null;

				if (mappedTypes.containsKey(field.getType())) {
					variable = currentClass.field(JMod.PRIVATE, mappedTypes.get(field.getType()), field.getName());
				} else {
					variable = currentClass.field(JMod.PRIVATE, field.getType(), field.getName());
				}

				String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
				JMethod getMethod = currentClass.method(JMod.PUBLIC, variable.type(), getMethodName);
				getMethod.body()._return(variable);

				String setMethodName = "set" + getMethodName.substring(3);
				JMethod setMethod = currentClass.method(JMod.PUBLIC, model.VOID, setMethodName);
				setMethod.param(variable.type(), field.getName());
				setMethod.body().assign(JExpr._this().ref(variable.name()), JExpr.ref(variable.name()));
			}

			/* Add error message to base entity DTO */
			if (type.getName().indexOf("BaseEntity") > -1) {
				JFieldVar varField = currentClass.field(JMod.PRIVATE, String.class, "errorMessage");
				String getMethodName = "getErrorMessage";
				JMethod getMethod = currentClass.method(JMod.PUBLIC, varField.type(), getMethodName);
				getMethod.body()._return(varField);

				String setMethodName = "setErrorMessage";
				JMethod setMethod = currentClass.method(JMod.PUBLIC, model.VOID, setMethodName);
				setMethod.param(varField.type(), varField.name());
				setMethod.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));
			}
			
			if (type.getName().indexOf("Lawsuite") > -1 && !(type.getName().indexOf("LawsuiteS") > -1)) {
				
				JFieldVar varField = currentClass.field(JMod.PRIVATE, currentClass.owner().directClass("java.util.Set<PersonDTO> "), "claimentList");
				String getMethodName = "getClaimentList";
				JMethod getMethod = currentClass.method(JMod.PUBLIC, varField.type(), getMethodName);
				getMethod.body()._return(varField);

				String setMethodName = "setClaimentList";
				JMethod setMethod = currentClass.method(JMod.PUBLIC, model.VOID, setMethodName);
				setMethod.param(varField.type(), varField.name());
				setMethod.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));
			}
			if (type.getName().indexOf("Lawsuite") > -1 && !(type.getName().indexOf("LawsuiteS") > -1)) {
				JFieldVar varField = currentClass.field(JMod.PRIVATE, currentClass.owner().directClass("java.util.Set<PersonDTO> "), "defendantList");
				String getMethodName = "getDefendantList";
				JMethod getMethod = currentClass.method(JMod.PUBLIC, varField.type(), getMethodName);
				getMethod.body()._return(varField);

				String setMethodName = "setDefendantList";
				JMethod setMethod = currentClass.method(JMod.PUBLIC, model.VOID, setMethodName);
				setMethod.param(varField.type(), varField.name());
				setMethod.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));
			}
			if (type.getName().indexOf("Lawsuite") > -1 && !(type.getName().indexOf("LawsuiteS") > -1)) {
				JFieldVar varField = currentClass.field(JMod.PRIVATE, currentClass.owner().directClass("java.util.Set<PersonDTO> "), "helpersList");
				String getMethodName = "getHelpersList";
				JMethod getMethod = currentClass.method(JMod.PUBLIC, varField.type(), getMethodName);
				getMethod.body()._return(varField);

				String setMethodName = "setHelpersList";
				JMethod setMethod = currentClass.method(JMod.PUBLIC, model.VOID, setMethodName);
				setMethod.param(varField.type(), varField.name());
				setMethod.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));
			}
		}
	}

	private boolean isExcluded(Class<?> type, String fieldName) {
		for (String excludedFieldName : type.getAnnotation(DTO.class).exclude()) {
			if (excludedFieldName.equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates the structure.
	 * 
	 * @throws IOException
	 */
	private void finish() throws IOException {
		File file = new File("src/main/java");
		file.mkdirs();
		model.build(file);
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Generator g = new Generator();
		g.generate("com.frantishex.lwr.model");

	}
}
