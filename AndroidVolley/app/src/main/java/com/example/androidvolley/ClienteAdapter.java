package com.example.androidvolley;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClienteAdapter extends ArrayAdapter<String> {

    public ClienteAdapter(Context context, List<String> datos) {
        super(context, 0, datos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String clienteInfo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(clienteInfo);

        return convertView;
    }
}
