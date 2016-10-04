package com.a1264d.a1264scout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<String> {
    String[] teams = {};
    String[] overalls = {};
    String[] OPRs = {};
    String[] AuPRs = {};
    String[] AxPRs = {};
    Context c;
    LayoutInflater inflater;


    public Adapter(Context context, String[] teams, String[] overalls, String[] OPRs, String[] AuPRs, String[] AxPRs) {
        super(context, R.layout.rankingrow);
        this.c = context;
        this.teams = teams;
        this.overalls = overalls;
        this.OPRs = OPRs;
        this.AuPRs = AuPRs;
        this.AxPRs = AxPRs;
    }

    public class ViewHolder{
        TextView teamsTv;
        TextView overallTv;
        TextView OPRTv;
        TextView AuPRTv;
        TextView AxPRTv;
    }

    public int getCount(){
        return teams.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rankingrow, null);
        }


        final ViewHolder holder=new ViewHolder();

        holder.teamsTv= (TextView) convertView.findViewById(R.id.rankingNumber);
        holder.overallTv= (TextView) convertView.findViewById(R.id.rankingOverall);
        holder.OPRTv= (TextView) convertView.findViewById(R.id.rankingOPR);
        holder.AuPRTv= (TextView) convertView.findViewById(R.id.rankingAuPR);
        holder.AxPRTv= (TextView) convertView.findViewById(R.id.rankingAxPR);

        holder.teamsTv.setText(teams[position]);
        holder.overallTv.setText("Overall: " + overalls[position]);
        holder.OPRTv.setText("OPR: " + OPRs[position]);
        holder.AuPRTv.setText("AuPR: " + AuPRs[position]);
        holder.AxPRTv.setText("AxPR: " + AxPRs[position]);

        return convertView;
    }
}
