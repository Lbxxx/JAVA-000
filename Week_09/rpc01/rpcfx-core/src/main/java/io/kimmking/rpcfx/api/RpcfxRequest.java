package io.kimmking.rpcfx.api;

public class RpcfxRequest<T> {

    private Class<T> serviceClazz;

  private String serviceClass;

  private String method;

  private Object[] params;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<T> getServiceClazz() {
        return serviceClazz;
    }

    public void setServiceClazz(Class<T> serviceClazz) {
        this.serviceClazz = serviceClazz;
    }
}
