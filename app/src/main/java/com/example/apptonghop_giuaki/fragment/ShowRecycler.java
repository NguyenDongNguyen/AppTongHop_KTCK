package com.example.apptonghop_giuaki.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptonghop_giuaki.AddCar;
import com.example.apptonghop_giuaki.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ShowRecycler extends Fragment {

    RecyclerView recyclerView;
    CarAdapter mainAdapter;

    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_list,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<Car> options =
                new FirebaseRecyclerOptions.Builder<Car>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cars"), Car.class)
                        .build();

        mainAdapter = new CarAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        //button add firebase
        floatingActionButton= (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddCar.class)  ;
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}
