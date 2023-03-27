package com.example.xrig;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class pc_build_adapter extends RecyclerView.Adapter<pc_build_adapter.ViewHolder> {

    private Context context;
    private ArrayList<pc_model> pcList;

    pc_build_adapter(Context context, ArrayList<pc_model> pcList){
        this.context = context;
        this.pcList = pcList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pc_cards, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pc_model modal = pcList.get(position);
        holder.pcName.setText(modal.getName());
        holder.pcPrice.setText(modal.getPrice());
        holder.pcDesc.setText(modal.getDesc());

        Picasso.get().load(modal.getImgUrl()).into(holder.pcImage);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Clicked Item is " + modal.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return pcList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView pcName, pcPrice, pcDesc;
        private ImageView pcImage;
        public ViewHolder( View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            pcName = itemView.findViewById(R.id.pcName);
            pcPrice = itemView.findViewById(R.id.pcPrice);
            pcImage = itemView.findViewById(R.id.pcImage);
            pcDesc = itemView.findViewById(R.id.pcDesc);

            pcImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            pc_model modal = pcList.get(position);
            String name = modal.getName();
            String price = modal.getPrice();
            String imgUrl = modal.getImgUrl();
            String desc = modal.getDesc();
            Intent intent = new Intent(context,XRiG_Details.class);
            intent.putExtra("detailsName",name);
            intent.putExtra("detailsPrice",price);
            intent.putExtra("detailsDesc",desc);
            intent.putExtra("detailsImgUrl",imgUrl);
            context.startActivity(intent);
        }
    }
}
