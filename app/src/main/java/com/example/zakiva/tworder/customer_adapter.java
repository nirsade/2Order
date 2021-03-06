package com.example.zakiva.tworder;

import android.content.Context;
import android.database.DataSetObserver;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Nir Sade on 12/10/2015.
 */
public class customer_adapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private ArrayList<customer_list_group> mParent;

    public customer_adapter(Context context, ArrayList<customer_list_group> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }


    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(final int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.customer_list_group, viewGroup,false);
        }
        TextView textView = (TextView) view.findViewById(R.id.list_item_head);
        textView.setText(getGroup(groupPosition).toString());
        TextView key = (TextView) view.findViewById(R.id.key);
        key.setText(mParent.get(groupPosition).getItemKey());

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if(view == null){
            view = inflater.inflate(R.layout.customer_list_item, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_child);

        textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition));


        view.setTag(holder);

        //return the entire view
        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
    }

}
