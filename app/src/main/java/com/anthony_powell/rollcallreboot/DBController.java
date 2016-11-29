package com.anthony_powell.rollcallreboot;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SGT_POWELL on 11/13/2016.
 */

public class DBController  {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser dbUser;
    private String dbLastName, dbEmail, dbPassword, dbStudentNumber;
    private Context c;
    private Boolean foundUser = Boolean.FALSE;
    private ArrayList<String> courseCodes;
    private FirebaseDatabase database;
    private DatabaseReference mRef = database.getReference();

    private DBController(){

    }
    private DBController(Context context){
        this.c = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public DBController(Context context, String dbLastName, String dbEmail, String dbPassword, String dbStudentNumber) {
        this.c = context;
        this.dbLastName = dbLastName;
        this.dbEmail = dbEmail;
        this.dbPassword = dbPassword;
        this.dbStudentNumber = dbStudentNumber;

    }

    public void createUserWithEmail(){
        mAuth.createUserWithEmailAndPassword(dbEmail, dbPassword).addOnCompleteListener((Activity) c, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(!task.isSuccessful()){
                    Toast.makeText(c, "Authentication failed", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(layout,"Authentication failed", Snackbar.LENGTH_SHORT ).show();
                }else{

                }

            }
        });

    }

    public void createUserWithGmail(){

    }

    public Boolean hasUser(){return foundUser;}

    public FirebaseUser getDbUser() {
        mAuth.addAuthStateListener(mAuthListener);
        return dbUser;
    }

    public String getDbLastName() {
        return dbLastName;
    }

    public String getDbEmail() {
        return dbEmail;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbStudentNumber() {
        return dbStudentNumber;
    }

    public void userSignInOutListener(){

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser  user = firebaseAuth.getCurrentUser();
                if(user != null){
                    dbUser = user;
                    foundUser = true;
                }else{
                    foundUser = false;
                }

            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void stopUserSignInOutListener(){
     if(mAuthListener != null){
         mAuth.removeAuthStateListener(mAuthListener);
     }
    }

    public void getCourseList(){
        courseCodes = null;

        final ProgressDialog progressDialog = new ProgressDialog(c);

        progressDialog.show();
        mRef.child("Courses").child("Codes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseCodes = (ArrayList<String>)dataSnapshot.getValue();
                Toast.makeText(c, "Course codes found...", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(c, "Unable to get course codes...", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    public ArrayList<String> getCourseCodes() {
        return courseCodes;
    }
}
