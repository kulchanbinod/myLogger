package com.usc.logger.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usc.logger.R;
import com.usc.logger.controllers.LogActivity;
import com.usc.logger.fragments.LogViewFragment;
import com.usc.logger.models.Log;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogHolder> {

    private Context mContext;
    private List<Log> mLogs;

    public LogAdapter(Context context, List<Log> logs){
        this.mContext = context;
        this.mLogs = logs;
    }

    @Override
    public LogHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.log_item,parent,false);
        return new LogHolder(view);

    }

    @Override
    public void onBindViewHolder(LogHolder holder, int position) {

        final Log myLog = mLogs.get(position);

        holder.bindLog(myLog);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                LogViewFragment myFragment = new LogViewFragment();

                Bundle bundle = new Bundle();
                bundle.putString(LogActivity.EXTRA_LOG_ID, myLog.getId().toString());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mLogs.size();
    }

    public class LogHolder extends RecyclerView.ViewHolder  {

        private Log mLog;

        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtDesc;

        public LogHolder(final View itemView){
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtDesc = (TextView) itemView.findViewById(R.id.desc);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void bindLog(Log log){
            mLog = log;
            txtDate.setText(mLog.getDate());
            txtTitle.setText(mLog.getTitle());
            txtDesc.setText(mLog.getDestination());
        }

    }

    public void setLogs(List<Log> logs){
        mLogs = logs;
    }

}
