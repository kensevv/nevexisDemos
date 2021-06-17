package com.frantishex.lp.interceptor;

public abstract class InterceptorImpl<E> implements Interceptor<E>
{

	private Interceptor<E> nextInterceptor;

	@Override
	public Interceptor<E> getNextInterceptor()
	{
		return nextInterceptor;
	}

	@Override
	public void setNextInterceptor(Interceptor<E> nextInterceptor)
	{
		this.nextInterceptor = nextInterceptor;
	}

	@Override
	public boolean hasNext()
	{
		return nextInterceptor != null;
	}

	@Override
	public final void invoke(E context) throws ProcessingException
	{
		process(context);
		if (hasNext()) {
			getNextInterceptor().invoke(context);
		}
	}

	@Override
	public abstract void process(E context) throws ProcessingException;

}