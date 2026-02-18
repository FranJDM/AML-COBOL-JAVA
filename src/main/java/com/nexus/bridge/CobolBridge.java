package com.nexus.bridge;

public class CobolBridge {
    // Definimos el método nativo (se implementará en C después)
    public native String callCobolCore(long origin, long dest, double amount);

    static {
        System.loadLibrary("nativelink"); 
    }
}