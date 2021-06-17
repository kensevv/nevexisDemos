package com.frantishex.lp.interceptor;

public interface InterceptorChain<E>
{

	public abstract void invoke(E context) throws ProcessingException;

}