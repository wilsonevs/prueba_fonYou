package com.wilsonevs.configuracion;

public class ContextoEsquema {

    private static ThreadLocal<Object> actualEsquema = new ThreadLocal<>();
    private static ThreadLocal<Object> actualEmpresa = new ThreadLocal<>();
    private static ThreadLocal<Object> actualRol = new ThreadLocal<>();

    public static ThreadLocal<Object> getActualEsquema() {
        return actualEsquema;
    }

    public static void setActualEsquema(ThreadLocal<Object> actualEsquema) {
        ContextoEsquema.actualEsquema = actualEsquema;
    }

    public static ThreadLocal<Object> getActualEmpresa() {
        return actualEmpresa;
    }

    public static void setActualEmpresa(ThreadLocal<Object> actualEmpresa) {
        ContextoEsquema.actualEmpresa = actualEmpresa;
    }

    public static ThreadLocal<Object> getActualRol() {
        return actualRol;
    }

    public static void setActualRol(ThreadLocal<Object> actualRol) {
        ContextoEsquema.actualRol = actualRol;
    }
}
