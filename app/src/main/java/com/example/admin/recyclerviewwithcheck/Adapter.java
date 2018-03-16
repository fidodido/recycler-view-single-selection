package com.example.admin.recyclerviewwithcheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MenuViewHolder> {

    RecyclerView mRecyclerView;
    List<ProductMenu> productMenus;
    Context context;
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    public Adapter(Context context, ArrayList<ProductMenu> productMenus) {
        this.productMenus = productMenus;
        this.context = context;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;

        MenuViewHolder(View itemView) {

            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

    @Override
    public int getItemCount() {
        return productMenus.size();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MenuViewHolder pvh = new MenuViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(final MenuViewHolder menuViewHolder, final int i) {

        if (!itemStateArray.get(i, false)) {
            menuViewHolder.checkBox.setChecked(false);
        } else {
            menuViewHolder.checkBox.setChecked(true);
        }

        menuViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!itemStateArray.get(i, false)) {

                    // le cambio a todos los checkbox el valor a falso.
                    for (int k = 0; k < itemStateArray.size(); k++) {

                        // dejo todos en falso.
                        itemStateArray.put(k, false);
                    }

                    View child;

                    for (int i = 0; i < mRecyclerView.getChildCount(); i++) {

                        child = mRecyclerView.getChildAt(i);

                        //In case you need to access ViewHolder:
                        MenuViewHolder viewHolder = (MenuViewHolder) mRecyclerView.getChildViewHolder(child);

                        viewHolder.checkBox.setChecked(false);
                    }

                    menuViewHolder.checkBox.setChecked(true);
                    itemStateArray.put(i, true);

                } else {
                    menuViewHolder.checkBox.setChecked(false);
                    itemStateArray.put(i, false);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }
}
