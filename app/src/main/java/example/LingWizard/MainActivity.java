package example.LingWizard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> languages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        languages.add("Korean");
        languages.add("French");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view,languages);
        list.setAdapter(adapter);

        if(findViewById(R.id.fragment_container)!= null) {
            if(savedInstanceState!=null) {
                return;
            }
            createLanguageBlocks(languages);
        }
    }

    public void createLanguageBlocks(ArrayList<String> languages){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for(String lang:languages) {
            transaction.add(R.id.fragment_container, MenuFragment.newInstance(lang+"/logo.jpg",lang));
        }
        transaction.commit();
    }
}
