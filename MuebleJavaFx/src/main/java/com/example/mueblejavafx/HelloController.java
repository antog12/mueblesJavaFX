package com.example.mueblejavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {//ojo implement , que no se olvide ponerlo
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnBorrar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtMaterial;

    @FXML
    private TextField txtPrecio;

    @FXML//mucho ojo al crear la tabla
    private TableView<Mueble> tblMuebles;

    @FXML
    private TableColumn<Mueble, Integer> colId;

    @FXML
    private TableColumn<Mueble, String> colTipo;

    @FXML
    private TableColumn<Mueble, String> colMaterial;

    @FXML
    private TableColumn<Mueble, Integer> colPrecio;


    public void initialize(URL url, ResourceBundle resourceBundle){
        txtId.setEditable(false);
        //txtTipo.setEditable(true);
        //txtMaterial.setEditable(true);
        //txtPrecio.setEditable(true);

        colId.setCellValueFactory(new PropertyValueFactory<Mueble, Integer>("id"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Mueble, String>("tipo"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<Mueble, String>("material"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Mueble, Integer>("precio"));
        cargaTabla();

    }
    //alta
    public void alta(){//le decimos que si el material es madera o hierro haga el alta, de lo contrario manda un warning
        if(txtMaterial.getText().equals("madera") || txtMaterial.getText().equals("hierro")) {
            Session s = HibernateUtil.openSession();
            s.beginTransaction();
            Mueble mimueble = new Mueble(txtTipo.getText(), txtMaterial.getText(), Integer.parseInt(txtPrecio.getText()));
            s.save(mimueble);
            s.getTransaction().commit();
            s.close();
            cargaTabla();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("No es un material permitido");
            alert.showAndWait();

        }
    }
    //modificar
    public void modificar(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Mueble miMueble=new Mueble(Integer.parseInt(txtId.getText()), txtTipo.getText(), txtMaterial.getText(), Integer.parseInt(txtPrecio.getText()));
        s.update(miMueble);
        s.getTransaction().commit();
        s.close();
        cargaTabla();

    }
    //borrar
    public  void borrar(){
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        Mueble miMueble=new Mueble();
        miMueble.setId(Integer.parseInt(txtId.getText()));
        s.delete(miMueble);
        s.getTransaction().commit();
        s.close();
        cargaTabla();
    }
    //cargar tabla
    public void cargaTabla() {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        ArrayList<Mueble> lista = (ArrayList<Mueble>) s.createQuery("from Mueble").list();
        s.getTransaction().commit();
        s.close();
        tblMuebles.getItems().setAll(lista);
    }
    //click tabla
    public void clicTabla(javafx.scene.input.MouseEvent mouseEvent){
        Mueble miMueble = (Mueble) tblMuebles.getSelectionModel().getSelectedItem();
        if (miMueble != null) {
            txtId.setText(Integer.toString(miMueble.getId()));
            txtTipo.setText(miMueble.getTipo());
            txtMaterial.setText(miMueble.getMaterial());
            txtPrecio.setText(Integer.toString(miMueble.getPrecio()));
        }else{
            System.out.println("No hay muebles");
        }
    }

}