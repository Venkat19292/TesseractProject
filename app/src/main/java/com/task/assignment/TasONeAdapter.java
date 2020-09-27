package com.task.assignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TasONeAdapter extends RecyclerView.Adapter<TasONeAdapter.viewHolder> {

    ArrayList<AppListModel> listOfapps;
    Context context;


    public TasONeAdapter(ArrayList<AppListModel> listOfapps, Context context) {
        this.listOfapps = listOfapps;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.datalist, parent, false);
        return new viewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AppListModel obj = listOfapps.get(position);

        holder.txt_appname.setText(obj.getAppname());
        holder.txt_packageName.setText(obj.getPackageName());
        holder.txt_versionName.setText(obj.getVersionname());
        holder.txt_classname.setText(obj.getAppClassname());
        holder.img_appIco.setImageDrawable(obj.getAppIcon());
    }

    @Override
    public int getItemCount() {
        return listOfapps.size();
    }

    public void setFilter(List<AppListModel> newList) {
        listOfapps.clear();
        listOfapps.addAll(newList);
        notifyDataSetChanged();
    }

    public void removefilter(List<AppListModel> newList) {
        listOfapps.clear();
        listOfapps.addAll(newList);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public final TextView txt_appname;
        private final TextView txt_packageName;
        private final TextView txt_classname;
        private final TextView txt_versionCode;
        private final TextView txt_versionName;
        private final ImageView img_appIco;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_appname = itemView.findViewById(R.id.txt_appname);
            txt_packageName = itemView.findViewById(R.id.txt_packageName);
            txt_classname = itemView.findViewById(R.id.txt_classname);
            txt_versionCode = itemView.findViewById(R.id.txt_versionCode);
            txt_versionName = itemView.findViewById(R.id.txt_versionName);
            img_appIco = itemView.findViewById(R.id.img_appIco);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        AppListModel obj = listOfapps.get(getAdapterPosition());
                        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(obj.getPackageName());
                        context.startActivity(launchIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
