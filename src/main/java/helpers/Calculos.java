package helpers;

import modelo.*;

import java.util.List;

public class Calculos {


    public static class LogicaEvento{

        //***** Global Vars
        List<Siembra> siembras;
        List<Cosecha> cosechas;
        Asistencias asistencia;
        Evento evento;

        //***** Corte del dia Vars
        double cSiembras = 0;
        double cCosechas = 0;
        double cBalance = 0;

        //***** Asistencia Vars
        int aTotal = 0;
        int aVisitantes = 0;
        int aActivos = 0;

        //***** Siebras Vars
        int sBTC = 0;
        int sUSD = 0;
        int sPrimera = 0;
        int sSegunda = 0;

        //**** Cosecha total
        int cTotal = 0;

        //**** Registros Totales
        int regTotales = 0;


        public LogicaEvento(Evento evento) {
            this.evento = evento;
            if (evento != null && evento.isDatosIngresados()){
                iniciar();
                corteCalc();
                asistenciaCalc();
                siembrasCalc();
                corteCalc();
                cosechaCalc();
                regTotales = Data.getClientesPorFecha(evento.getFecha()).size();
            }
        }

        private void iniciar(){
            siembras = Data.getSiembrasPorEvento(evento.getID_Planes());
            cosechas = Data.getCosechasPorEvento(evento.getID_Planes());
            asistencia = Data.getAsistenciaPorEvento(evento.getID_Planes());
        }

        private void corteCalc(){
            for (Siembra s:siembras){
                cSiembras = cSiembras+s.getMonto();
            }
            for (Cosecha c:cosechas){
                cCosechas = cCosechas + c.getMonto();
            }
            cBalance = cSiembras - cCosechas;
        }

        private void asistenciaCalc(){
            aTotal = asistencia.getClientes().size();

            List<Cliente> cli = asistencia.getClientes();

            for (Cliente c:cli){
                if (c.isVisitante()){
                    aVisitantes++;
                }else {
                    aActivos++;
                }
            }
        }

        private void siembrasCalc(){
            for (Siembra s:siembras){
                if (s.isBtc()){
                    sBTC++;
                }else {
                    sUSD++;
                }
            }
        }

        private void cosechaCalc(){
            cTotal = cosechas.size();
        }

        public double getcSiembras() {
            return cSiembras;
        }

        public double getcCosechas() {
            return cCosechas;
        }

        public double getcBalance() {
            return cBalance;
        }

        public int getaTotal() {
            return aTotal;
        }

        public int getaVisitantes() {
            return aVisitantes;
        }

        public int getaActivos() {
            return aActivos;
        }

        public int getsBTC() {
            return sBTC;
        }

        public int getsUSD() {
            return sUSD;
        }

        public int getsPrimera() {
            return sPrimera;
        }

        public int getsSegunda() {
            return sSegunda;
        }

        public int getcTotal() {
            return cTotal;
        }

        public int getRegTotales() {
            return regTotales;
        }
    }







}
