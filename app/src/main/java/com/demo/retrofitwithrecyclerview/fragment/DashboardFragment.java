package com.demo.retrofitwithrecyclerview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.demo.retrofitwithrecyclerview.R;
import com.demo.retrofitwithrecyclerview.adapter.CallDetailsAdapter;
import com.demo.retrofitwithrecyclerview.model.DashboardResponseModel;
import com.demo.retrofitwithrecyclerview.retrofit.RetrofitClient;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    Context mContext;
    int page = 0;
    @BindView(R.id.contactListRecyclerView)
    SuperRecyclerView contactListRecyclerView;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    private boolean isRefresh = false;
    private OnFragmentInteractionListener mListener;
    CallDetailsAdapter callListAdapter;
    private LinearLayoutManager mLayoutManager;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        //((MainActivity)getActivity()).setSupportActionBar(toolbar);

        prepareRecycleView();
        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mContext = getActivity();

        dashboard_callList(page);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void prepareRecycleView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        contactListRecyclerView.setLayoutManager(mLayoutManager);
      /*  contactListRecyclerView.addItemDecoration(new DividerItemDecoration());*/

        contactListRecyclerView.setRefreshListener(this);
        contactListRecyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        contactListRecyclerView.setupMoreListener(this, 5);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 0;
        dashboard_callList(page);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        page += 1;
        dashboard_callList(page);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void dashboard_callList(final int pageIndex) {
        {
            //ProgressLoader.getInstance().showProgressDialog(mContext, progressMessage);

            HashMap<String, String> requestObject = new HashMap<>();

            requestObject.put("token", "9c1afa746b14e1ede458d429b6c1e9b5");
            requestObject.put("dashboard", "true");
            requestObject.put("page_no", String.valueOf(pageIndex));
            //page = 0;


            Call<DashboardResponseModel> apiCallRequest = RetrofitClient.instance()
                    .getAPIServiceInstance()
                    .dashboard(requestObject);

            apiCallRequest.enqueue(new Callback<DashboardResponseModel>() {
                @Override
                public void onResponse(Call<DashboardResponseModel> call, Response<DashboardResponseModel> response) {
                    if (response.isSuccessful()) {
                        Timber.e("list size====" + response.body().getData().size());
                        //ProgressLoader.getInstance().dismissProgressDialog();
                        if (pageIndex == 0) {
                            if (!isRefresh) {
                                callListAdapter = new CallDetailsAdapter(getActivity(), (ArrayList<DashboardResponseModel.DashboardResponse>) response.body().getData());
                                contactListRecyclerView.setAdapter(callListAdapter);
                            } else {
                                callListAdapter.refreshList(response.body().getData());
                            }
                        } else {
                            callListAdapter.addAll(response.body().getData());
                        }
                    } else {
                        if (contactListRecyclerView.isLoadingMore()) {
                            contactListRecyclerView.hideMoreProgress();
                        }
                    }

                }

                @Override
                public void onFailure(Call<DashboardResponseModel> call, Throwable t) {
                    //ProgressLoader.getInstance().dismissProgressDialog();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dashboard, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_call:
                /*Intent intent_add_new_call = new Intent(getActivity(), AddNewCallActivity.class);
                startActivity(intent_add_new_call);*/
                break;

            case R.id.action_archive:
                /*Intent intent_archived = new Intent(getActivity(), ArchiveActivity.class);
                startActivity(intent_archived);*/
                break;

            case R.id.action_search:
                //displayAdvanceSearch();
                break;
        }

        return false;
    }
}
