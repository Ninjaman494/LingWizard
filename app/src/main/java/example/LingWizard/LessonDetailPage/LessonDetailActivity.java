package example.LingWizard.LessonDetailPage;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.util.ArrayList;

import example.LingWizard.R;
import example.LingWizard.XML.LessonPlan;

public class LessonDetailActivity extends AppCompatActivity {
    String language;
    String unit;
    String lesson;

    ArrayList<String> notes;
    ArrayList<String> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        ViewPager viewPager = (ViewPager)findViewById(R.id.tabContent);
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        language = intent.getStringExtra("LANGUAGE_SELECTION");
        unit = intent.getStringExtra("UNIT_SELECTION");
        lesson = intent.getStringExtra("LESSON_SELECTION");


    }
    public LessonPlan getLessonPlan() {
        try {
            Serializer serializer = new Persister();
            InputStream ims = getAssets().open(language + "/" + unit + "/" + lesson + "/lesson_plan.xml");
            LessonPlan plan = serializer.read(LessonPlan.class, ims);
            return plan;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {
        private String tabTitles[] = new String[] { "Lesson Notes", "Grammar", "Vocab" };
        GrammarListFragment grammarListFragment = GrammarListFragment.newInstance();
        VocabListFragment vocabListFragment = VocabListFragment.newInstance();
        LessonNotesFragment lessonNotesFragment = LessonNotesFragment.newInstance();

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return lessonNotesFragment;
            }
            if(position==1) {
                return grammarListFragment;
            }
            else{
                return vocabListFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}

