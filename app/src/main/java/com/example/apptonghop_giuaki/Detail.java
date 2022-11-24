package com.example.apptonghop_giuaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends Fragment {

    String name, describe, img;
    ImageButton btnBack;

    public Detail(String name, String describe, String img) {
        this.name = name;
        this.describe = describe;
        this.img = img;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_detail, container, false);

        btnBack = view.findViewById(R.id.buttonBack);

        ImageView imgDetail = view.findViewById(R.id.img_Detail);
        TextView txtTenDetail = view.findViewById(R.id.txt_TenDetail);
        TextView txtMotaDetail = view.findViewById(R.id.txt_MotaDetail);

        txtTenDetail.setText(name);
        txtMotaDetail.setText(describe);
        Glide.with(getContext()).load(img).into(imgDetail);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent1);
            }
        });
        return view;
    }
}
