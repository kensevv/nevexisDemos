package com.frantishex.lp.interceptor;

public class InterceptorChainImpl<E> implements InterceptorChain<E>
{

	private Interceptor<E> firstInterceptor;

	@SafeVarargs
	public InterceptorChainImpl(Interceptor<E>... interceptors) throws ProcessingException {
		if (interceptors.length < 1) {
			throw new ProcessingException("Pass at least one interceptor");
		}

		firstInterceptor = interceptors[0];

		for (int i = 0; i + 1 < interceptors.length; i++) {
			interceptors[i].setNextInterceptor(interceptors[i + 1]);
		}
	}

	@Override
	public void invoke(E context) throws ProcessingException
	{
		firstInterceptor.invoke(context);
	}

}
