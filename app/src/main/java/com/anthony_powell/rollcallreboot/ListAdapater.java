package com.anthony_powell.rollcallreboot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by SGT_POWELL on 11/26/2016.
 */

public class ListAdapater extends ArrayAdapter<String> {

 public ListAdapater(Context context, int resource) {
  super(context, resource);
 }

 public ListAdapater(Context context, int resource, List<String> objects) {
  super(context, resource, objects);

 }
}
