package com.kensev.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

public class Handler implements InvocationHandler{
	private Connection original;

	public Handler(Connection original) {
		this.original = original;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(method.getName() == "close") {
			ConnectionPool.releaseConnection((Connection)proxy);
			return null;
		}
		return method.invoke(original, args);
	}
}
