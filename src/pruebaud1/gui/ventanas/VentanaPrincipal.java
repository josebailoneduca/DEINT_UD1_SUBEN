/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
Lista de paquetes:
 */
package pruebaud1.gui.ventanas;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import pruebaud1.dto.Trabajo;
import pruebaud1.dto.Empleado;
import pruebaud1.gui.tablemodels.EmpleadosTableModel;
import pruebaud1.gui.tablemodels.TrabajosTableModel;
import pruebaud1.logica.Logica;

/**
 *
 * @author Jose Javier BO
 */
public class VentanaPrincipal extends javax.swing.JFrame implements ListSelectionListener {

    private Trabajo trabajoSiendoEditado = null;
    private Empleado empleadoSiendoEditado = null;
    private boolean operacionesActivadas = false;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        initPropio();
    }

//INICIALIZACION ###############################################################
//<editor-fold defaultstate="collapsed" desc="INICIALIZACION">
    
    
    /**
     * Inicializar estado inicial: -Inicializar tablas -Limpieza formulario
     * insercion
     */
    private void initPropio() {
        inicializarTablaTrabajos();
        inicializarTablaEmpleadosDeTrabajo();
        inicializarTablaTodosEmpleados();
        resetearFormNuevoTrabajo();
    }
    
    /**
     * Actualiza la tabla conformando su modelo e introduciendo los datos
     * necesarios
     */
    private void inicializarTablaTrabajos() {
        //crear modelo
        TrabajosTableModel etm = new TrabajosTableModel(Logica.getTrabajos());
        //establecer el modelo como modelo de la tabla
        this.tblTrabajos.setModel(etm);
        
        //crear sorter
        TableRowSorter<TrabajosTableModel> rowSorter = new TableRowSorter<>(etm);
        this.tblTrabajos.setRowSorter(rowSorter);
        
        //ordenacion por defecto inicial
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
        
        //listener de seleccion de fila
        this.tblTrabajos.getSelectionModel().addListSelectionListener(this);
    }
    
    private void acutalizarTablaTrabajos() {
        //actualizar los datos de la tabla
        TrabajosTableModel etm = (TrabajosTableModel) tblTrabajos.getModel();
        etm.fireTableDataChanged();
    }
    
    /**
     * Actualiza la tabla conformando su modelo e introduciendo los datos
     * necesarios
     */
    private void inicializarTablaEmpleadosDeTrabajo() {
        //listener de seleccion de fila
        this.tblEmpleadosDeTrabajo.getSelectionModel().addListSelectionListener(this);
    }
    
    /**
     * Actualiza la tabla conformando su modelo e introduciendo los datos
     * necesarios
     */
    private void inicializarTablaTodosEmpleados() {
        //crear modelo
        EmpleadosTableModel etm = new EmpleadosTableModel(Logica.getEmpleados());
        //establecer el modelo como modelo de la tabla
        this.tblTodosEmpleados.setModel(etm);
        
        //crear sorter
        TableRowSorter<EmpleadosTableModel> rowSorter = new TableRowSorter<>(etm);
        this.tblTodosEmpleados.setRowSorter(rowSorter);
        
        //ordenacion por defecto inicial
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
        
        //listener de seleccion de fila
        this.tblTodosEmpleados.getSelectionModel().addListSelectionListener(this);
        
    }
    
    private void actualizarTablaTodosEmpleados() {
        //actualizar los datos de la tabla
        EmpleadosTableModel etm = (EmpleadosTableModel) tblTodosEmpleados.getModel();
        etm.fireTableDataChanged();
    }
    
//</editor-fold>
 
  
// MENU ########################################################################
//<editor-fold defaultstate="collapsed" desc="MENU">   

    private void miSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSalirActionPerformed
        if ( confirmar("¿Salir del programa?")) 
            System.exit(0);
    }//GEN-LAST:event_miSalirActionPerformed

    private void miTrabajosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTrabajosActionPerformed
        //mostrar panel de resulados
        CardLayout cl = (CardLayout) this.panelCardsGeneral.getLayout();
        cl.show(panelCardsGeneral, "panelGeneralTrabajos");
    }//GEN-LAST:event_miTrabajosActionPerformed

    private void miEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEmpleadosActionPerformed
        CardLayout cl = (CardLayout) this.panelCardsGeneral.getLayout();
        cl.show(panelCardsGeneral, "panelGeneralEmpleados");
    }//GEN-LAST:event_miEmpleadosActionPerformed

    //</editor-fold>


//TRABAJOS #####################################################################
//<editor-fold defaultstate="collapsed" desc="TRABAJOS">  

    //TRABAJO NUEVO ############################################################
    //<editor-fold defaultstate="collapsed" desc="TRABAJO NUEVO">   
    
    
    private void btnAgregarTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTrabajoActionPerformed
        resetearFormNuevoTrabajo();
    }//GEN-LAST:event_btnAgregarTrabajoActionPerformed

    private void inputNombreTrabajoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNombreTrabajoKeyReleased
        String txt = this.inputNombreTrabajo.getText();
        if (txt == null || txt != "")
            this.btnGuardarTrabajoNuevo.setEnabled(true);
        else
            this.btnGuardarTrabajoNuevo.setEnabled(false);
    }//GEN-LAST:event_inputNombreTrabajoKeyReleased

    private void btnGuardarTrabajoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTrabajoNuevoActionPerformed
        int numero = Integer.parseInt(lbNumeroTrabajo.getText());
        String nombre = inputNombreTrabajo.getText();
        if (nombre.length() > 0) {
            Logica.addTrabajo(new Trabajo(numero, nombre));
            this.acutalizarTablaTrabajos();
            resetearFormNuevoTrabajo();
        }
    }//GEN-LAST:event_btnGuardarTrabajoNuevoActionPerformed
