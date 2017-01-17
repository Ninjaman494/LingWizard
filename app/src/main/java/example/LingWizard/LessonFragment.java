package example.LingWizard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonFragment extends Fragment {
    private static final String text = "lesson_text";
    private static final String image = "lesson_image";

    private String lesson_text;
    private String lesson_image;

    public LessonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param text Parameter 1.
     * @param image Parameter 2.
     * @return A new instance of fragment LessonFragment.
     */
    public static LessonFragment newInstance(String text, String image) {
        LessonFragment fragment = new LessonFragment();
        Bundle args = new Bundle();
        args.putString(LessonFragment.text, text);
        args.putString(LessonFragment.image, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lesson_text = getArguments().getString(text);
            lesson_image = getArguments().getString(image);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        ((TextView)view.findViewById(R.id.text)).setText(lesson_text);
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

    public String getText(){
        return getArguments().getString(text);
    }
}
