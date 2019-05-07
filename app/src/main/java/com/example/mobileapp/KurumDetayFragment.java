package com.example.mobileapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KurumDetayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KurumDetayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KurumDetayFragment extends Fragment {
    Kurum kurum;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KurumDetayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KurumDetayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KurumDetayFragment newInstance(String param1, String param2) {
        KurumDetayFragment fragment = new KurumDetayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView imageviewKurumDetay;
    TextView textviewKurumDetayAd;
    Button buttonKurumDetayBagislar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_kurum_detay,container,false);

        imageviewKurumDetay = (ImageView) RootView.findViewById(R.id.imageviewKurumDetay);//Fotoyu tut
        textviewKurumDetayAd=(TextView)RootView.findViewById(R.id.textviewKurumDetayKurumadi);
        textviewKurumDetayAd.setText(kurum.getKurumAdi());
        TextView textviewKurumDetayKurumayrintisiveri = (TextView) RootView.findViewById(R.id.textviewKurumDetayKurumayrintisiveri);

        TextView textviewKurumDetaySosyalmedyaveri = (TextView) RootView.findViewById(R.id.textviewKurumDetaySosyalmedyaveri);

        TextView textviewKurumDetayIstatistiklerveri = (TextView) RootView.findViewById(R.id.textviewKurumDetayIstatistiklerveri);

        LinearLayout linearlayoutKurumDetay = (LinearLayout) RootView.findViewById(R.id.linearlayoutKurumDetay);
        buttonKurumDetayBagislar=(Button)RootView.findViewById(R.id.buttonKurumDetayBagislar);
        buttonKurumDetayBagislar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final KurumBagislarFragment kurumBagislarFragment = new KurumBagislarFragment();
                kurumBagislarFragment.kurum=kurum;
                System.out.println(kurumBagislarFragment.kurum.getKurumAdi());
                setFragment(kurumBagislarFragment);
            }
        });

        String[] buton={"Bagis-1","Bagis-2","Bagis-3","Bagis-4"};

        /*for(int i = 0 ; i < 4 ; i++) {
            linearlayoutKurumDetay.addView(new ButonOlustur(getActivity(),buton[i]));
        }*/

        return RootView;
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
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}
