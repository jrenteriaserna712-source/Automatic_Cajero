package com.mycompany.bancolombia;

import java.util.Locale;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Cajero {

    private int saldo = 7000000, saldoC = 20000000, retiroD = 2100000;
    private boolean continuar = true;

    public Cajero() {
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldoC() {
        return saldoC;
    }

    public void setSaldoC(int saldoC) {
        this.saldoC = saldoC;
    }

    public int getRetiroD() {
        return retiroD;
    }

    public void setRetiroD(int retiroD) {
        this.retiroD = retiroD;

    }

    public void cajero() {
        while (continuar) {
            try {
                StringBuilder menu = new StringBuilder("MENU CAJERO AUTOMATICO \n\n ");
                menu.append("seleccione una opcion del 1 al 4 asi: \n")
                        .append("1.Consultar saldo \n")
                        .append("2.Consignar dinero \n")
                        .append("3.Retirar dinero \n")
                        .append("4.Salir");
                String opcion = JOptionPane.showInputDialog(null, menu,
                        "CAJERO AUTOMATICO", JOptionPane.QUESTION_MESSAGE);
                if (opcion == null) {
                    if (confirmarSalida()) {
                        continuar = false;
                    }
                    continue;
                }
                int opc = Integer.parseInt(opcion);
                switch (opc) {
                    case 1:
                        consultarSaldo();

                        break;
                    case 2:
                        consignarDinero();
                        break;
                    case 3:
                        retirarDinero();
                        break;
                    case 4:
                        if (confirmarSalida()){
                       continuar = false;
                        }
                     
                        continue;
                    default:
                        JOptionPane.showMessageDialog(null,"Error ingrese numeros del 1 al 4");

                }
            } catch (NumberFormatException e) {

                JOptionPane.showInputDialog("ERROR:debe ingresar numeros del 1 del 4:");
            }
        }

    }

    public boolean confirmarSalida() {
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Deseas Salir?",
                "CONFIRMAR SALIDA", JOptionPane.YES_NO_OPTION);
        return confirmar == JOptionPane.YES_OPTION;
    }

    public String idValidacion() {
        Random random = new Random();
        int numero = random.nextInt(9000) + 1000;
        return "ID de la operacion # " + numero + "\n";

    }

    public void consultarSaldo() {
        String validacion = idValidacion();
        StringBuilder mensaje = new StringBuilder("CONSULTAR SALDO \n");
        mensaje.append(validacion)
                .append("Su saldo es : $")
                .append(String.format("%,d ", saldo));
        JOptionPane.showMessageDialog(null, mensaje, "Consultar saldo \n",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void consignarDinero() {

        JOptionPane.showMessageDialog(null, "NO INGRESAR MONEDAS", "ADVERTENCIA",
                JOptionPane.WARNING_MESSAGE);
        String consigna = JOptionPane.showInputDialog("Monto a consignar:");
        if (consigna == null) {

        }
        try {
            int monto = Integer.parseInt(consigna);
            if (monto % 10000 == 0) {
                saldo += monto;
                JOptionPane.showMessageDialog(null, "Consignacion exitosa \n su nuevo saldo es: "
                        + saldo);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Monto invalido",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
        }
    }

    public void retirarDinero() {
        String saldoRet = JOptionPane.showInputDialog("Monto a retirar:");
        if (saldoRet == null);
        try {
            int retiro = Integer.parseInt(saldoRet);
            if (retiro <= 0) {
                JOptionPane.showMessageDialog(null, "Monto invalido");
            } else if (retiro > retiroD) {
                JOptionPane.showMessageDialog(null, "Excede el limite diario de retiro: " + retiroD);
            } else if (retiro > saldo) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            } else if (retiro % 10000 == 0 && retiro > 9000) {

                saldo -= retiro;
                JOptionPane.showMessageDialog(null,
                        "Retiro exitoso \n Nuevo saldo: " + saldo);
            } else {
                JOptionPane.showMessageDialog(null, "Monto invalido");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero valido", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

        }

    }
}
