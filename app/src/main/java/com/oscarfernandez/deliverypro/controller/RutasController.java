package com.oscarfernandez.deliverypro.controller;

import com.oscarfernandez.deliverypro.activity.RutasActivity;
import com.oscarfernandez.deliverypro.api.RutasApi;
import com.oscarfernandez.deliverypro.data.SQLiteHelper;
import com.oscarfernandez.deliverypro.data.SharedPreferenceHelper;
import com.oscarfernandez.deliverypro.domain.DataString;
import com.oscarfernandez.deliverypro.domain.Ruta;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RutasController {

    private WeakReference<RutasActivity> rutasActivity;
    private RutasApi rutasApi;
    private SQLiteHelper dbHelper;

    public RutasController(RutasActivity activity) {
        this.rutasActivity = new WeakReference<>(activity);
        dbHelper = new SQLiteHelper(rutasActivity.get());
        this.rutasApi = new RutasApi();
    }

    public void getRutasInfo() {
        String idUser = SharedPreferenceHelper.getSharedPreference(rutasActivity.get(), "idUser", "0");
        rutasApi.getDatosRutas(idUser, this);
    }

    public void sendResponse(String json) {
        if (json == null || json.equals("")) {
            setOfflineData();
        } else {
            ArrayList<Ruta> datosRutas = convertJson(json);
            ArrayList<String> rutas = new ArrayList<>();
            dbHelper.addRutas(datosRutas);
            rutas.add("Seleccionar ruta");
            for (Ruta ruta : datosRutas) {
                rutas.add(ruta.getIdOrden());
            }
            rutasActivity.get().fillDatosRutas(rutas);
        }
    }

    public void getRutaById(String id) {
        Ruta ruta = dbHelper.getRutasById(id);
        ArrayList<DataString> data = new ArrayList<>();
        data.add(new DataString("Nombre", ruta.getNombre()));
        data.add(new DataString("Dirección", ruta.getCalle() + " " + ruta.getNumero() + ", " + ruta.getComunas()));
        data.add(new DataString("Telefonos", ruta.getTelefono()));
        data.add(new DataString("Producto", ruta.getProducto()));
        data.add(new DataString("Empresa", ruta.getIdEmpresa()));
        data.add(new DataString("Fecha Agendada", ruta.getFechaAgendada()));
        data.add(new DataString("Ejecutivo de Agendamiento", ""));
        data.add(new DataString("Hora Programada", deleteDate(ruta.getHoraDesde()) + " - " + deleteDate(ruta.getHoraHasta())));
        rutasActivity.get().fillRuta(data);
    }

    public void setOfflineData() {

        ArrayList<Ruta> datosRutas = dbHelper.getRutas();
        ArrayList<String> rutas = new ArrayList<>();
        rutas.add("Seleccionar ruta");
        for (Ruta ruta : datosRutas) {
            rutas.add(ruta.getIdOrden());
        }
        rutasActivity.get().fillDatosRutas(rutas);

    }

    private String deleteDate(String dateTime) {
        String time = dateTime;
        time = time.substring(10);
        return time;
    }

    private ArrayList<Ruta> convertJson(String json) {
        ArrayList<Ruta> rutas = new ArrayList();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Ruta ruta = new Ruta();
                ruta.setIdUsuario(obj.getString("idUsuario"));
                ruta.setIdOrden(obj.getString("idOrden"));
                ruta.setRUT(obj.getString("RUT"));
                ruta.setDV(obj.getString("DV"));
                ruta.setNombre(obj.getString("Nombre"));
                ruta.setProducto(obj.getString("Producto"));
                ruta.setFechaAgendada(obj.getString("FechaAgendada"));
                ruta.setHoraDesde(obj.getString("HoraDesde"));
                ruta.setHoraHasta(obj.getString("HoraHasta"));
                ruta.setHoraInicio(obj.getString("HoraInicio"));
                ruta.setHoraFin(obj.getString("HoraFin"));
                ruta.setTelefono(obj.getString("Telefono"));
                ruta.setCalle(obj.getString("Calle"));
                ruta.setNumero(obj.getString("Numero"));
                ruta.setComunas(obj.getString("comunas"));
                ruta.setIdEmpresa(obj.getString("idEmpresa"));
                rutas.add(ruta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rutas;
    }
}
