package com.example.sellerspage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditFragment extends Fragment {

    EditText edtBook,edtAuthor,editEdition,editPrice;
    ImageView imageView;
    Button btnUpdate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AlertDialog.Builder builder;
    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Application");
        edtBook = view.findViewById(R.id.edtBook);
        edtAuthor= view.findViewById(R.id.edtAuthor);
        editEdition = view.findViewById(R.id.editEdition);
        editPrice = view.findViewById(R.id.editPrice);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        imageView = view.findViewById(R.id.imageView);
        builder = new AlertDialog.Builder(getActivity());

        edtBook.setText(testing.profiles.get(testing.pos).getBookName());
        edtAuthor.setText(testing.profiles.get(testing.pos).getAuthorName());
        editEdition.setText(testing.profiles.get(testing.pos).getEdition());
        editPrice.setText(testing.profiles.get(testing.pos).getPrice());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edtBook.getText().toString().equals("")) || (edtAuthor.getText().toString().equals("")) ||
                        (editEdition.getText().toString().equals("")) || (editPrice.getText().toString().equals("")))
                {
                    Toast.makeText(getContext(), "Empty Values are not acceptable", Toast.LENGTH_SHORT).show();
                }
                else {
                    builder.setMessage("Are you sure to Update this book database")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            databaseReference.child(ListFragment.keys.get(testing.pos))
                                                    .child("bookName").setValue(edtBook.getText().toString());
                                            databaseReference.child(ListFragment.keys.get(testing.pos))
                                                    .child("authorName").setValue(edtAuthor.getText().toString());
                                            databaseReference.child(ListFragment.keys.get(testing.pos))
                                                    .child("edition").setValue(editEdition.getText().toString());
                                            databaseReference.child(ListFragment.keys.get(testing.pos))
                                                    .child("price").setValue(editPrice.getText().toString());
                                            Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                                            NavHostFragment.findNavController(EditFragment.this)
                                                    .navigate(R.id.action_editFragment_to_listFragment);
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

            }
        });


    }
}
