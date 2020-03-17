package com.example.sellerspage;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class SecondFragment extends Fragment {

    EditText edtBookName;
    EditText edtAuthorName;
    EditText edtEdition;
    EditText edtPrice;
    ImageButton imgBtnBook;
    Button btnSubmit;
    Bitmap bitmap;
    StorageReference storageReference;
    static final int num = 1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Application");


    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    final  int imageCode= 10000;
    public static final String CAMERA_PREF = "camera_pref";
    final int IMAGE_REQUEST_CODE=1000;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtBookName = view.findViewById(R.id.edtBookName);
        edtAuthorName = view.findViewById(R.id.edtAuthorName);
        edtEdition = view.findViewById(R.id.edtEdition);
        edtPrice = view.findViewById(R.id.edtPrice);
        imgBtnBook = view.findViewById(R.id.imgBtnBook);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/

        imgBtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(getContext());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetails book =
                        new BookDetails(
                                edtBookName.getText().toString(),edtAuthorName.getText().toString(),
                                edtEdition.getText().toString(),edtPrice.getText().toString(),storageReference.toString());

                if((edtBookName.getText().toString().equals("")) || (edtAuthorName.getText().toString().equals("")) ||
                        (edtEdition.getText().toString().equals("")) || (edtPrice.getText().toString().equals("")) ||
                        (imgBtnBook==null)) {

                    Toast.makeText(getActivity(),"No empty fields are acceptable",Toast.LENGTH_LONG).show();
                }
                else {
                    databaseReference.child(databaseReference.push().getKey()).setValue(book);

                    edtBookName.setText("");
                    edtAuthorName.setText("");
                    edtEdition.setText("");
                    edtPrice.setText("");
                    imgBtnBook.setImageResource(android.R.color.background_light);
                    Toast.makeText(getActivity(),"Successfully created",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != Activity.RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        Bitmap choosen = (Bitmap) data.getExtras().get("data");
                        File file = createImageFile();
                            FileOutputStream fout;
                            try {
                                fout = new FileOutputStream(file);
                                choosen.compress(Bitmap.CompressFormat.PNG,70,fout);
                                fout.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Uri uri=Uri.fromFile(file);
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContext()).getContentResolver(), uri);
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,70,out);
                                bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                                bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                                imgBtnBook.setImageBitmap(bitmap);
                                storageReference = FirebaseStorage.getInstance().getReference("/Images/storage/emulated/0/DCIM/Camera")
                                        .child(uri.getLastPathSegment());
                                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(getActivity(),"Uploaded successfully",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"Upload unsuccessfull",Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                            catch (IOException e) {

                            }
                    }


                    break;
                case 1:
                    if (resultCode == Activity.RESULT_OK && data != null) {
                            Uri choosenImage = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),choosenImage);
                                 bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                                imgBtnBook.setImageBitmap(bitmap);
                                storageReference = FirebaseStorage.getInstance().getReference("Images")
                                        .child(choosenImage.getLastPathSegment());
                                storageReference.putFile(choosenImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(getActivity(),"Uploaded successfully",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"Upload unsuccessfull",Toast.LENGTH_SHORT).show();

                                    }
                                });


                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    break;
            }
        }
    }



    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File mFileTemp = null;
        String root=getActivity().getDir("my_sub_dir",Context.MODE_PRIVATE).getAbsolutePath();
        File myDir = new File(root + "/Img");
        if(!myDir.exists()){
            myDir.mkdirs();
        }
        try {
            mFileTemp=File.createTempFile(imageFileName,".jpg",myDir.getAbsoluteFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return mFileTemp;
    }



}
