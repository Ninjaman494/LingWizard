package example.LingWizard;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.util.ArrayList;

import example.LingWizard.XML.LessonPlan;
import example.LingWizard.XML.Section;

public class LessonActivity extends AppCompatActivity implements CustomViewPager.OnSwipeOutListener {
    String language;
    String unit;
    String lesson;

    ArrayList<Section> sectionList;
    int lessonLength;
    Section currentSection;
    int currentSectionInt;
    int currentComponentInt;
    CustomViewPager lessonContainer;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Intent intent = getIntent();
        language = intent.getStringExtra("LANGUAGE_SELECTION");
        unit = intent.getStringExtra("UNIT_SELECTION");
        lesson = intent.getStringExtra("LESSON_SELECTION");
        currentSectionInt = 0;
        currentComponentInt = 0;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        LessonPlan plan = getLessonPlan();
        getSupportActionBar().setTitle(plan.getHeaderText());
        sectionList = plan.getSections();
        currentSection = sectionList.get(currentSectionInt);
        lessonContainer = (CustomViewPager) findViewById(R.id.lesson_container);
        setFragments();
    }

    public void onSwipeOutEnd(){
        progressBar.setProgress(100);
        currentSection = getNextSection();
        if(currentSection == null){
            System.out.println("All Done!");
        }
        else {
            setFragments();
        }
    }

    public Section getNextSection() {
        currentSectionInt++;
        if (currentSectionInt >= sectionList.size()) {
            return null;
        }
        return sectionList.get(currentSectionInt);
    }

    public void setFragments(){
        ((TextView) findViewById(R.id.titleText)).setText(currentSection.getTitleText());
        ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),currentSection.generateFragments());
        lessonContainer.setAdapter(mPagerAdapter);
        lessonContainer.setOnSwipeOutListener(this);
        lessonContainer.addOnPageChangeListener(new CustomOnPageChangeListener(mPagerAdapter.getCount()));
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

    private class CustomOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener{
        int length;
        public CustomOnPageChangeListener(int length){
            this.length = length;
        }

        float tempPositionOffset = 0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            double progress = 100 * ((double)position/(double)length);
            progressBar.setProgress((int)progress);
            //System.out.println(position);
            if (tempPositionOffset < positionOffset) {
                //System.out.println("Did you scroll right?");
            } else {
                //System.out.println("Did you scroll left?");
            }
            tempPositionOffset = positionOffset;
        }

    }

    public static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> fragments;
        public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
