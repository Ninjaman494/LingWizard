package example.LingWizard.LessonDetailPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.LingWizard.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VocabCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VocabCardFragment extends Fragment {
    private static final String ARG_PARAM1 = "term";
    private static final String ARG_PARAM2 = "definition";

    private String term;
    private String definition;


    public VocabCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param term Parameter 1.
     * @param definition Parameter 2.
     * @return A new instance of fragment VocabCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VocabCardFragment newInstance(String term, String definition) {
        VocabCardFragment fragment = new VocabCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, term);
        args.putString(ARG_PARAM2, definition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            term = getArguments().getString(ARG_PARAM1);
            definition = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocab_card, container, false);
    }

}
