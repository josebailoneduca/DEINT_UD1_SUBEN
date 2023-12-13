/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package pruebaud1.logica;

import java.util.ArrayList;
import pruebaud1.dto.Empleado;
import pruebaud1.dto.Trabajo;
import pruebaud1.gui.ventanas.VentanaPrincipal;

/**
 *
 * @author Jose Javier BO
 */
public class Logica {

    public static int proximaIdTrabajo = 1;
    public static int proximaIdEmpleado = 1;

    private static ArrayList<Trabajo> listaTrabajos = new ArrayList<Trabajo>();
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    public static ArrayList<Trabajo> getTrabajos() {
        return listaTrabajos;
    }

    public static ArrayList<Empleado> getEmpleados() {
        return listaEmpleados;
    }

    public static Trabajo getTrabajo(int numero) {
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getNumero()==numero)
                return trabajo;
        }
        return null;
    }

    public static Empleado getEmpleado(int id) {
               for (Empleado empleado : listaEmpleados) {
            if (empleado.getId()==id)
                return empleado;
        }
        return null;
    }

    public static void addTrabajo(Trabajo trabajo) {
        listaTrabajos.add(trabajo);
        proximaIdTrabajo++;
    }

    public static void addEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
        proximaIdEmpleado++;
    }
    public static void modificaEmpleado(Empleado e) {
        Empleado empleado = getEmpleado(e.getId());
        empleado.setNombre(e.getNombre());
        empleado.setApellidos(e.getApellidos());
        empleado.setDni(e.getDni());
        empleado.setSueldo(e.getSueldo());
    }

    public static void eliminarEmpleadoDeTrabajo(int empleado, int trabajo) {
        getTrabajo(trabajo).desasiganrEmpleado(getEmpleado(empleado));
    }

    public static void asignarEmpleadoATrabajo(int empleado, int trabajo) {
        getTrabajo(trabajo).asignarEmpleado(getEmpleado(empleado));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Trabajo t = new Trabajo(proximaIdTrabajo, "Trabajo 1");
        addTrabajo(t);
        Empleado e = new Empleado(proximaIdEmpleado, "Jose Javier", "Bailón", "3983429327s", 2000);
        addEmpleado(e);
        asignarEmpleadoATrabajo(e.getId(), 1);
        Empleado e2 = new Empleado(proximaIdEmpleado, "Victor", "Gutierrez", "98563214s", 2100);
        addEmpleado(e2);
        asignarEmpleadoATrabajo(e2.getId(), 1);

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal vp = new VentanaPrincipal();
                vp.setLocationRelativeTo(null);
                vp.setVisible(true);
            }
        });

    }

    public static void eliminarEmpleado(Empleado empleadoSiendoEditado) {
        listaEmpleados.remove(empleadoSiendoEditado);
        for (Trabajo trabajo : listaTrabajos) {
            trabajo.getEmpleados().remove(empleadoSiendoEditado);
        }
    }



}
