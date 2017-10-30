package adapter;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oscarfernandez.deliverypro.R;
import com.oscarfernandez.deliverypro.domain.DataString;

import java.util.ArrayList;

public class DatosRutaAdapter extends RecyclerView.Adapter<DatosRutaAdapter.ViewHolder> {

    private ArrayList<DataString> data;

    public DatosRutaAdapter() {
        getData();
    }

    private ArrayList<DataString> getData() {
        data = new ArrayList<>();
        data.add(new DataString("Nombre", ""));
        data.add(new DataString("Dirección", ""));
        data.add(new DataString("Telefonos", ""));
        data.add(new DataString("Producto", ""));
        data.add(new DataString("Empresa", ""));
        data.add(new DataString("Fecha Agendada", ""));
        data.add(new DataString("Ejecutivo de Agendamiento", ""));
        data.add(new DataString("Hora Programada", ""));
        return data;
    }

    public void updateData(ArrayList<DataString> updateData) {
        data = updateData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rutas, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtKey.setText(data.get(position).getKey());
        holder.txtValue.setText(data.get(position).getValue());
        if (position % 2 == 0) {
            holder.constraintLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
        } else {
            holder.constraintLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKey;
        TextView txtValue;
        ConstraintLayout constraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.RL);
            txtKey = (TextView) itemView.findViewById(R.id.txtKey);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);

        }
    }
}
