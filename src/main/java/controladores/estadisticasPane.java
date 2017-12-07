package controladores;

import com.jfoenix.controls.JFXComboBox;
import helpers.Calculos;
import helpers.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import modelo.Evento;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class estadisticasPane implements Initializable{

    @FXML
    private JFXComboBox<String> meses;

    @FXML
    private PieChart asistenciasTotales;

    @FXML
    private PieChart registrosNuevos;

    @FXML
    private PieChart siembrasTotalesUSD;

    @FXML
    private PieChart siembrasTotalesBTC;

    @FXML
    private PieChart cosechasTotales;


    private List<Evento> filtrarEventos(int mes){
        List<Evento> evento = Data.getEventos();
        List<Evento> evenFil = new ArrayList<>();

        for (Evento e:evento){
            if (e.getFecha().getMonthValue() == mes && e.isDatosIngresados()){
                evenFil.add(e);
            }
        }
        return evenFil;
    }

    private void graficarPie(List<Evento> eventos){
        ObservableList<PieChart.Data> dataAsistencias = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> dataRegistrosN = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> dataSiembraUSD = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> dataSiembraBTC = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> dataCosechaTotal = FXCollections.observableArrayList();

        for (Evento e:eventos){
            Calculos.LogicaEvento logica = new Calculos.LogicaEvento(e);

            int aTotal = logica.getaTotal();
            int regTotal = logica.getRegTotales();
            int sTotalUSD = logica.getsUSD();
            int sTotalBTC = logica.getsBTC();
            int cTotal = logica.getcTotal();

            dataAsistencias.add(new PieChart.Data(e.getFecha().toString(),aTotal));
            dataRegistrosN.add(new PieChart.Data(e.getFecha().toString(),regTotal));
            dataSiembraUSD.add(new PieChart.Data(e.getFecha().toString(),sTotalUSD));
            dataSiembraBTC.add(new PieChart.Data(e.getFecha().toString(),sTotalBTC));
            dataCosechaTotal.add(new PieChart.Data(e.getFecha().toString(),cTotal));
        }

        asistenciasTotales.setData(dataAsistencias);
        registrosNuevos.setData(dataRegistrosN);
        siembrasTotalesUSD.setData(dataSiembraUSD);
        siembrasTotalesBTC.setData(dataSiembraBTC);
        cosechasTotales.setData(dataCosechaTotal);

    }

    private void cambiarAtributos(PieChart chart, String nombre){
        chart.setTitle(nombre);
        chart.setLegendSide(Side.LEFT);
        chart.setTitleSide(Side.BOTTOM);
        chart.setLabelLineLength(60);
        chart.setLabelsVisible(true);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        meses.getItems().setAll("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
                "Agosto","Septiembre","Octubre","Noviembre","Diciembre");
        meses.getSelectionModel().select(LocalDate.now().getMonthValue()-1);

        cambiarAtributos(asistenciasTotales,"Asistencias Totales");
        cambiarAtributos(registrosNuevos,"Registros Nuevos");
        cambiarAtributos(siembrasTotalesUSD,"Siembras en USD");
        cambiarAtributos(siembrasTotalesBTC,"Siembras en BTC");
        cambiarAtributos(cosechasTotales,"Cosechas Totales");

        graficarPie(filtrarEventos(LocalDate.now().getMonthValue()));

        meses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case "Enero":
                    graficarPie(filtrarEventos(1));
                    break;
                case "Febrero":
                    graficarPie(filtrarEventos(2));
                    break;
                case "Marzo":
                    graficarPie(filtrarEventos(3));
                    break;
                case "Abril":
                    graficarPie(filtrarEventos(4));
                    break;
                case "Mayo":
                    graficarPie(filtrarEventos(5));
                    break;
                case "Junio":
                    graficarPie(filtrarEventos(6));
                    break;
                case "Julio":
                    graficarPie(filtrarEventos(7));
                    break;
                case "Agosto":
                    graficarPie(filtrarEventos(8));
                    break;
                case "Septiembre":
                    graficarPie(filtrarEventos(9));
                    break;
                case "Octubre":
                    graficarPie(filtrarEventos(10));
                    break;
                case "Noviembre":
                    graficarPie(filtrarEventos(11));
                    break;
                case "Diciembre":
                    graficarPie(filtrarEventos(12));
                    break;
            }
        });



    }




}
