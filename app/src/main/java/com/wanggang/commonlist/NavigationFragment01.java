package com.wanggang.commonlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanggang.commonlist.test.Test01Entity;
import com.wanggang.commonlist.test.Test02Entity;
import com.wanggang.commonlist.test.Test03Entity;
import com.wanggang.commonlist.test.Test04Entity;
import com.wanggang.library.commonlist.BaseViewTypeEntity;
import com.wanggang.library.commonlist.CommonAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationFragment01.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationFragment01#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment01 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private CommonAdapter mAdapter;

    public NavigationFragment01() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NavigationFragment01.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment01 newInstance(String param1) {
        NavigationFragment01 fragment = new NavigationFragment01();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_navigation_fragment01, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonAdapter();
        recyclerView.setAdapter(mAdapter);

        testData();
        return view;
    }

    private void testData() {
        List<BaseViewTypeEntity> list = new ArrayList<>();
        if (TextUtils.equals(mParam1, "01")) {
            list.add(new Test02Entity("111111111"));

            Test03Entity test03Entity = new Test03Entity();
            test03Entity.setMenu1("模块1");
            test03Entity.setMenu2("模块2");
            test03Entity.setMenu3("模块3");
            test03Entity.setMenu4("模块4");
            list.add(test03Entity);

            list.add(new Test01Entity("111111111"));
            list.add(new Test01Entity("222222222"));
            list.add(new Test01Entity("333333333"));
            list.add(new Test01Entity("444444444"));
            list.add(new Test01Entity("555555555"));
            list.add(new Test01Entity("6666666666"));
            list.add(new Test01Entity("777777777"));
            list.add(new Test01Entity("888888888"));
            list.add(new Test01Entity("99999999999"));
        } else if (TextUtils.equals(mParam1, "02")) {
            list.add(new Test04Entity("标题1", "", "请选择", true));
            list.add(new Test04Entity("标题2", "", "请选择", true));
            list.add(new Test04Entity("标题3", "哈哈哈哈哈", "请选择", true));
            list.add(new Test04Entity("标题4", "", "请选择", true));
            list.add(new Test04Entity("标题5", "", "请选择", false));
            list.add(new BaseViewTypeEntity(CommonAdapterEnum.PADDING12.ordinal()));
            list.add(new Test04Entity("标题6", "", "请选择", true));
            list.add(new Test04Entity("标题7", "", "请选择", true));
            list.add(new Test04Entity("标题8", "", "请选择", false));
        } else if (TextUtils.equals(mParam1, "03")) {
            list.add(new Test04Entity("标题1", "", "请选择", true));
            list.add(new Test04Entity("标题2", "", "请选择", true));
            list.add(new Test04Entity("标题3", "", "请选择", true));
            list.add(new Test04Entity("标题4", "", "请选择", true));
            list.add(new Test04Entity("标题5", "", "请选择", false));
            list.add(new BaseViewTypeEntity(CommonAdapterEnum.PADDING12.ordinal()));
            list.add(new Test04Entity("标题6", "", "请选择", true));
            list.add(new Test04Entity("标题7", "", "请选择", true));
            list.add(new Test04Entity("标题8", "", "请选择", false));
        }

        mAdapter.addSource(list);
        mAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
