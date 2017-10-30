package com.oscarfernandez.deliverypro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.oscarfernandez.deliverypro.domain.Ruta;

import java.util.ArrayList;

import static com.oscarfernandez.deliverypro.data.contract.RutasContract.CREATE_TABLE_RUTAS;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.DATABASE_NAME;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.DATABASE_VERSION;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.DROP_TABLE_RUTAS;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_COMUNAS;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_CALLE;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_DV;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_HORA_DESDE;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_HORA_FIN;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_HORA_HASTA;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_HORA_INICIO;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_ID_EMPRESA;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_ID_FECHA_AGENDADA;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_ID_ORDEN;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_ID_PRODUCTO;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_ID_USUARIO;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_LOCAL;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_NOMBRE;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_NUMERO;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_RUT;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.COLUMN_NAME_TELEFONO;
import static com.oscarfernandez.deliverypro.data.contract.RutasContract.RutasEntry.TABLE_RUTAS;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create", "create");
        db.execSQL(CREATE_TABLE_RUTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUTAS);
        onCreate(db);
    }

    public void addRuta(Ruta ruta) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMUNAS, ruta.getComunas());
        values.put(COLUMN_NAME_CALLE, ruta.getCalle());
        values.put(COLUMN_NAME_DV, ruta.getDV());
        values.put(COLUMN_NAME_HORA_DESDE, ruta.getHoraDesde());
        values.put(COLUMN_NAME_HORA_FIN, ruta.getHoraFin());
        values.put(COLUMN_NAME_HORA_HASTA, ruta.getHoraHasta());
        values.put(COLUMN_NAME_HORA_INICIO, ruta.getHoraInicio());
        values.put(COLUMN_NAME_ID_EMPRESA, ruta.getIdEmpresa());
        values.put(COLUMN_NAME_ID_FECHA_AGENDADA, ruta.getFechaAgendada());
        values.put(COLUMN_NAME_ID_ORDEN, ruta.getIdOrden());
        values.put(COLUMN_NAME_ID_PRODUCTO, ruta.getProducto());
        values.put(COLUMN_NAME_ID_USUARIO, ruta.getIdUsuario());
        values.put(COLUMN_NAME_LOCAL, ruta.getLocal());
        values.put(COLUMN_NAME_NOMBRE, ruta.getNombre());
        values.put(COLUMN_NAME_NUMERO, ruta.getNumero());
        values.put(COLUMN_NAME_RUT, ruta.getRUT());
        values.put(COLUMN_NAME_TELEFONO, ruta.getTelefono());

        db.insert(TABLE_RUTAS, null, values);
        db.close();
    }

    public void addRutas(ArrayList<Ruta> rutas) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_RUTAS);
        for (Ruta ruta : rutas) {
            addRuta(ruta);
        }
    }

    public ArrayList<Ruta> getRutas() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RUTAS;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Log.d("movetofisrt", "yes");
            do {
                Log.d("dowiledeldemonio", "yes");
                Ruta ruta = new Ruta();
                ruta.setIdUsuario(cursor.getString(0));
                ruta.setIdOrden(cursor.getString(1));
                ruta.setRUT(cursor.getString(2));
                ruta.setDV(cursor.getString(3));
                ruta.setNombre(cursor.getString(4));
                ruta.setProducto(cursor.getString(5));
                ruta.setFechaAgendada(cursor.getString(6));
                ruta.setHoraDesde(cursor.getString(7));
                ruta.setHoraHasta(cursor.getString(8));
                ruta.setHoraInicio(cursor.getString(9));
                ruta.setHoraFin(cursor.getString(10));
                ruta.setTelefono(cursor.getString(11));
                ruta.setCalle(cursor.getString(12));
                ruta.setNumero(cursor.getString(13));
                ruta.setLocal(cursor.getString(14));
                ruta.setComunas(cursor.getString(15));
                ruta.setIdEmpresa(cursor.getString(16));
                rutas.add(ruta);
            } while (cursor.moveToNext());
        };
        return rutas;
    }

    public Ruta getRutasById(String id) {
        Ruta ruta = null;
        String selectQuery = "SELECT  * FROM " + TABLE_RUTAS + " WHERE idOrden = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            ruta = new Ruta();
            ruta.setIdUsuario(cursor.getString(0));
            ruta.setIdOrden(cursor.getString(1));
            ruta.setRUT(cursor.getString(2));
            ruta.setDV(cursor.getString(3));
            ruta.setNombre(cursor.getString(4));
            ruta.setProducto(cursor.getString(5));
            ruta.setFechaAgendada(cursor.getString(6));
            ruta.setHoraDesde(cursor.getString(7));
            ruta.setHoraHasta(cursor.getString(8));
            ruta.setHoraInicio(cursor.getString(9));
            ruta.setHoraFin(cursor.getString(10));
            ruta.setTelefono(cursor.getString(11));
            ruta.setCalle(cursor.getString(12));
            ruta.setNumero(cursor.getString(13));
            ruta.setLocal(cursor.getString(14));
            ruta.setComunas(cursor.getString(15));
            ruta.setIdEmpresa(cursor.getString(16));
        }
        return ruta;
    }
}
