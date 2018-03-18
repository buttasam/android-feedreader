package buttasam.cvut.cz.android_feedreader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.activity.ArticleDetailActivity;


public class ArticleListFragment extends Fragment {


    public ArticleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set listeners
        getActivity().findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArticleDetail(v);
            }
        });
    }

    public void showArticleDetail(View view) {
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        startActivity(intent);
    }

}
