package com.covid19.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {

    private Data data;
    private Context context;
    private List<Data> dataList;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvState, tvDate;
        CardView cardView;


        ViewHolder(View view) {
            super(view);

            tvState = view.findViewById(R.id.tvState);
            tvDate = view.findViewById(R.id.tvDate);
            cardView = view.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data = dataList.get(getAdapterPosition());
                    Intent i = new Intent(context, DataActivity.class);

                    i.putExtra("Positive", data.getPositive());
                    i.putExtra("Negative", data.getNegative());
                    i.putExtra("Recovered", data.getRecovered());
                    i.putExtra("Death", data.getDeath());
                    i.putExtra("Total", data.getTotal());
                    context.startActivity(i);
                }
            });


        }


    }

    public DataAdapter(Context mContext, List<Data> dataList) {
        this.context = mContext;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);


        return new DataAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.ViewHolder holder, final int position) {

        data = dataList.get(position);
        holder.tvState.setText(data.getState());
        holder.tvDate.setText(String.format("Last updated: %s", data.getLastUpdated()));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Data> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Data data : dataList) {
                    if (data.getState().toLowerCase().contains(filterPattern)) {
                        filteredList.add(data);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}