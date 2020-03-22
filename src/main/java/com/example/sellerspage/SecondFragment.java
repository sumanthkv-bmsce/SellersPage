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
import android.os.Build;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class SecondFragment extends Fragment{

    EditText edtBookName;
    EditText edtAuthorName;
    EditText edtEdition;
    EditText edtPrice;
    StorageReference storageReference;
    List<String> li;


    Button btnSubmit;
    Bitmap bitmap;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Application");



    ImageButton[] imgBtnBook=new ImageButton[6];
    int[] flag=new int[6];
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flag[0] = 0;
        flag[1] = 0;
        flag[2] = 0;
        flag[3] = 0;
        flag[4] = 0;
        flag[5] = 0;

        li = new ArrayList<>();
        edtBookName = view.findViewById(R.id.edtBookName);
        edtAuthorName = view.findViewById(R.id.edtAuthorName);
        edtEdition = view.findViewById(R.id.edtEdition);
        edtPrice = view.findViewById(R.id.edtPrice);

        btnSubmit = view.findViewById(R.id.btnSubmit);

        imgBtnBook[0] = view.findViewById(R.id.imgBtnBook1);
        imgBtnBook[1] = view.findViewById(R.id.imgBtnBook2);
        imgBtnBook[2] = view.findViewById(R.id.imgBtnBook3);
        imgBtnBook[3] = view.findViewById(R.id.imgBtnBook4);
        imgBtnBook[4] = view.findViewById(R.id.imgBtnBook5);
        imgBtnBook[5] = view.findViewById(R.id.imgBtnBook6);

        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/

        imgBtnBook[0].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[0]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        imgBtnBook[1].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[1]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        imgBtnBook[2].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[2]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        imgBtnBook[3].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[3]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        imgBtnBook[4].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[4]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        imgBtnBook[5].setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    flag[5]=1;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((edtBookName.getText().toString().equals("")) || (edtAuthorName.getText().toString().equals("")) ||
                        (edtEdition.getText().toString().equals("")) || (edtPrice.getText().toString().equals("")) ||
                        li.size()<6) {

                    Toast.makeText(getActivity(),"No empty fields are acceptable",Toast.LENGTH_LONG).show();
                }
                else {
                    BookDetails book =
                            new BookDetails(
                                    edtBookName.getText().toString(),edtAuthorName.getText().toString(),
                                    edtEdition.getText().toString(),edtPrice.getText().toString(),
                                    li.get(0),li.get(1),
                                    li.get(2),li.get(3),li.get(4),li.get(5)
                            );
                    databaseReference.child(databaseReference.push().getKey()).setValue(book);

                    edtBookName.setText("");
                    edtAuthorName.setText("");
                    edtEdition.setText("");
                    edtPrice.setText("");

                    imgBtnBook[0].setImageResource(android.R.color.transparent);
                    imgBtnBook[1].setImageResource(android.R.color.transparent);
                    imgBtnBook[2].setImageResource(android.R.color.transparent);
                    imgBtnBook[3].setImageResource(android.R.color.transparent);
                    imgBtnBook[4].setImageResource(android.R.color.transparent);
                    imgBtnBook[5].setImageResource(android.R.color.transparent);

                    Toast.makeText(getActivity(),"Successfully created",Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);

                }
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

                    if (resultCode == Activity.RESULT_OK && resultCode == Activity.RESULT_OK) {
                        bitmap = setImageMe(data,storageReference);
                        if(flag[0]==1) {
                            imgBtnBook[0].setImageBitmap(bitmap);
                            flag[0]=0;
                        }
                        else if(flag[1]==1) {
                            imgBtnBook[1].setImageBitmap(bitmap);
                            Toast.makeText(getActivity(),"st2",Toast.LENGTH_SHORT).show();
                            flag[1]=0;
                        }
                        else if(flag[2]==1) {
                            imgBtnBook[2].setImageBitmap(bitmap);
                            Toast.makeText(getActivity(),"st3",Toast.LENGTH_SHORT).show();
                            flag[2]=0;
                        }
                        else if(flag[3]==1) {
                            imgBtnBook[3].setImageBitmap(bitmap);
                            Toast.makeText(getActivity(),"st4",Toast.LENGTH_SHORT).show();
                            flag[3]=0;
                        }
                        else if(flag[4]==1) {
                            imgBtnBook[4].setImageBitmap(bitmap);
                            Toast.makeText(getActivity(),"st5",Toast.LENGTH_SHORT).show();
                            flag[4]=0;
                        }
                        else if(flag[5]==1) {
                            imgBtnBook[5].setImageBitmap(bitmap);
                            Toast.makeText(getActivity(),"st5",Toast.LENGTH_SHORT).show();
                            flag[5]=0;
                        }

                    }

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

    public Bitmap setImageMe(Intent data,StorageReference storageReference) {
        Bitmap choosen = (Bitmap) data.getExtras().get("data");
        File file = createImageFile();
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(file);
            choosen.compress(Bitmap.CompressFormat.PNG, 70, fout);
            fout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(file);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContext()).getContentResolver(), uri);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, out);
            bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            storageReference = FirebaseStorage.getInstance().getReference("/Images/storage/emulated/0/DCIM/Camera")
                    .child(uri.getLastPathSegment());
            storageReference.putFile(uri);
            li.add(storageReference.toString());

        } catch (IOException e) {

        }
        return bitmap;
    }

}
