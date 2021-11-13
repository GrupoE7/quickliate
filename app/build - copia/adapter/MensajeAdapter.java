package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickliate.R;

import java.util.ArrayList;
import java.util.Arrays;

import Models.Mensaje;


public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {
private int resource;
private ArrayList<Mensaje> mensajesList;

public MensajeAdapter(ArrayList<Mensaje> mensajesList , int resource ){
    this.mensajesList = mensajesList;
    this.resource=resource;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
    Mensaje mensaje = mensajesList.get(index);
    holder.textVerMensaje.setText(mensaje.getHum());

    }

    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textVerMensaje;
        public View view;
        public ViewHolder(View view){
            super(view);
            this.view=view;
            this.textVerMensaje =(TextView) view.findViewById(R.id.textVerMensaje);
        }


            }
}