//</editor-fold>


    //TRABAJO ADMINISTRAR EMPLEADOS ############################################
    //<editor-fold defaultstate="collapsed" desc="TRABAJO ADMINISTRAR EMPLEADOS">   
    private void editarTrabajo(int id) {

        this.trabajoSiendoEditado = Logica.getTrabajo(id);
        if (trabajoSiendoEditado == null) {
            return;
        }

        CardLayout cl = (CardLayout) this.panelAccionesTrabajos.getLayout();
        cl.show(panelAccionesTrabajos, "panelGestionEmpleadosTrabajo");
        rellenarTablaEmpleadosDeTrabajo(trabajoSiendoEditado.getEmpleados());
        lbNombreTrabajo.setText(trabajoSiendoEditado.getNombre());

    }

     /**
     * Actualiza la tabla conformando su modelo e introduciendo los datos
     * necesarios
     */
    private void rellenarTablaEmpleadosDeTrabajo(ArrayList<Empleado> empleados) {
        //crear modelo
        EmpleadosTableModel etm = new EmpleadosTableModel(empleados);
        //establecer el modelo como modelo de la tabla
        this.tblEmpleadosDeTrabajo.setModel(etm);

        //crear sorter
        TableRowSorter<EmpleadosTableModel> rowSorter = new TableRowSorter<>(etm);
        this.tblEmpleadosDeTrabajo.setRowSorter(rowSorter);

        //ordenacion por defecto inicial
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
    }



    private void actualizarTablaEmpleadosDeTrabajos() {
        //actualizar los datos de la tabla 
        try {
            EmpleadosTableModel etm = (EmpleadosTableModel) tblEmpleadosDeTrabajo.getModel();
            etm.fireTableDataChanged();
        } catch (ClassCastException ex) {
        }
    }




    private void btnGestionarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarEmpleadosActionPerformed
        int filaSeleccionada = this.tblTrabajos.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;//no hacer nada si no hay fila seleccionada
        }        //Hace conversion segun el modelo de tabla
        int filaCorrecta = this.tblTrabajos.convertRowIndexToModel(filaSeleccionada);
        int idTrabajo = (int) this.tblTrabajos.getValueAt(filaCorrecta, 0);
        editarTrabajo(idTrabajo);

    }//GEN-LAST:event_btnGestionarEmpleadosActionPerformed






    private void btnDesasignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesasignarActionPerformed
        int filaSeleccionada = this.tblEmpleadosDeTrabajo.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;//no hacer nada si no hay fila seleccionada
        }        //Hace conversion segun el modelo de tabla
        int filaCorrecta = this.tblEmpleadosDeTrabajo.convertRowIndexToModel(filaSeleccionada);
        int idEmpleado = (int) this.tblEmpleadosDeTrabajo.getModel().getValueAt(filaCorrecta, 0);
        Logica.desasignarEmpleadoDeTrabajo(idEmpleado, this.trabajoSiendoEditado.getNumero());
        this.actualizarTablaEmpleadosDeTrabajos();
        this.acutalizarTablaTrabajos();
    }//GEN-LAST:event_btnDesasignarActionPerformed
//</editor-fold>

//</editor-fold>

//EMPLEADOS ####################################################################
//<editor-fold defaultstate="collapsed" desc="EMPLEADOS">  
//EMPLEADOS FILTRAR ############################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS FILTRAR">  
    private void btnBuscarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFiltroActionPerformed
        filtrarEmpleados();
    }//GEN-LAST:event_btnBuscarFiltroActionPerformed


    private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased
        if (busqueda.getText().length() > 0)
            btnBuscarFiltro.setEnabled(true);
        else {
            btnBuscarFiltro.setEnabled(false);
            filtrarEmpleados();
        }
    }//GEN-LAST:event_busquedaKeyReleased


    private void filtrarEmpleados() {
        String txtFiltro = this.busqueda.getText();
        int indiceFiltro = 2;
        //gestion del radiobutton group para la seleccion de sexo
        ButtonModel seleccionado = this.rgFiltro.getSelection();
        if (seleccionado == null) {
            return;
        }
        String columna = seleccionado.getActionCommand();
        switch (columna) {
            case "Apellido":
                indiceFiltro = 2;
                break;
            case "DNI":
                indiceFiltro = 3;
                break;
            case "Sueldo":
                indiceFiltro = 4;
                break;
            default:
                throw new AssertionError();
        }

        RowFilter<EmpleadosTableModel, Integer> rf = RowFilter.regexFilter(txtFiltro, indiceFiltro);
        TableRowSorter<EmpleadosTableModel> rs = (TableRowSorter<EmpleadosTableModel>) tblTodosEmpleados.getRowSorter();
        rs.setRowFilter(rf);
    }
//</editor-fold>

