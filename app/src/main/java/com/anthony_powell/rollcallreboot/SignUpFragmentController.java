package com.anthony_powell.rollcallreboot;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SGT_POWELL on 11/12/2016.
 */

public class SignUpFragmentController extends Fragment {

 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

  View view = inflater.inflate(R.layout.sign_up_fragment, container, false);
  Button signUpBtn = (Button) view.findViewById(R.id.sul_fragment_button);
  signUpBtn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    startActivity(new Intent(getActivity(), CourseListingsActivity.class));
   }
  });

  return view;
 }
}
