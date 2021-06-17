package com.frantishex.lp.interceptor;

public interface Interceptor<E>
{

	public abstract Interceptor<E> getNextInterceptor();

	public abstract void setNextInterceptor(Interceptor<E> nextInterceptor);

	public abstract boolean hasNext();

	public abstract void process(E Context) throws ProcessingException;

	public abstract void invoke(E Context) throws ProcessingException;

}