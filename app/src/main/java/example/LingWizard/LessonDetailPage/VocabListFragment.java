package example.LingWizard.LessonDetailPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import example.LingWizard.R;
import example.LingWizard.XML.LessonPlan;
import example.LingWizard.XML.Vocab;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VocabListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VocabListFragment extends Fragment {
    private static final String ARG_PARAM1 = "language";
    private static final String ARG_PARAM2 = "unit";
    private static final String ARG_PARAM3 = "lesson";

    private String language;
    private String unit;
    private String lesson;
    private Vocab vocab;

    public VocabListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VocabListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VocabListFragment newInstance() {
        VocabListFragment fragment = new VocabListFragment();
       /* Bundle args = new Bundle();
        args.putString(ARG_PARAM1, language);
        args.putString(ARG_PARAM2, unit);
        args.putString(ARG_PARAM3,lesson);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            language = getArguments().getString(ARG_PARAM1);
            unit = getArguments().getString(ARG_PARAM2);
            lesson = getArguments().getString(ARG_PARAM3);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView view = (ListView) inflater.inflate(R.layout.fragment_vocab_list, container, false);
        if(getActivity() instanceof LessonDetailActivity){
            LessonPlan plan = ((LessonDetailActivity) getActivity()).getLessonPlan();
            vocab = plan.getVocab();
        }
        view.setAdapter(new VocabAdapter(vocab.getTerms(),vocab.getDefinitions(),inflater,R.layout.vocab_list_view));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

class VocabAdapter extends BaseAdapter {
    ArrayList<String> terms,definitions;
    LayoutInflater inflater;
    int layoutResourceId;
    public VocabAdapter(ArrayList<String> terms, ArrayList<String> definitions, LayoutInflater inflater,int layoutResourceId) {
        this.inflater = inflater;
        this.layoutResourceId = layoutResourceId;
        this.terms = terms;
        this.definitions = definitions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = inflater.inflate(layoutResourceId,parent,false);
        }
        TextView term = (TextView) convertView.findViewById(R.id.term);
        TextView definition = (TextView) convertView.findViewById(R.id.definition);
        term.setText(terms.get(position));
        definition.setText(definitions.get(position));
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount(){
        return terms.size();
    }
}
