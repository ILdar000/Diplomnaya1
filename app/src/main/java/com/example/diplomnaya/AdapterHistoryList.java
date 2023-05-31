package com.example.diplomnaya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.diplomnaya.entity.History;

import java.util.List;

public class AdapterHistoryList extends ArrayAdapter<History> {

    private LayoutInflater inflater;
    private int layout;
    private List<History> сlothingSizes;

    public AdapterHistoryList(Context context, int resource, List<History> сlothingSizes) {
        super(context, resource, сlothingSizes);
        this.сlothingSizes = сlothingSizes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView textViewInListRuShirt = view.findViewById(R.id.textViewInListRuResShirt);
        TextView textViewInListIntShirt = view.findViewById(R.id.textViewInListIntResShirt);
        TextView textViewInListEuShirt = view.findViewById(R.id.textViewInListEuResShirt);
        TextView textViewInListUsShirt = view.findViewById(R.id.textViewInListUsResShirt);
        TextView textViewInListRuJeans = view.findViewById(R.id.textViewInListRuResJeans);
        TextView textViewInListIntJeans = view.findViewById(R.id.textViewInListIntResJeans);
        TextView textViewInListEuJeans = view.findViewById(R.id.textViewInListEuResJeans);
        TextView textViewInListUsJeans = view.findViewById(R.id.textViewInListUsResJeans);
        TextView textViewInListHeight = view.findViewById(R.id.textViewInListHeight);
        TextView textViewInListSex = view.findViewById(R.id.textViewInListSex);

        History clothingSize = сlothingSizes.get(position);


        textViewInListRuShirt.setText(clothingSize.ruShirt());
        textViewInListIntShirt.setText(clothingSize.intShirt());
        textViewInListEuShirt.setText(clothingSize.euShirt());
        textViewInListUsShirt.setText(clothingSize.usShirt());
        textViewInListRuJeans.setText(clothingSize.ruJeans());
        textViewInListIntJeans.setText(clothingSize.intJeans());
        textViewInListEuJeans.setText(clothingSize.euJeans());
        textViewInListUsJeans.setText(clothingSize.usJeans());
        textViewInListHeight.setText(clothingSize.height());
        textViewInListSex.setText(clothingSize.sex());

        return view;
    }
}
