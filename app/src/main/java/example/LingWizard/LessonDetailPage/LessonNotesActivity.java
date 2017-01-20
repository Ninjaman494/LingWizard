package example.LingWizard.LessonDetailPage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import example.LingWizard.CustomViewPager;
import example.LingWizard.LessonActivity;
import example.LingWizard.LessonFragment;
import example.LingWizard.R;

public class LessonNotesActivity extends AppCompatActivity {
    ArrayList<String> notes;
    ArrayList<String> images;
    CustomViewPager viewPager;
    int start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        findViewById(R.id.titleText).setVisibility(View.GONE);

        Intent intent = getIntent();
        notes = intent.getStringArrayListExtra("notes");
        images = intent.getStringArrayListExtra("images");
        start = intent.getIntExtra("start",0);
        viewPager = (CustomViewPager)findViewById(R.id.lesson_container);


        ArrayList<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<notes.size();i++){
            fragments.add(new LessonFragment().newInstance(notes.get(i),null));
        }
        viewPager.setAdapter(new LessonActivity.ScreenSlidePagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.setCurrentItem(start);

    }
}
