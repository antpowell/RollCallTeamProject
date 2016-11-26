package com.anthony_powell.rollcallreboot;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SGT_POWELL on 11/12/2016.
 */

public class SignUpFragmentController extends Fragment {

 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.sign_up_fragment, container, false);

  return view;
 }
}
