package com.anthony_powell.rollcallreboot;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import info.hoang8f.android.segmented.SegmentedGroup;

public class RegisterActivity extends AppCompatActivity {
 FrameLayout frame;
 SignUpFragmentController signUpFragmentController;
 SignInFragmentController signInFragmentController;
 SegmentedGroup segmentedGroup;
 FragmentTransaction ft;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_register);

  signInFragmentController = new SignInFragmentController();
  signUpFragmentController = new SignUpFragmentController();
//  ft = getFragmentManager().beginTransaction();
//  ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

  getFragmentManager().beginTransaction()
    .add(R.id.fl_register_container,signInFragmentController)
    .commit();

  viewHooks();
  hooksHandler();


 }

 private void viewHooks(){
  frame = (FrameLayout)findViewById(R.id.fl_register_container);
  segmentedGroup = (SegmentedGroup)findViewById(R.id.segmented_group);

 }

 private void hooksHandler(){
  segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
   @Override
   public void onCheckedChanged(RadioGroup radioGroup, int i) {
    switch(i){
     case R.id.rb_sign_in_segment:
      getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
      .replace(R.id.fl_register_container, signInFragmentController).commit();
      break;
     case R.id.rb_sign_up_segment:
      getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        .replace(R.id.fl_register_container, signUpFragmentController).commit();
      break;
    }
//    ft.commit();

   }
  });
 }
}
