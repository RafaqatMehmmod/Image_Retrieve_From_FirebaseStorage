package com.example.imageretrievefirebasestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    StorageReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseStorage.getInstance().getReference("/");


        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference reference : listResult.getPrefixes()) {
                    String fName = reference.getName();

                    if (fName.equals("F1")) {
                        ref.child(fName).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                for (StorageReference reference1 : listResult.getItems()) {
                                    Log.i("rafaqat..F1..", reference1.getName());
                                }
                            }
                        });

                    } else if (fName.equals("F2")) {
                        ref.child(fName).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                for (StorageReference reference1 : listResult.getItems()) {
                                    Log.i("rafaqat..F2..", reference1.getName());
                                }
                            }
                        });

                    } else if (fName.equals("F3")) {
                        ref.child(fName).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                for (StorageReference reference1 : listResult.getItems()) {

                                    reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Log.i("rafaqat.3..", uri.toString());
                                        }
                                    });
                                }
                            }
                        });
                    }

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("rafaqat", "onFailure: " + e.getMessage());
            }
        });

    }
}