//EMPLEADOS OPERACIONES UTILES##################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS OPERACIONES UTILES">  
    private void empleadoSeleccionado(int idEmpleado) {
        empleadoSiendoEditado = Logica.getEmpleado(idEmpleado);
        rellenarFichaEmpleado();
    }


    private void btnOperacionesActDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacionesActDesActionPerformed

        if (this.operacionesActivadas) {
            desactivarOperaciones();
        } else {
            activarOperaciones();
        }
    }//GEN-LAST:event_btnOperacionesActDesActionPerformed


    private void activarOperaciones() {
        this.operacionesActivadas = true;
        btnOperacionesActDes.setText("Operaciones: Desactivar");
        activarOpciones();
    }

    private void desactivarOperaciones() {
            this.operacionesActivadas = false;
            btnOperacionesActDes.setText("Operaciones: Activar");
            desactivarOpciones();
    }


    /**
     * Resetea el formulario de agregar un trabajo
     */
    private void resetearFormNuevoTrabajo() {
        CardLayout cl = (CardLayout) this.panelAccionesTrabajos.getLayout();
        cl.show(panelAccionesTrabajos, "panelAgregarTrabajo");

        this.lbNumeroTrabajo.setText("" + Logica.proximaIdTrabajo);
        this.inputNombreTrabajo.setText("");
        this.btnGuardarTrabajoNuevo.setEnabled(false);
    }




    private void rellenarFichaEmpleado() {
        inputNombreEmpleado.setText(empleadoSiendoEditado.getNombre());
        inputApellidosEmpleado.setText(empleadoSiendoEditado.getApellidos());
        inputDniEmpleado.setText(empleadoSiendoEditado.getDni());
        inputSueldoEmpleado.setText("" + empleadoSiendoEditado.getSueldo());
        inputIdEmpleado.setText("" + empleadoSiendoEditado.getId());

        lbFichaEmpleado.setEnabled(true);
        lbFichaEmpNombre.setEnabled(true);
        lbFichaEmpApellidos.setEnabled(true);
        lbFichaEmpDNI.setEnabled(true);
        lbFichaEmpSueldo.setEnabled(true);
        lbFichaEmpID.setEnabled(true);
        inputNombreEmpleado.setEnabled(true);
        inputApellidosEmpleado.setEnabled(true);
        inputDniEmpleado.setEnabled(true);
        inputSueldoEmpleado.setEnabled(true);
        inputIdEmpleado.setEnabled(true);
        setFichaNoEditable();
    }

    private void vaciarFichaEmpleado() {
        inputNombreEmpleado.setText("");
        inputApellidosEmpleado.setText("");
        inputDniEmpleado.setText("");
        inputSueldoEmpleado.setText("");
        inputIdEmpleado.setText("");
        setFichaNoEditable();
    }

    private void deshabilitarFichaEmpleado() {
        lbFichaEmpleado.setEnabled(false);
        lbFichaEmpNombre.setEnabled(false);
        lbFichaEmpApellidos.setEnabled(false);
        lbFichaEmpDNI.setEnabled(false);
        lbFichaEmpSueldo.setEnabled(false);
        lbFichaEmpID.setEnabled(false);
        inputNombreEmpleado.setEnabled(false);
        inputApellidosEmpleado.setEnabled(false);
        inputDniEmpleado.setEnabled(false);
        inputSueldoEmpleado.setEnabled(false);
        inputIdEmpleado.setEnabled(false);
        setFichaNoEditable();
    }



    private void activarOpciones() {
        rbModificarEmpleado.setEnabled(true);
        rbAnadirEmpleado.setEnabled(true);
        rbAsignarEmpleado.setEnabled(true);
        rbEliminarEmpleado.setEnabled(true);
        this.rgOperacionesEmpleado.setSelected(rbModificarEmpleado.getModel(), true);
        activarOpcionModificarUsuario();
    }

    private void desactivarOpciones() {
        rbModificarEmpleado.setEnabled(false);
        rbAnadirEmpleado.setEnabled(false);
        rbAsignarEmpleado.setEnabled(false);
        rbEliminarEmpleado.setEnabled(false);
        desactivarBotonesOpciones();
        
    }

    private void desactivarBotonesOpciones() {
        btnGuardarCambios.setEnabled(false);
        btnGuardarEmpleado.setEnabled(false);
        btnEliminarEmpleado.setEnabled(false);
        inputNTrabajoEmpleado.setEnabled(false);
        btnAsignarEmpleado.setEnabled(false);
        setFichaNoEditable();

    }


    private void setFichaNoEditable() {
        inputNombreEmpleado.setEditable(false);
        inputApellidosEmpleado.setEditable(false);
        inputDniEmpleado.setEditable(false);
        inputSueldoEmpleado.setEditable(false);
    }

    private void setFichaSiEditable() {
        inputNombreEmpleado.setEditable(true);
        inputApellidosEmpleado.setEditable(true);
        inputDniEmpleado.setEditable(true);
        inputSueldoEmpleado.setEditable(true);
    }

    private Empleado recogerFormularioEmpleado() {
        String nombre = this.inputNombreEmpleado.getText();
        if (nombre.length() < 1) {
            msgError("Nombre no válido");
            return null;
        }

        String apellidos = this.inputApellidosEmpleado.getText();
        if (apellidos.length() < 1) {
            msgError("Apellido no válido");
            return null;
        }

        String dni = this.inputDniEmpleado.getText();
        if (dni.length() < 9) {
            msgError("Dni no válido. Debe tener al menos 9 caracteres");
            return null;
        }

        double sueldo = 0;
        try {
            sueldo = Double.parseDouble(this.inputSueldoEmpleado.getText());
            if (sueldo < 0) {
                throw new Exception();
            }
        } catch (Exception ex) {
            msgError("Sueldo no válido");
            return null;
        }
        int id = Integer.parseInt(this.inputIdEmpleado.getText());

        return new Empleado(id, nombre, apellidos, dni, sueldo);

    }
//</editor-fold>

//EMPLEADOS MODIFICAR ##########################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS OPERACION MODIFICAR">  
    private void activarOpcionModificarUsuario() {
        desactivarBotonesOpciones();
        btnGuardarCambios.setEnabled(true);
        rellenarFichaEmpleado();
        setFichaSiEditable();
    }

    private void rbModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbModificarEmpleadoActionPerformed
        activarOpcionModificarUsuario();
    }//GEN-LAST:event_rbModificarEmpleadoActionPerformed


    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        Empleado e = recogerFormularioEmpleado();
        if (e != null) {
            Logica.modificaEmpleado(e);
            msgInfo("Empleado modificado");
            actualizarTablaTodosEmpleados();
            actualizarTablaEmpleadosDeTrabajos();
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed
//</editor-fold>

//EMPLEADOS ANADIR #############################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS OPERACION ANADIR">  

    private void activarOpcionAnadirUsuario() {
        desactivarBotonesOpciones();
        btnGuardarEmpleado.setEnabled(true);
        vaciarFichaEmpleado();
        inputIdEmpleado.setText(""+Logica.proximaIdEmpleado);
        setFichaSiEditable();
    }

    private void rbAnadirEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAnadirEmpleadoActionPerformed
        activarOpcionAnadirUsuario();
    }//GEN-LAST:event_rbAnadirEmpleadoActionPerformed

    private void btnGuardarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadoActionPerformed
        Empleado e = recogerFormularioEmpleado();
        if (e != null) {
            Logica.addEmpleado(e);
            msgInfo("Empleado añadido");
            actualizarTablaTodosEmpleados();
            actualizarTablaEmpleadosDeTrabajos();
        }
    }//GEN-LAST:event_btnGuardarEmpleadoActionPerformed
//</editor-fold>

//EMPLEADOS ELIMINAR ###########################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS OPERACION ELIMINAR">  
    private void activarOpcionEliminarEmpleado() {
        desactivarBotonesOpciones();
        btnEliminarEmpleado.setEnabled(true);
        rellenarFichaEmpleado();
        setFichaNoEditable();
    }



    private void rbEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEliminarEmpleadoActionPerformed
     activarOpcionEliminarEmpleado();
    }//GEN-LAST:event_rbEliminarEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        if (confirmar("Realmente desea borrar el empleado "+empleadoSiendoEditado.getId()+" "+empleadoSiendoEditado.getNombre()+" "+empleadoSiendoEditado.getApellidos())){
            Logica.eliminarEmpleado(empleadoSiendoEditado);
            msgInfo("Empleado eliminado");
            actualizarTablaTodosEmpleados();
            actualizarTablaEmpleadosDeTrabajos();
        }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed
