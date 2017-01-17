package example.LingWizard.LessonDetailPage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import example.LingWizard.LessonActivity;
import example.LingWizard.LessonFragment;
import example.LingWizard.R;
import example.LingWizard.XML.Grammar;
import example.LingWizard.XML.LessonPlan;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GrammarListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrammarListFragment extends Fragment {
    private static final String ARG_PARAM1 = "language";
    private static final String ARG_PARAM2 = "unit";
    private static final String ARG_PARAM3 = "lesson";

    private String language;
    private String unit;
    private String lesson;
    private Grammar grammar;
    private LessonPlan plan;

    public GrammarListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GrammarListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GrammarListFragment newInstance() {
        GrammarListFragment fragment = new GrammarListFragment();
  /*      Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
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
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.fragment_grammar_list, container, false);
        if(getActivity() instanceof LessonDetailActivity){
            plan = ((LessonDetailActivity) getActivity()).getLessonPlan();
            grammar = plan.getGrammar();
        }
        view.setAdapter(new GrammarAdapter(grammar,inflater,R.layout.grammar_list_view));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> pages = grammar.getPages(position);
                ArrayList<String> text = new ArrayList<String>();
                for(String s:pages){
                    int Section = Character.getNumericValue(s.charAt(0)) -1;
                    int Lesson = Character.getNumericValue(s.charAt(2))-1;
                    String lesson_text = plan.getLessonFragment(Section,Lesson).getText();
                    text.add(lesson_text);
                }

                Intent intent = new Intent(getActivity(),GrammarListActivity.class);
                intent.putStringArrayListExtra("pages",text);
                TextView textView = (TextView)view.findViewById(R.id.concept);
                intent.putExtra("title",textView.getText().toString());
                startActivity(intent);

            }
        });
        return view;
    }
}

class GrammarAdapter extends BaseAdapter {
    Grammar grammar;
    LayoutInflater inflater;
    int layoutResourceId;
    public GrammarAdapter(Grammar grammar, LayoutInflater inflater,int layoutResourceId) {
        this.inflater = inflater;
        this.layoutResourceId = layoutResourceId;
        this.grammar = grammar;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = inflater.inflate(layoutResourceId,parent,false);
        }
        TextView term = (TextView) convertView.findViewById(R.id.concept);
        term.setText(grammar.getName(position));
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
        return grammar.getConcepts().size();
    }
}