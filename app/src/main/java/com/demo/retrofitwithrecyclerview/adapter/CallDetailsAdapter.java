package com.demo.retrofitwithrecyclerview.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.demo.retrofitwithrecyclerview.R;
import com.demo.retrofitwithrecyclerview.model.DashboardResponseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallDetailsAdapter extends RecyclerView.Adapter<CallDetailsAdapter.ViewHolder> {

    public ArrayList<DashboardResponseModel.DashboardResponse> data;
    private TextDrawable.IShapeBuilder textCharacterBuilder;
    //private int actualTempFontSize;
    private Activity instance;


    public CallDetailsAdapter(Activity instance, ArrayList<DashboardResponseModel.DashboardResponse> data) {
        this.data = data;
        this.instance = instance;
        textCharacterBuilder = TextDrawable.builder();
        //actualTempFontSize = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, instance.getResources().getDisplayMetrics()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_contacts, parent, false);
        return new ViewHolder(view, new ItemClickListener() {
            @Override
            public void onItemClicked(int position) {
               /* Intent intent_call_details = new Intent(instance, ViewCallActivity.class);
                intent_call_details.putExtra(API.CD_ID, data.get(position).getCdId());
                intent_call_details.putExtra(API.CD_FROM, data.get(position).getCdFrom());
                intent_call_details.putExtra(API.CD_PHONE_NO, data.get(position).getCdPhoneNumber());
                intent_call_details.putExtra(API.CD_EMAIL, data.get(position).getCdEmail());
                intent_call_details.putExtra(API.CD_PROVIDER_NAME, data.get(position).getPrvName());
                intent_call_details.putExtra(API.CD_RESPONSIBLE_PERSON, data.get(position).getResponsiblePerson());
                intent_call_details.putExtra(API.CD_REASON_CALL, data.get(position).getCdReasonForCall());
                instance.startActivity(intent_call_details);*/
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DashboardResponseModel.DashboardResponse dashboardResponse = data.get(position);
        holder.userNameTextView.setText(dashboardResponse.getCdFrom());
        //holder.dateTextView.setText(dashboardResponse.getCdCreatedDate());
        holder.responsibleNameTextView.setText(dashboardResponse.getResponsiblePerson());
        holder.contactPhoneNumberTextView.setText(dashboardResponse.getCdPhoneNumber());

        String status = dashboardResponse.getCdStatus();
        if (status.equals("Not Started")){
            holder.statusTextView.setText(dashboardResponse.getCdStatus());
            holder.statusTextView.setTextColor(ContextCompat.getColor(instance,R.color.phonecall_red));
            //#f44336 - Not started
        }else{
            holder.statusTextView.setText(dashboardResponse.getCdStatus());
            holder.statusTextView.setTextColor(ContextCompat.getColor(instance,R.color.phonecall_yellow));
            //#FFEB3B - In Progress
        }

        String kpi = dashboardResponse.getCdKPI();
        if (!kpi.isEmpty()){
            holder.kpiTimeTextView.setText(dashboardResponse.getCdKPI());
        }else{
            holder.kpiTimeTextView.setText("00:00");
        }

        Boolean kpi_overdue = dashboardResponse.getCdIsOverdue();
        if (kpi_overdue.equals(true)){
            holder.kpiTimeTextView.setTextColor(ContextCompat.getColor(instance,R.color.phonecall_red));
            holder.kpiTimeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list_contact_clock_red, 0, 0, 0);
        }else{
            holder.kpiTimeTextView.setTextColor(ContextCompat.getColor(instance,R.color.phonecall_green));
            holder.kpiTimeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list_contact_clock, 0, 0, 0);
        }

        /*String abbriviation_color_code = "#"+dashboardResponse.getPrvAbbrColor();
        Drawable typeDrawable = textCharacterBuilder.beginConfig().fontSize(actualTempFontSize).bold().endConfig().buildRound(dashboardResponse.getPrvAbbr(), Color.parseColor(abbriviation_color_code));
        holder.providerImageView.setImageDrawable(typeDrawable);*/

        /*if (model.getNotesCount().equalsIgnoreCase("0")) {
            holder.notesCountTextView.setBackgroundResource(R.drawable.green_circular_drawable);
        } else {
            holder.notesCountTextView.setBackgroundResource(R.drawable.red_circular_drawable);
        }*/
        /*holder.notesCountTextView.setBackgroundResource(R.drawable.green_circular_drawable);
        holder.notesCountTextView.setText("0");*/

        SimpleDateFormat systm = new SimpleDateFormat("MMM,dd yyyy hh:mm a");
        Date date1 = null;
        try {
            date1 = systm.parse(dashboardResponse.getCdCreatedDate());
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMM dd, hh:mm a");
        String formated_created_dt = postFormater.format(date1);
        holder.dateTextView.setText(""+formated_created_dt);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(DashboardResponseModel.DashboardResponse string) {
        insert(string, data.size());
    }

    public void insert(DashboardResponseModel.DashboardResponse string, int position) {
        data.add(position, string);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void refreshList(List<DashboardResponseModel.DashboardResponse> strings) {
        data.clear();
        data.addAll(strings);
        notifyDataSetChanged();
    }

    public void addAll(List<DashboardResponseModel.DashboardResponse> strings) {
        data.addAll(strings);
        notifyDataSetChanged();
    }

    private interface ItemClickListener {
        void onItemClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.providerImageView)
        ImageView providerImageView;
        @BindView(R.id.userNameTextView)
        TextView userNameTextView;
        @BindView(R.id.dateTextView)
        TextView dateTextView;
        @BindView(R.id.responsibleNameTextView)
        TextView responsibleNameTextView;
        @BindView(R.id.statusTextView)
        TextView statusTextView;
        @BindView(R.id.callImageView)
        ImageView callImageView;
        @BindView(R.id.contactPhoneNumberTextView)
        TextView contactPhoneNumberTextView;
        @BindView(R.id.callLayout)
        RelativeLayout callLayout;
        @BindView(R.id.kpiTimeTextView)
        TextView kpiTimeTextView;
        @BindView(R.id.notesCountTextView)
        TextView notesCountTextView;
        private ItemClickListener mItemClickListener;

        public ViewHolder(View itemView, ItemClickListener mItemClickListener) {
            super(itemView);
            this.mItemClickListener = mItemClickListener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClicked(getAdapterPosition());
        }
    }

}
