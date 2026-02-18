       IDENTIFICATION DIVISION.
       PROGRAM-ID. CORE_BANK.

       DATA DIVISION.
       LINKAGE SECTION.
       01  LS-DATA.
           05 LS-ORIGEN    PIC 9(18) COMP-5.
           05 LS-DESTINO   PIC 9(18) COMP-5.
           05 LS-MONTO     USAGE COMP-2.
           05 LS-STATUS    PIC X(02).

       PROCEDURE DIVISION USING LS-DATA.
           DISPLAY "COBOL: Recibida peticion de cuenta " LS-ORIGEN.
           
           IF LS-MONTO > 5000.00
               MOVE "01" TO LS-STATUS
           ELSE
               MOVE "00" TO LS-STATUS
               DISPLAY "COBOL: Transaccion procesada exitosamente."
           END-IF.
           
           GOBACK.
           