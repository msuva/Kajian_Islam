package com.example.kajianislam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class CatatanKajian extends AppCompatActivity {
    private final LinkedList<Kajian> mKajian=new LinkedList<>();
    private int mCount=0;
    private RecyclerView mRecyclerView;
    private KajianAdaptor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_kajian);


        for(int i=0 ; i<20 ; i++){
            mKajian.addLast(new Kajian("Judul "+i, "Ustadz "+i, i+"/4/2020"));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new KajianAdaptor(this, mKajian);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

