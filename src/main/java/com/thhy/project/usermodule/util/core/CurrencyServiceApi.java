package com.thhy.project.usermodule.util.core;

@FunctionalInterface
public interface CurrencyServiceApi<T, R> {

	R execute(T t);

	default <V> CurrencyServiceApi<T, V> bridge(CurrencyServiceApi<? super R, ? extends V> serviceApi) {
		return t -> serviceApi.execute(execute(t));
	}

	default <V> CurrencyServiceApi<V, R> bridge2(CurrencyServiceApi<? super V, ? extends T> serviceApi) {
		return v -> execute(serviceApi.execute(v));
	}
}
