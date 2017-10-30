package com.oscarfernandez.deliverypro.data.contract;

import android.provider.BaseColumns;

import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.TABLE_RUTAS;

public class RutasContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "delivery";

    public static class RutasEntry implements BaseColumns {

        public static final String TABLE_RUTAS = "rutas";
        public static final String COLUMN_NAME_ID_USUARIO = "idUsuario";
        public static final String COLUMN_NAME_ID_ORDEN = "idOrden";
        public static final String COLUMN_NAME_RUT = "RUT";
        public static final String COLUMN_NAME_DV = "DV";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_ID_PRODUCTO = "producto";
        public static final String COLUMN_NAME_ID_FECHA_AGENDADA = "fechaAgendada";
        public static final String COLUMN_NAME_HORA_DESDE = "horaDesde";
        public static final String COLUMN_NAME_HORA_HASTA = "horaHasta";
        public static final String COLUMN_NAME_HORA_INICIO = "horaInicio";
        public static final String COLUMN_NAME_HORA_FIN = "horaFin";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_CALLE = "calle";
        public static final String COLUMN_NAME_NUMERO = "numero";
        public static final String COLUMN_NAME_LOCAL = "local";
        public static final String COLUMN_COMUNAS = "comunas";
        public static final String COLUMN_NAME_ID_EMPRESA = "idEmpresa";
    }

    public static final String CREATE_TABLE_RUTAS = "CREATE TABLE rutas(idUsuario TEXT, idOrden TEXT PRIMARY KEY, " +
            "RUT TEXT, DV TEXT, nombre TEXT, producto TEXT, fechaAgendada TEXT, horaDesde TEXT, horaHasta TEXT, " +
            "horaInicio TEXT, horaFin TEXT, telefono TEXT, calle TEXT, numero TEXT, local TEXT, comunas TEXT, idEmpresa TEXT);";
    public static final String DROP_TABLE_RUTAS = "DROP TABLE IF EXISTS " + TABLE_RUTAS;
}
