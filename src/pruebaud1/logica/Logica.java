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
 * Clase lógica
 *
 * @author Jose Javier BO
 */
public class Logica {

    //ATRIBUTOS
    //Id a asignar al próximo trabajo que se cree
    public static int proximaIdTrabajo = 1;

    //id a asignar al próximo empleado que se cree
    public static int proximaIdEmpleado = 1;

    //Lista de trabajos actuales
    private static ArrayList<Trabajo> listaTrabajos = new ArrayList<Trabajo>();

    //Lista de empleados actuales
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    /**
     * Devuevle la lista de trabajos
     *
     * @return La lista de trabajos
     */
    public static ArrayList<Trabajo> getTrabajos() {
        return listaTrabajos;
    }

    /**
     * Devuelve la lista de empleados
     *
     * @return La lista de empleados
     */
    public static ArrayList<Empleado> getEmpleados() {
        return listaEmpleados;
    }

    /**
     * Devuelve el trabajo con numero igual al suministrado
     *
     * @param numero Numero del trabajo
     *
     * @return El trabajo o null si no existe
     */
    public static Trabajo getTrabajo(int numero) {
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getNumero() == numero) {
                return trabajo;
            }
        }
        return null;
    }

    /**
     * Devuelve el empleado con id igual a la suministrada
     *
     * @param id Id del empleado
     *
     * @return El emleado o null si no existe
     */
    public static Empleado getEmpleado(int id) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * Agrega un trabajo a la lista de trabajos
     *
     * @param trabajo El trabajo a agregar
     */
    public static void addTrabajo(Trabajo trabajo) {
        listaTrabajos.add(trabajo);
        proximaIdTrabajo++;
    }

    /**
     * Agrega un empleado a la lista de empleados
     *
     * @param empleado El empleado a agregar
     */
    public static void addEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
        proximaIdEmpleado++;
    }

    /**
     * Modifica un empleado existente en la lista de empleados
     *
     * @param e El empleado a modificar
     */
    public static void modificaEmpleado(Empleado e) {
        Empleado empleado = getEmpleado(e.getId());
        empleado.setNombre(e.getNombre());
        empleado.setApellidos(e.getApellidos());
        empleado.setDni(e.getDni());
        empleado.setSueldo(e.getSueldo());
    }

    /**
     * Elimina un empleado.
     * 
     * @param empleado  El empleado a eliminar
     */
    public static void eliminarEmpleado(Empleado empleado) {
        listaEmpleados.remove(empleado);
        for (Trabajo trabajo : listaTrabajos) {
            trabajo.getEmpleados().remove(empleado);
        }
    }
    
    /**
     * Elimina la asignacion de un empleado en un trabajo
     *
     * @param empleado El empleado
     * @param trabajo El trabajo
     */
    public static void desasignarEmpleadoDeTrabajo(int empleado, int trabajo) {
        getTrabajo(trabajo).desasiganrEmpleado(getEmpleado(empleado));
    }

    /**
     * Asigna un empleado en un trabajo
     *
     * @param empleado El empleado
     * @param trabajo El trabajo
     */
    public static void asignarEmpleadoATrabajo(int empleado, int trabajo) {
        getTrabajo(trabajo).asignarEmpleado(getEmpleado(empleado));
    }


    /**
     * MAIN entrada del programa. Crea la ventana principal y genera unos datos
     * iniciales
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //agregar trabajo y empleados

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
}
