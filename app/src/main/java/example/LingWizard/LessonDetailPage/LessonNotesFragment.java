package example.LingWizard.LessonDetailPage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.LingWizard.R;
import example.LingWizard.XML.LessonPlan;
import example.LingWizard.XML.Section;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonNotesFragment extends Fragment {
    private static final String language = "lesson_language";
    private static final String unit = "lesson_unit";
    private static final String lesson = "lesson_lesson";

    private String lesson_language;
    private String lesson_unit;
    private String lesson_lesson;

    public LessonNotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LessonNotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonNotesFragment newInstance() {
        LessonNotesFragment fragment = new LessonNotesFragment();
        /* Bundle args = new Bundle();
        args.putString(LessonNotesFragment.language, language);
        args.putString(LessonNotesFragment.unit, unit);
        args.putString(LessonNotesFragment.lesson,lesson);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* if (getArguments() != null) {
        lesson_language = getArguments().getString(language);
        lesson_unit = getArguments().getString(unit);
        lesson_lesson = getArguments().getString(lesson);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_lesson_notes, container, false);
        LinearLayout l = (LinearLayout)v.findViewById(R.id.really);
        LessonPlan plan = null;
        if(getActivity() instanceof LessonDetailActivity){
            plan = ((LessonDetailActivity) getActivity()).getLessonPlan();
        }

        final ArrayList<String> notes = new ArrayList<>();
        final ArrayList<String> images = new ArrayList<>();
        for(Section s: plan.getSections()){
            notes.addAll(s.getNotes());
        }

        for(int i=0;i<notes.size();i++) {
            FrameLayout card = (FrameLayout) inflater.inflate(R.layout.lesson_note, container, false);
            ((TextView)card.findViewById(R.id.textView)).setText(notes.get(i));
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(((TextView)v.findViewById(R.id.textView)).getText());
                    Intent intent = new Intent(getActivity(),LessonNotesActivity.class);
                    intent.putStringArrayListExtra("notes",notes);
                    intent.putStringArrayListExtra("images",images);
                    String text = ((TextView)v.findViewById(R.id.textView)).getText().toString();
                    intent.putExtra("start",notes.indexOf(text));
                    startActivity(intent);
                }
            });
            l.addView(card);
        }
        return v;
    }
}
