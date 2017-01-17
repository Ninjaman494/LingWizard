package example.LingWizard.LessonDetailPage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import example.LingWizard.CustomViewPager;
import example.LingWizard.LessonActivity;
import example.LingWizard.LessonFragment;
import example.LingWizard.R;

public class GrammarListActivity extends AppCompatActivity {
    ArrayList<String> pages;
    ArrayList<Fragment> fragments;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.titleText)).setVisibility(View.GONE);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        pages = intent.getStringArrayListExtra("pages");
        fragments = new ArrayList<>();
        for(String s:pages){
            fragments.add(LessonFragment.newInstance(s,null));
        }

        System.out.println(title);
        getSupportActionBar().setTitle(title);
        CustomViewPager viewPager = (CustomViewPager)findViewById(R.id.lesson_container);
        viewPager.setAdapter(new LessonActivity.ScreenSlidePagerAdapter(getSupportFragmentManager(),fragments));

    }
}
