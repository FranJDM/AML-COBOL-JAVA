package com.nexus.api;

import com.nexus.aml.AMLService;
import com.nexus.bridge.CobolBridge;

public class TransactionController {

    private final AMLService amlService = new AMLService();
    private final CobolBridge cobolBridge = new CobolBridge();

    /**
     * Simula la entrada de una petición REST/JSON.
     * En un proyecto real de Spring Boot, esto llevaría @PostMapping.
     */
    public TransactionResponse processTransfer(long originId, long destId, double amount) {
        
        // 1. Capa de Prevención de Fraude (Java)
        if (!amlService.isSafe(amount)) {
            return new TransactionResponse("RECHAZADA", "ALERTA AML: Monto sospechoso o fuera de límites.");
        }

        // 2. Capa de Ejecución Contable (COBOL)
        try {
            // Llamamos al método nativo que conecta con el core.cbl
            String cobolStatus = cobolBridge.callCobolCore(originId, destId, amount);

            if ("00".equals(cobolStatus)) {
                return new TransactionResponse("EXITOSA", "Procesado por Core Bancario COBOL.");
            } else {
                return new TransactionResponse("FALLIDA", "Error en Core: Código " + cobolStatus);
            }
        } catch (UnsatisfiedLinkError e) {
            return new TransactionResponse("ERROR", "Librería nativa COBOL no encontrada en el sistema.");
        } catch (Exception e) {
            return new TransactionResponse("ERROR", "Error crítico: " + e.getMessage());
        }
    }

    // Método main para que puedas probarlo sin necesidad de montar un servidor web entero
  public static void main(String[] args) {
    TransactionController api = new TransactionController();

    // Prueba 1: Transacción Normal
    System.out.println("--- PRUEBA 1: Transacción Normal ---");
    api.processTransfer(1001, 2002, 1500.50);

    System.out.println("\n------------------------------------\n");

    // Prueba 2: Transacción Sospechosa (Monto > 5000)
    System.out.println("--- PRUEBA 2: Alerta de Fraude ---");
    api.processTransfer(1001, 9999, 7500.00);
}
}