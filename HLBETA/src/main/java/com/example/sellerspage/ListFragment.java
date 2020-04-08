package com.example.sellerspage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class ListFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    //private CollectionReference collectionReference = firebaseFirestore.collection("bookDatabase");
    RecyclerView recyclerView;
    static ArrayList<Profile> profiles;
    testing myAdapter;
    static List<String> keys;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTransaction mFragmentTransaction = getFragmentManager()
                .beginTransaction();
        mFragmentTransaction.addToBackStack(null);

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        //databaseReference = firebaseDatabase.getReference("Application");
        recyclerView = view.findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        profiles = new ArrayList<Profile>();
        keys = new ArrayList<String>();
    }



    @Override
    public void onResume() {
        super.onResume();

        firebaseFirestore.collection("bookDatabase")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            //Profile p = documentSnapshot.toObject(Profile.class);

                            //profiles.add(p);

                            String auth = documentSnapshot.getString("AUTHORNAME");
                            String book = documentSnapshot.getString("BOOKNAME");
                            String edi = documentSnapshot.getString("EDITION");
                            String pri = documentSnapshot.getString("PRICE");
                            String img1 = documentSnapshot.getString("IMAGE1");
                            String img2 = documentSnapshot.getString("IMAGE2");
                            String img3 = documentSnapshot.getString("IMAGE3");
                            String img4 = documentSnapshot.getString("IMAGE4");
                            String img5 = documentSnapshot.getString("IMAGE5");
                            String img6 = documentSnapshot.getString("IMAGE6");
                            Profile p = new Profile(book,auth,edi,img1,img2,img3,img4,img5,img6,pri);
                            keys.add(documentSnapshot.getId());
                            profiles.add(p);
                            //Toast.makeText(getContext(),documentSnapshot.getString("AUTHORNAME"),Toast.LENGTH_SHORT).show();
                        }

                        myAdapter = new testing(getContext(),profiles,ListFragment.this);
                        recyclerView.setAdapter(myAdapter);
                    }
                });
    }




}
