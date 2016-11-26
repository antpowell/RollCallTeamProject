package com.anthony_powell.rollcallreboot;

import android.app.Activity;
import android.content.Context;
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
}
