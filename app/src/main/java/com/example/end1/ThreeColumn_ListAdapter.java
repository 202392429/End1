package com.example.end1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<User> {
           // Flater
    private LayoutInflater mInflater;
         // Array Class User
    private ArrayList<User> users;
      // Variable view
    private int mViewResourceId;
    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView id = (TextView) convertView.findViewById(R.id.textFavFood);
            TextView firstName = (TextView) convertView.findViewById(R.id.textFirstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.textLastName);
            TextView eamil = (TextView) convertView.findViewById(R.id.texteamil);
            // if all text not equals null
            if (id != null) {
                id.setText(user.getID());
            }

            if (firstName != null) {
                firstName.setText(user.getFirstName());
            }

            if (lastName != null) {
                lastName.setText((user.getLastName()));
            }


            if (eamil != null) {
                eamil.setText((user.getEmail()));
            }
        }

        return convertView;
    }
}
