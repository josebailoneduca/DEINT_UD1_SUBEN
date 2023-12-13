/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package pruebaud1.dto;

import java.util.ArrayList;

/**
 * DTO Trabajo
 *
 * @author Jose Javier BO
 */
public class Trabajo {

    
    //ATRIBUTOS
    int numero;
    String nombre;
    ArrayList<Empleado> empleados;

    
    
    
    
    //METODOS
    
    /**
     * Constructor
     * @param numero
     * @param nombre 
     */
    
    public Trabajo(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        this.empleados = new ArrayList<Empleado>();
    }

    
    /**
     * Asigna un empleado al trabajo.
     * @param empleado  Empleado a asignar
     */    
    public void asignarEmpleado(Empleado empleado) {
        if (!empleados.contains(empleado)) 
            empleados.add(empleado);
        
    }
    
    /**
     * Desasigna un empleado al trabajo
     * @param empleado Empleado a desasignar
     * @return  True si lo ha desasignado y false si no estaba asignado
     */
    public boolean desasiganrEmpleado(Empleado empleado) {
        return empleados.remove(empleado);
    }
    
    // GETTERS / SETTERS
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
