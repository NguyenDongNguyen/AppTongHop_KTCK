package com.example.apptonghop_giuaki.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apptonghop_giuaki.R;
import com.example.apptonghop_giuaki.SignIn;

public class ProfileFragment extends Fragment {

    ImageButton imgLogOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        imgLogOut = (ImageButton) view.findViewById(R.id.imageLogout);
        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });

        return view;

    }
}
