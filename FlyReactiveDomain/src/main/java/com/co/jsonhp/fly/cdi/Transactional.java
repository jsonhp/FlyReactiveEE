package com.co.jsonhp.fly.cdi;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

@TransactionalIT
@Interceptor
public class Transactional {
	
	@Resource
	UserTransaction tx;
	
	@AroundInvoke
	public Object manageTransaction(InvocationContext context) throws Exception {
		Object result = null;
		boolean notReadOnly = !context.getMethod().getDeclaredAnnotation(TransactionalIT.class).readOnly();
		List<Class<? extends Throwable>> noRollbackFor = Arrays
				.asList(context.getMethod().getDeclaredAnnotation(TransactionalIT.class).noRollbackFor());
		if (notReadOnly)
			tx.begin();
		System.out.println("Starting transaction");
		try {
			result = context.proceed();
		} catch (Throwable e) {
			if (notReadOnly)
				if (noRollbackFor.contains(e))
					tx.rollback();
			throw e;

		}
		if (notReadOnly)
			tx.commit();
		System.out.println("Committing transaction");

		return result;
	}	

}