//</editor-fold>

//EMPLEADOS ASIGNAR ############################################################
    //<editor-fold defaultstate="collapsed" desc="EMPLEADOS OPERACION ASIGNAR">
    private void activarOpcionAsignarTrabajoAEmpleado() {
        desactivarBotonesOpciones();
        btnAsignarEmpleado.setEnabled(true);
        inputNTrabajoEmpleado.setEnabled(true);
        inputNTrabajoEmpleado.setText("");
        lbOperacionesNtrabajo.setEnabled(true);
        rellenarFichaEmpleado();
        setFichaNoEditable();
    }

    private void rbAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAsignarEmpleadoActionPerformed
         activarOpcionAsignarTrabajoAEmpleado();
    }//GEN-LAST:event_rbAsignarEmpleadoActionPerformed

    private void btnAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEmpleadoActionPerformed
        int numeroTrabajo=0;
        try{
        numeroTrabajo = Integer.parseInt(inputNTrabajoEmpleado.getText());
        if (numeroTrabajo==0)
            throw new Exception();
        }catch (Exception ex){
            msgError("Numero de trabajo erroneo");
            return;
        }
        Trabajo t = Logica.getTrabajo(numeroTrabajo);
        if (t==null){
            msgError("El trabajo "+numeroTrabajo+" no existe");
            return;
        }
        
        Logica.asignarEmpleadoATrabajo(empleadoSiendoEditado.getId(),numeroTrabajo);
        msgInfo("Empleado "+empleadoSiendoEditado.getId()+" "+empleadoSiendoEditado.getNombre()+" "+ empleadoSiendoEditado.getApellidos()+" asignado a trabajo "+t.getNumero()+" "+t.getNombre());
        inputNTrabajoEmpleado.setText("");
        acutalizarTablaTrabajos();
    }//GEN-LAST:event_btnAsignarEmpleadoActionPerformed
    //</editor-fold>
//</editor-fold>
    
// LISTENER TABLAS #############################################################
//<editor-fold defaultstate="collapsed" desc="LISTENER TABLAS">
    /*listener clic tablas*/
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        //tabla trabajos
        if (e.getSource().equals(tblTrabajos.getSelectionModel())) {
            if (this.tblTrabajos.getSelectedRow() != -1) {
                btnGestionarEmpleados.setEnabled(true);
            } else {
                btnGestionarEmpleados.setEnabled(false);
            }
        }
        
        //tabla empleados de trabajo
        else if (e.getSource().equals(tblEmpleadosDeTrabajo.getSelectionModel())) {
            if (this.tblEmpleadosDeTrabajo.getSelectedRow() != -1) {
                btnDesasignar.setEnabled(true);
            } else {
                btnDesasignar.setEnabled(false);
            }
        }
        
        //tabla empleados
        else if (e.getSource().equals(tblTodosEmpleados.getSelectionModel())) {
            int filaSeleccionada = this.tblTodosEmpleados.getSelectedRow();
            if (filaSeleccionada != -1) {
                int filaCorrecta = this.tblTodosEmpleados.convertRowIndexToModel(filaSeleccionada);
                int idEmpleado = (int) this.tblTodosEmpleados.getModel().getValueAt(filaCorrecta, 0);
                empleadoSeleccionado(idEmpleado);
            } else {
                vaciarFichaEmpleado();
                deshabilitarFichaEmpleado();
                desactivarOperaciones();
            }
        }
    }
//</editor-fold>

// MENSAJES ####################################################################
//<editor-fold defaultstate="collapsed" desc="MENSAJES INFORMACION">
    private void msgError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void msgInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private boolean confirmar(String msg) {
        Object[] opciones = {"Sí","No"};
        return JOptionPane.showOptionDialog(this,
                msg,
                "Aviso",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "default")==JOptionPane.YES_OPTION;
    }
