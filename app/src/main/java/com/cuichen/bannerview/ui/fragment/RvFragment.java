package com.cuichen.bannerview.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.RvAdapter;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RvFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RvFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RvFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RvFragment newInstance() {
        return new RvFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_rv, container, false);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("BannerFragmentAct-Rv", "setUserVisibleHint: "+isVisibleToUser);
    }

    boolean isLoadData; //是否加载过数据了
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isLoadData) {
            isLoadData = true;
            RecyclerView rv = view.findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
            RvAdapter rvAdapter = new RvAdapter();
            rvAdapter.setDatas(Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
            rv.setAdapter(rvAdapter);
            Log.i("BannerFragmentAct-Rv", "BannerFragmentAct-Rv-onViewCreated: ");
        }
    }

}