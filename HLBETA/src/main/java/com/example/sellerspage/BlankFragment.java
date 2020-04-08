package com.example.sellerspage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class BlankFragment extends Fragment {
    TextView txtBookName,txtAuthorName,txtEditiion,txtPrice;
    Button btnEdit,btnDelete;
    ImageView imgView;
    FirebaseFirestore firebaseFirestore;
    AlertDialog.Builder builder;

    public BlankFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTransaction mFragmentTransaction = getFragmentManager()
                .beginTransaction();
        mFragmentTransaction.addToBackStack(null);

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtAuthorName = view.findViewById(R.id.txtAuthorName);
        btnEdit= view.findViewById(R.id.btnEdit);
        txtBookName = view.findViewById(R.id.txtBookName);
        txtEditiion = view.findViewById(R.id.txtEdition);
        txtPrice = view.findViewById(R.id.txtPrice);
        imgView = view.findViewById(R.id.imgView);
        btnDelete = view.findViewById(R.id.btnDelete);
        builder = new AlertDialog.Builder(getActivity());
        firebaseFirestore =FirebaseFirestore.getInstance();

        txtBookName.setText(testing.profiles.get(testing.pos).getBookName());
        txtPrice.setText(testing.profiles.get(testing.pos).getPrice());
        txtEditiion.setText(testing.profiles.get(testing.pos).getEdition());
        txtAuthorName.setText(testing.profiles.get(testing.pos).getAuthorName());
        Picasso.get().load(testing.profiles.get(testing.pos).getImage1()).into(imgView);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                NavHostFragment.findNavController(BlankFragment.this)
                        .navigate(R.id.action_blankFragment_to_editFragment);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Are you sure to delete this book database")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                firebaseFirestore.collection("bookDatabase")
                                        .document(ListFragment.keys.get(testing.pos)).delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                ListFragment.profiles.remove(testing.pos);
                                                ListFragment.keys.remove(testing.pos);
                                                testing.pos =0;
                                                NavHostFragment.findNavController(BlankFragment.this)
                                                        .navigate(R.id.action_blankFragment_to_listFragment);
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }


        });
    }

}
