package com.nexus.aml;

public class AMLService {
    public boolean isSafe(double amount) {
        // Si el monto termina en .99 y es > 5000, es sospechoso (regla simple)
        return !(amount > 5000 && (amount % 1 > 0.98));
    }
}