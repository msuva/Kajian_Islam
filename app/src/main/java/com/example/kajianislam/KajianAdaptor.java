package com.example.kajianislam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class KajianAdaptor extends RecyclerView.Adapter<KajianAdaptor.KajianViewHolder> {
    private final LinkedList <Kajian> mKajian;
    private LayoutInflater mInflater;

    public KajianAdaptor(Context context, LinkedList<Kajian> kajian){
        mInflater=LayoutInflater.from(context);
        this.mKajian=kajian;
    }
    @Override
    public KajianViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View mItemView=mInflater.inflate(R.layout.isidata, parent, false);
        return new KajianViewHolder(mItemView, this);
    }

    public void onBindViewHolder(KajianViewHolder holder, int position){
        Kajian mCurrent = mKajian.get(position);
        holder.mJudul.setText(mCurrent.judulKajian);
        holder.mTanggal.setText(mCurrent.tanggal);
        holder.mUstadz.setText(mCurrent.namaUstadz);
    }

    @Override
    public int getItemCount() {
        return mKajian.size();
    }

    class KajianViewHolder extends RecyclerView.ViewHolder{
        public final TextView mJudul, mUstadz, mTanggal;
        final KajianAdaptor mAdapter;

        public KajianViewHolder(View itemView, KajianAdaptor adapter){
            super(itemView);
            mJudul=(TextView) itemView.findViewById(R.id.judulData);
            mUstadz=(TextView) itemView.findViewById(R.id.namaData);
            mTanggal=(TextView) itemView.findViewById(R.id.tanggalData);
            this.mAdapter=adapter;
        }
    }
}
