package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    Object resolveGeneric(Class<?> serviceClass);

    <T> Object resolveGeneric2(Class<T> serviceClass);
}
