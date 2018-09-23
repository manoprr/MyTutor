package manop.mytutor.com.mytutor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manop.mytutor.com.mytutor.R;

public class ShowVideoFragment extends Fragment{

    private String urlString;

    public static ShowVideoFragment showVideoFragment(String urlString) {
        ShowVideoFragment showVideoFragment = new ShowVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Youtube", urlString);
        showVideoFragment.setArguments(bundle);
        return showVideoFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        urlString = getArguments().getString("Youtube");
        Log.d("22SepV2", "Youtube ==>" + urlString);

    }   //main method

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_video, container, false);


        return view;
    }
}