//</editor-fold>
  
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgFiltro = new javax.swing.ButtonGroup();
        rgOperacionesEmpleado = new javax.swing.ButtonGroup();
        panelCardsGeneral = new javax.swing.JPanel();
        panelGeneralTrabajos = new javax.swing.JPanel();
        panelSuperiorTrabajos = new javax.swing.JPanel();
        lbGestionDeTrabajos = new javax.swing.JLabel();
        panelTablaTrabajos = new javax.swing.JPanel();
        tblTrabajosScroll = new javax.swing.JScrollPane();
        tblTrabajos = new javax.swing.JTable();
        panelOpcionesTrabajos = new javax.swing.JPanel();
        btnAgregarTrabajo = new javax.swing.JButton();
        btnGestionarEmpleados = new javax.swing.JButton();
        panelAccionesTrabajos = new javax.swing.JPanel();
        panelAgregarTrabajo = new javax.swing.JPanel();
        lbAgregarTrabajo = new javax.swing.JLabel();
        panelAccionAgregarTrabajoInterno = new javax.swing.JPanel();
        lbNTrabajoinfo = new javax.swing.JLabel();
        lbNumeroTrabajo = new javax.swing.JLabel();
        lbNombreTrabajoNuevo = new javax.swing.JLabel();
        inputNombreTrabajo = new javax.swing.JTextField();
        btnGuardarTrabajoNuevo = new javax.swing.JButton();
        panelGestionEmpleadosTrabajo = new javax.swing.JPanel();
        panelCentralAccionTrabajosGestionaEmpleados = new javax.swing.JPanel();
        lbGestionEmpleados = new javax.swing.JLabel();
        lbNombreTrabajo = new javax.swing.JLabel();
        jScrollPaneTblEmpleadosDeTrabajo = new javax.swing.JScrollPane();
        tblEmpleadosDeTrabajo = new javax.swing.JTable();
        btnDesasignar = new javax.swing.JButton();
        panelGeneralEmpleados = new javax.swing.JPanel();
        panelSuperiorEmpleados = new javax.swing.JPanel();
        lbGestionDeEmpleados = new javax.swing.JLabel();
        panelTablaEmpleados = new javax.swing.JPanel();
        tblEmpleadosScroll = new javax.swing.JScrollPane();
        tblTodosEmpleados = new javax.swing.JTable();
        panelFiltros = new javax.swing.JPanel();
        lbBuscarPor = new javax.swing.JLabel();
        rbFiltroApellido = new javax.swing.JRadioButton();
        busqueda = new javax.swing.JTextField();
        rbFiltroDNI = new javax.swing.JRadioButton();
        rbFiltroSueldo = new javax.swing.JRadioButton();
        btnBuscarFiltro = new javax.swing.JButton();
        panelFichaYoperacionesEmpleado = new javax.swing.JPanel();
        panelFichaEmpleado = new javax.swing.JPanel();
        lbFichaEmpNombre = new javax.swing.JLabel();
        lbFichaEmpApellidos = new javax.swing.JLabel();
        lbFichaEmpDNI = new javax.swing.JLabel();
        lbFichaEmpSueldo = new javax.swing.JLabel();
        lbFichaEmpID = new javax.swing.JLabel();
        inputNombreEmpleado = new javax.swing.JTextField();
        inputApellidosEmpleado = new javax.swing.JTextField();
        inputDniEmpleado = new javax.swing.JTextField();
        inputSueldoEmpleado = new javax.swing.JTextField();
        inputIdEmpleado = new javax.swing.JTextField();
        lbFichaEmpleado = new javax.swing.JLabel();
        btnOperacionesActDes = new javax.swing.JButton();
        panelOperaciones = new javax.swing.JPanel();
        rbModificarEmpleado = new javax.swing.JRadioButton();
        rbAnadirEmpleado = new javax.swing.JRadioButton();
        rbEliminarEmpleado = new javax.swing.JRadioButton();
        rbAsignarEmpleado = new javax.swing.JRadioButton();
        btnGuardarCambios = new javax.swing.JButton();
        btnGuardarEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        lbOperacionesNtrabajo = new javax.swing.JLabel();
        inputNTrabajoEmpleado = new javax.swing.JTextField();
        btnAsignarEmpleado = new javax.swing.JButton();
        barraDeMenu = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        miEmpleados = new javax.swing.JMenuItem();
        miTrabajos = new javax.swing.JMenuItem();
        miSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(979, 781));

        panelCardsGeneral.setLayout(new java.awt.CardLayout());

        panelGeneralTrabajos.setName("panelGeneralTrabajos"); // NOI18N

        lbGestionDeTrabajos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbGestionDeTrabajos.setText("GESTIÓN DE TRABAJOS");

        tblTrabajos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTrabajosScroll.setViewportView(tblTrabajos);

        javax.swing.GroupLayout panelTablaTrabajosLayout = new javax.swing.GroupLayout(panelTablaTrabajos);
        panelTablaTrabajos.setLayout(panelTablaTrabajosLayout);
        panelTablaTrabajosLayout.setHorizontalGroup(
            panelTablaTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaTrabajosLayout.createSequentialGroup()
                .addComponent(tblTrabajosScroll)
                .addGap(0, 0, 0))
        );
        panelTablaTrabajosLayout.setVerticalGroup(
            panelTablaTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaTrabajosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblTrabajosScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        btnAgregarTrabajo.setText("Agregar Trabajo");
        btnAgregarTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTrabajoActionPerformed(evt);
            }
        });

        btnGestionarEmpleados.setText("GestionarEmpleados");
        btnGestionarEmpleados.setEnabled(false);
        btnGestionarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarEmpleadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesTrabajosLayout = new javax.swing.GroupLayout(panelOpcionesTrabajos);
        panelOpcionesTrabajos.setLayout(panelOpcionesTrabajosLayout);
        panelOpcionesTrabajosLayout.setHorizontalGroup(
            panelOpcionesTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesTrabajosLayout.createSequentialGroup()
                .addGroup(panelOpcionesTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesTrabajosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelOpcionesTrabajosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGestionarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelOpcionesTrabajosLayout.setVerticalGroup(
            panelOpcionesTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesTrabajosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnAgregarTrabajo)
                .addGap(44, 44, 44)
                .addComponent(btnGestionarEmpleados)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSuperiorTrabajosLayout = new javax.swing.GroupLayout(panelSuperiorTrabajos);
        panelSuperiorTrabajos.setLayout(panelSuperiorTrabajosLayout);
        panelSuperiorTrabajosLayout.setHorizontalGroup(
            panelSuperiorTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorTrabajosLayout.createSequentialGroup()
                .addComponent(panelTablaTrabajos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcionesTrabajos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelSuperiorTrabajosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbGestionDeTrabajos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSuperiorTrabajosLayout.setVerticalGroup(
            panelSuperiorTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorTrabajosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbGestionDeTrabajos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSuperiorTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTablaTrabajos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOpcionesTrabajos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAccionesTrabajos.setLayout(new java.awt.CardLayout());

        panelAgregarTrabajo.setName("panelAgregarTrabajo"); // NOI18N

        lbAgregarTrabajo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbAgregarTrabajo.setText("Agregar Trabajo");

        panelAccionAgregarTrabajoInterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        lbNTrabajoinfo.setText("NºTrabajo:");

        lbNombreTrabajoNuevo.setText("Nombre:");

        inputNombreTrabajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputNombreTrabajoKeyReleased(evt);
            }
        });

        btnGuardarTrabajoNuevo.setText("Guardar Trabajo");
        btnGuardarTrabajoNuevo.setEnabled(false);
        btnGuardarTrabajoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTrabajoNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionAgregarTrabajoInternoLayout = new javax.swing.GroupLayout(panelAccionAgregarTrabajoInterno);
        panelAccionAgregarTrabajoInterno.setLayout(panelAccionAgregarTrabajoInternoLayout);
        panelAccionAgregarTrabajoInternoLayout.setHorizontalGroup(
            panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionAgregarTrabajoInternoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombreTrabajoNuevo)
                    .addComponent(lbNTrabajoinfo))
                .addGap(25, 25, 25)
                .addGroup(panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAccionAgregarTrabajoInternoLayout.createSequentialGroup()
                        .addComponent(lbNumeroTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelAccionAgregarTrabajoInternoLayout.createSequentialGroup()
                        .addComponent(inputNombreTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
            .addGroup(panelAccionAgregarTrabajoInternoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardarTrabajoNuevo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelAccionAgregarTrabajoInternoLayout.setVerticalGroup(
            panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionAgregarTrabajoInternoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNTrabajoinfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNumeroTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAccionAgregarTrabajoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreTrabajoNuevo)
                    .addComponent(inputNombreTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarTrabajoNuevo)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelAgregarTrabajoLayout = new javax.swing.GroupLayout(panelAgregarTrabajo);
        panelAgregarTrabajo.setLayout(panelAgregarTrabajoLayout);
        panelAgregarTrabajoLayout.setHorizontalGroup(
            panelAgregarTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarTrabajoLayout.createSequentialGroup()
                .addGap(0, 297, Short.MAX_VALUE)
                .addGroup(panelAgregarTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAccionAgregarTrabajoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAgregarTrabajo))
                .addGap(0, 297, Short.MAX_VALUE))
        );
        panelAgregarTrabajoLayout.setVerticalGroup(
            panelAgregarTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarTrabajoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbAgregarTrabajo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAccionAgregarTrabajoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        panelAccionesTrabajos.add(panelAgregarTrabajo, "panelAgregarTrabajo");

        panelGestionEmpleadosTrabajo.setName("panelGestionEmpleadosTrabajo"); // NOI18N

        lbGestionEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbGestionEmpleados.setText("Gestion de empleados de trabajo:");

        lbNombreTrabajo.setText(" ");

        tblEmpleadosDeTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPaneTblEmpleadosDeTrabajo.setViewportView(tblEmpleadosDeTrabajo);

        btnDesasignar.setText("Desasignar");
        btnDesasignar.setEnabled(false);
        btnDesasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesasignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCentralAccionTrabajosGestionaEmpleadosLayout = new javax.swing.GroupLayout(panelCentralAccionTrabajosGestionaEmpleados);
        panelCentralAccionTrabajosGestionaEmpleados.setLayout(panelCentralAccionTrabajosGestionaEmpleadosLayout);
        panelCentralAccionTrabajosGestionaEmpleadosLayout.setHorizontalGroup(
            panelCentralAccionTrabajosGestionaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPaneTblEmpleadosDeTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                        .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                                .addGap(277, 277, 277)
                                .addComponent(btnDesasignar))
                            .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbGestionEmpleados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNombreTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCentralAccionTrabajosGestionaEmpleadosLayout.setVerticalGroup(
            panelCentralAccionTrabajosGestionaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCentralAccionTrabajosGestionaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGestionEmpleados)
                    .addComponent(lbNombreTrabajo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPaneTblEmpleadosDeTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesasignar)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGestionEmpleadosTrabajoLayout = new javax.swing.GroupLayout(panelGestionEmpleadosTrabajo);
        panelGestionEmpleadosTrabajo.setLayout(panelGestionEmpleadosTrabajoLayout);
        panelGestionEmpleadosTrabajoLayout.setHorizontalGroup(
            panelGestionEmpleadosTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionEmpleadosTrabajoLayout.createSequentialGroup()
                .addGap(0, 126, Short.MAX_VALUE)
                .addComponent(panelCentralAccionTrabajosGestionaEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 127, Short.MAX_VALUE))
        );
        panelGestionEmpleadosTrabajoLayout.setVerticalGroup(
            panelGestionEmpleadosTrabajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionEmpleadosTrabajoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelCentralAccionTrabajosGestionaEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAccionesTrabajos.add(panelGestionEmpleadosTrabajo, "panelGestionEmpleadosTrabajo");

        javax.swing.GroupLayout panelGeneralTrabajosLayout = new javax.swing.GroupLayout(panelGeneralTrabajos);
        panelGeneralTrabajos.setLayout(panelGeneralTrabajosLayout);
        panelGeneralTrabajosLayout.setHorizontalGroup(
            panelGeneralTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSuperiorTrabajos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelAccionesTrabajos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGeneralTrabajosLayout.setVerticalGroup(
            panelGeneralTrabajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralTrabajosLayout.createSequentialGroup()
                .addComponent(panelSuperiorTrabajos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelAccionesTrabajos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCardsGeneral.add(panelGeneralTrabajos, "panelGeneralTrabajos");

        panelGeneralEmpleados.setName("panelGeneralEmpleados"); // NOI18N

        lbGestionDeEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbGestionDeEmpleados.setText("GESTIÓN DE EMPLEADOS");

        tblTodosEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEmpleadosScroll.setViewportView(tblTodosEmpleados);

        javax.swing.GroupLayout panelTablaEmpleadosLayout = new javax.swing.GroupLayout(panelTablaEmpleados);
        panelTablaEmpleados.setLayout(panelTablaEmpleadosLayout);
        panelTablaEmpleadosLayout.setHorizontalGroup(
            panelTablaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblEmpleadosScroll)
        );
        panelTablaEmpleadosLayout.setVerticalGroup(
            panelTablaEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblEmpleadosScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbBuscarPor.setText("Buscar por:");

        rgFiltro.add(rbFiltroApellido);
        rbFiltroApellido.setSelected(true);
        rbFiltroApellido.setText("Apellido");
        rbFiltroApellido.setActionCommand("Apellido");

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });

        rgFiltro.add(rbFiltroDNI);
        rbFiltroDNI.setText("DNI");
        rbFiltroDNI.setActionCommand("DNI");

        rgFiltro.add(rbFiltroSueldo);
        rbFiltroSueldo.setText("Sueldo");
        rbFiltroSueldo.setActionCommand("Sueldo");

        btnBuscarFiltro.setText("Buscar");
        btnBuscarFiltro.setEnabled(false);
        btnBuscarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbBuscarPor)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(rbFiltroSueldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnBuscarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(rbFiltroDNI)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(rbFiltroApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbBuscarPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFiltroApellido)
                    .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(rbFiltroDNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbFiltroSueldo))
                    .addComponent(btnBuscarFiltro))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSuperiorEmpleadosLayout = new javax.swing.GroupLayout(panelSuperiorEmpleados);
        panelSuperiorEmpleados.setLayout(panelSuperiorEmpleadosLayout);
        panelSuperiorEmpleadosLayout.setHorizontalGroup(
            panelSuperiorEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorEmpleadosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbGestionDeEmpleados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSuperiorEmpleadosLayout.createSequentialGroup()
                .addComponent(panelTablaEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelSuperiorEmpleadosLayout.setVerticalGroup(
            panelSuperiorEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbGestionDeEmpleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSuperiorEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTablaEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        panelFichaEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelFichaEmpleado.setForeground(new java.awt.Color(204, 204, 204));

        lbFichaEmpNombre.setText("Nombre:");
        lbFichaEmpNombre.setEnabled(false);

        lbFichaEmpApellidos.setText("Apellidos:");
        lbFichaEmpApellidos.setEnabled(false);

        lbFichaEmpDNI.setText("DNI:");
        lbFichaEmpDNI.setEnabled(false);

        lbFichaEmpSueldo.setText("Sueldo:");
        lbFichaEmpSueldo.setEnabled(false);

        lbFichaEmpID.setText("ID:");
        lbFichaEmpID.setEnabled(false);

        inputNombreEmpleado.setEditable(false);
        inputNombreEmpleado.setEnabled(false);

        inputApellidosEmpleado.setEditable(false);
        inputApellidosEmpleado.setEnabled(false);

        inputDniEmpleado.setEditable(false);
        inputDniEmpleado.setEnabled(false);

        inputSueldoEmpleado.setEditable(false);
        inputSueldoEmpleado.setEnabled(false);

        inputIdEmpleado.setEditable(false);
        inputIdEmpleado.setEnabled(false);

        javax.swing.GroupLayout panelFichaEmpleadoLayout = new javax.swing.GroupLayout(panelFichaEmpleado);
        panelFichaEmpleado.setLayout(panelFichaEmpleadoLayout);
        panelFichaEmpleadoLayout.setHorizontalGroup(
            panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                        .addComponent(lbFichaEmpNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(inputNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFichaEmpApellidos)
                            .addComponent(lbFichaEmpDNI)
                            .addComponent(lbFichaEmpSueldo)
                            .addComponent(lbFichaEmpID))
                        .addGap(18, 18, 18)
                        .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputApellidosEmpleado)
                            .addComponent(inputDniEmpleado)
                            .addComponent(inputSueldoEmpleado)
                            .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                                .addComponent(inputIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        panelFichaEmpleadoLayout.setVerticalGroup(
            panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFichaEmpleadoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbFichaEmpNombre)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFichaEmpleadoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(inputNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFichaEmpApellidos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputDniEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFichaEmpDNI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputSueldoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFichaEmpSueldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFichaEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFichaEmpID))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbFichaEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbFichaEmpleado.setText("Ficha de empleado");
        lbFichaEmpleado.setEnabled(false);

        btnOperacionesActDes.setText("Operaciones: Activar");
        btnOperacionesActDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacionesActDesActionPerformed(evt);
            }
        });

        panelOperaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        rgOperacionesEmpleado.add(rbModificarEmpleado);
        rbModificarEmpleado.setSelected(true);
        rbModificarEmpleado.setText("Modificar");
        rbModificarEmpleado.setActionCommand("modificar");
        rbModificarEmpleado.setEnabled(false);
        rbModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbModificarEmpleadoActionPerformed(evt);
            }
        });

        rgOperacionesEmpleado.add(rbAnadirEmpleado);
        rbAnadirEmpleado.setText("Añadir");
        rbAnadirEmpleado.setActionCommand("anadir");
        rbAnadirEmpleado.setEnabled(false);
        rbAnadirEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAnadirEmpleadoActionPerformed(evt);
            }
        });

        rgOperacionesEmpleado.add(rbEliminarEmpleado);
        rbEliminarEmpleado.setText("Eliminar");
        rbEliminarEmpleado.setActionCommand("eliminar");
        rbEliminarEmpleado.setEnabled(false);
        rbEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEliminarEmpleadoActionPerformed(evt);
            }
        });

        rgOperacionesEmpleado.add(rbAsignarEmpleado);
        rbAsignarEmpleado.setText("Asignar");
        rbAsignarEmpleado.setActionCommand("asignar");
        rbAsignarEmpleado.setEnabled(false);
        rbAsignarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAsignarEmpleadoActionPerformed(evt);
            }
        });

        btnGuardarCambios.setText("Guardar Cambios");
        btnGuardarCambios.setEnabled(false);
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnGuardarEmpleado.setText("Guardar Empleado");
        btnGuardarEmpleado.setEnabled(false);
        btnGuardarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpleadoActionPerformed(evt);
            }
        });

        btnEliminarEmpleado.setText("Eliminar Empleado");
        btnEliminarEmpleado.setEnabled(false);
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

        lbOperacionesNtrabajo.setText("Nº TRABAJO");
        lbOperacionesNtrabajo.setEnabled(false);

        inputNTrabajoEmpleado.setEnabled(false);

        btnAsignarEmpleado.setText("Asignar");
        btnAsignarEmpleado.setEnabled(false);
        btnAsignarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOperacionesLayout = new javax.swing.GroupLayout(panelOperaciones);
        panelOperaciones.setLayout(panelOperacionesLayout);
        panelOperacionesLayout.setHorizontalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbModificarEmpleado)
                    .addComponent(rbAnadirEmpleado)
                    .addComponent(rbEliminarEmpleado)
                    .addComponent(rbAsignarEmpleado))
                .addGap(42, 42, 42)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAsignarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(panelOperacionesLayout.createSequentialGroup()
                        .addComponent(lbOperacionesNtrabajo)
                        .addGap(18, 18, 18)
                        .addComponent(inputNTrabajoEmpleado))
                    .addComponent(btnEliminarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(btnGuardarCambios, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelOperacionesLayout.setVerticalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperacionesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbModificarEmpleado)
                    .addComponent(btnGuardarCambios))
                .addGap(18, 18, 18)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAnadirEmpleado)
                    .addComponent(btnGuardarEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbEliminarEmpleado)
                    .addComponent(btnEliminarEmpleado))
                .addGap(33, 33, 33)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAsignarEmpleado)
                    .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbOperacionesNtrabajo)
                        .addComponent(inputNTrabajoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignarEmpleado)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout panelFichaYoperacionesEmpleadoLayout = new javax.swing.GroupLayout(panelFichaYoperacionesEmpleado);
        panelFichaYoperacionesEmpleado.setLayout(panelFichaYoperacionesEmpleadoLayout);
        panelFichaYoperacionesEmpleadoLayout.setHorizontalGroup(
            panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                .addGroup(panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(lbFichaEmpleado))
                    .addGroup(panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFichaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122)
                .addGroup(panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                        .addComponent(btnOperacionesActDes)
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                        .addComponent(panelOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        panelFichaYoperacionesEmpleadoLayout.setVerticalGroup(
            panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaYoperacionesEmpleadoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFichaEmpleado)
                    .addComponent(btnOperacionesActDes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFichaYoperacionesEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFichaEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout panelGeneralEmpleadosLayout = new javax.swing.GroupLayout(panelGeneralEmpleados);
        panelGeneralEmpleados.setLayout(panelGeneralEmpleadosLayout);
        panelGeneralEmpleadosLayout.setHorizontalGroup(
            panelGeneralEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperiorEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFichaYoperacionesEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGeneralEmpleadosLayout.setVerticalGroup(
            panelGeneralEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralEmpleadosLayout.createSequentialGroup()
                .addComponent(panelSuperiorEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFichaYoperacionesEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCardsGeneral.add(panelGeneralEmpleados, "panelGeneralEmpleados");

        menu.setText("Menu");

        miEmpleados.setText("Empleados");
        miEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEmpleadosActionPerformed(evt);
            }
        });
        menu.add(miEmpleados);

        miTrabajos.setText("Trabajos");
        miTrabajos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTrabajosActionPerformed(evt);
            }
        });
        menu.add(miTrabajos);

        miSalir.setText("Salir");
        miSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSalirActionPerformed(evt);
            }
        });
        menu.add(miSalir);

        barraDeMenu.add(menu);

        setJMenuBar(barraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCardsGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCardsGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // <editor-fold defaultstate="collapsed" desc="ATRIBUTOS AUTOGENERADOS">  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JButton btnAgregarTrabajo;
    private javax.swing.JButton btnAsignarEmpleado;
    private javax.swing.JButton btnBuscarFiltro;
    private javax.swing.JButton btnDesasignar;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnGestionarEmpleados;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnGuardarEmpleado;
    private javax.swing.JButton btnGuardarTrabajoNuevo;
    private javax.swing.JButton btnOperacionesActDes;
    private javax.swing.JTextField busqueda;
    private javax.swing.JTextField inputApellidosEmpleado;
    private javax.swing.JTextField inputDniEmpleado;
    private javax.swing.JTextField inputIdEmpleado;
    private javax.swing.JTextField inputNTrabajoEmpleado;
    private javax.swing.JTextField inputNombreEmpleado;
    private javax.swing.JTextField inputNombreTrabajo;
    private javax.swing.JTextField inputSueldoEmpleado;
    private javax.swing.JScrollPane jScrollPaneTblEmpleadosDeTrabajo;
    private javax.swing.JLabel lbAgregarTrabajo;
    private javax.swing.JLabel lbBuscarPor;
    private javax.swing.JLabel lbFichaEmpApellidos;
    private javax.swing.JLabel lbFichaEmpDNI;
    private javax.swing.JLabel lbFichaEmpID;
    private javax.swing.JLabel lbFichaEmpNombre;
    private javax.swing.JLabel lbFichaEmpSueldo;
    private javax.swing.JLabel lbFichaEmpleado;
    private javax.swing.JLabel lbGestionDeEmpleados;
    private javax.swing.JLabel lbGestionDeTrabajos;
    private javax.swing.JLabel lbGestionEmpleados;
    private javax.swing.JLabel lbNTrabajoinfo;
    private javax.swing.JLabel lbNombreTrabajo;
    private javax.swing.JLabel lbNombreTrabajoNuevo;
    private javax.swing.JLabel lbNumeroTrabajo;
    private javax.swing.JLabel lbOperacionesNtrabajo;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem miEmpleados;
    private javax.swing.JMenuItem miSalir;
    private javax.swing.JMenuItem miTrabajos;
    private javax.swing.JPanel panelAccionAgregarTrabajoInterno;
    private javax.swing.JPanel panelAccionesTrabajos;
    private javax.swing.JPanel panelAgregarTrabajo;
    private javax.swing.JPanel panelCardsGeneral;
    private javax.swing.JPanel panelCentralAccionTrabajosGestionaEmpleados;
    private javax.swing.JPanel panelFichaEmpleado;
    private javax.swing.JPanel panelFichaYoperacionesEmpleado;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelGeneralEmpleados;
    private javax.swing.JPanel panelGeneralTrabajos;
    private javax.swing.JPanel panelGestionEmpleadosTrabajo;
    private javax.swing.JPanel panelOpcionesTrabajos;
    private javax.swing.JPanel panelOperaciones;
    private javax.swing.JPanel panelSuperiorEmpleados;
    private javax.swing.JPanel panelSuperiorTrabajos;
    private javax.swing.JPanel panelTablaEmpleados;
    private javax.swing.JPanel panelTablaTrabajos;
    private javax.swing.JRadioButton rbAnadirEmpleado;
    private javax.swing.JRadioButton rbAsignarEmpleado;
    private javax.swing.JRadioButton rbEliminarEmpleado;
    private javax.swing.JRadioButton rbFiltroApellido;
    private javax.swing.JRadioButton rbFiltroDNI;
    private javax.swing.JRadioButton rbFiltroSueldo;
    private javax.swing.JRadioButton rbModificarEmpleado;
    private javax.swing.ButtonGroup rgFiltro;
    private javax.swing.ButtonGroup rgOperacionesEmpleado;
    private javax.swing.JTable tblEmpleadosDeTrabajo;
    private javax.swing.JScrollPane tblEmpleadosScroll;
    private javax.swing.JTable tblTodosEmpleados;
    private javax.swing.JTable tblTrabajos;
    private javax.swing.JScrollPane tblTrabajosScroll;
    // End of variables declaration//GEN-END:variables
// </editor-fold>     
}
