package com.hub.gui.wag.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hub.gui.wag.R;
import com.hub.gui.wag.adapter.UserAdapter;
import com.hub.gui.wag.api.StackExchangeSite;
import com.hub.gui.wag.manager.UserManager;
import com.hub.gui.wag.model.ResponseWrapper;
import com.hub.gui.wag.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import timber.log.Timber;

public class UserFragment extends Fragment implements UserManager.UserManagerListener {

    public static final String KEY_USERS = "users";

    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ViewGroup mNoDataLayout;
    private Button mRefreshButton;

    private boolean mIsLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        mNoDataLayout = v.findViewById(R.id.layout_refresh);
        mRefreshButton = v.findViewById(R.id.btn_refresh);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoDataLayout.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(true);
                requestUsers();
            }
        });

        mRecyclerView = v.findViewById(R.id.recycler_view_user);
        //add a divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        mSwipeRefreshLayout = v.findViewById(R.id.user_swiperefreshlayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestUsers();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserManager.getsInstance().registerListener(this);

        mAdapter = new UserAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //change the number of column in the grid depending on the device screen size
        int spanCount = 1;
        Resources resources = getContext().getResources();

        if(resources != null) {
            spanCount = resources.getInteger(R.integer.grid_span_count);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        if(savedInstanceState == null){
            requestUsers();
            mSwipeRefreshLayout.setRefreshing(true);
        }
        else{
            List<User> list = savedInstanceState.getParcelableArrayList(KEY_USERS);
            Timber.e("saveInstanceState not null %s",list);
            //restore the user previously downloaded
            if(list != null) {
                mAdapter.setUserList(list);
            }
            else{
                requestUsers();
                mSwipeRefreshLayout.setRefreshing(true);
            }
        }
    }

    private void requestUsers(){
        //don't send another request if a request is in progress
        if(mIsLoading)
            return;

        mIsLoading = true;
        UserManager.getsInstance().requestUsers(StackExchangeSite.STACK_OVERFLOW,100);
    }

    @Override
    public void onUserRequestReceived(ResponseWrapper<User> response) {
        Timber.i("success");

        mSwipeRefreshLayout.setRefreshing(false);

        refreshDisplay(response.getItems());

        String message = getContext().getResources().getString(R.string.quota,+response.getQuotaRemaining());
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();

        mIsLoading = false;

    }

    @Override
    public void onUserRequestFailed() {
        Timber.i("failure");
        mSwipeRefreshLayout.setRefreshing(false);
        mIsLoading = false;
        refreshDisplay(null);

    }

    /**
     * Update the screen to display the new list of users<br>
     * If the list is empty or null, it will show the refresh button if no previous data was loaded
     * @param newUserList
     */
    private void refreshDisplay(List<User> newUserList){
        Timber.i("refreshDisplay %s",newUserList);

        //if we don't have any previous data loaded and the new list is empty,
        // we hide the RecyclerView and show the refresh button
        if((newUserList == null || newUserList.size() == 0) && mAdapter.getItemCount() == 0){
            mSwipeRefreshLayout.setVisibility(View.GONE);
            mNoDataLayout.setVisibility(View.VISIBLE);
        }
        else{
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mNoDataLayout.setVisibility(View.GONE);
            //Don't swap the existing list with an empty list
            if(newUserList != null && newUserList.size() > 0)
                mAdapter.setUserList(newUserList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManager.getsInstance().unregisterListener(this);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the users so we don't have to request them again when the activity will restore
        List<User> currentList = mAdapter.getUserList();
        if(currentList == null)
            return;

        outState.putParcelableArrayList(KEY_USERS,new ArrayList<Parcelable>(currentList));
    }
}
