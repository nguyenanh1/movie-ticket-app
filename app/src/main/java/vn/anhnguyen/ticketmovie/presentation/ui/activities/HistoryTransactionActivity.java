package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.presentation.presenter.IPresenterHistoryTrans;
import vn.anhnguyen.ticketmovie.presentation.presenter.impl.PresenterInjection;
import vn.anhnguyen.ticketmovie.presentation.ui.adapter.AdapterTransaction;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;
import vn.anhnguyen.ticketmovie.util.common.CommonUtil;

public class HistoryTransactionActivity extends BaseActivity implements IPresenterHistoryTrans.IViewHistoryTrans,
        AdapterTransaction.IOnEventTransaction {
    @BindView(R.id.recycler_transaction)
    RecyclerView mRecyclerTrans;
    @BindView(R.id.text_notify)
    CustomTextView mTextNotify;

    private List<TransMovie> mList;
    private AdapterTransaction mAdapter;

    private IPresenterHistoryTrans mPreenter;

    public static Intent getIntent(Context context){
        return new Intent(context,HistoryTransactionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_transaction);
        ButterKnife.bind(this);
        initToolbar();
        hideAvatar();
        showBack();
        hideMenuNavigation();
        setTitle(CommonUtil.getStringFromRes(R.string.history_transaction,this));
        initView();
        setUpRecyclerTrans();
    }

    private void setUpRecyclerTrans() {
        mList =new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mAdapter = new AdapterTransaction(mList,this);
        mRecyclerTrans.setLayoutManager(layoutManager);
        mRecyclerTrans.setAdapter(mAdapter);
        mRecyclerTrans.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getmButtonBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mPreenter.resume();
    }

    private void initView() {
        mPreenter = PresenterInjection.getInjection().newPresenterHistoryTrans(this);
    }


    @Override
    public void showHistoryTrans(List<TransMovie> list) {
        if(list.size()==0){
            mTextNotify.setVisibility(View.VISIBLE);
        }else {
            mTextNotify.setVisibility(View.GONE);
        }
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(TransMovie transMovie) {

    }
}
