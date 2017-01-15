package example.LingWizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import example.LingWizard.LessonDetailPage.LessonDetailActivity;

public class LessonListActivity extends AppCompatActivity {
    String language;
    String unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        unit = getIntent().getStringExtra("UNIT_SELECTION");
        language = getIntent().getStringExtra("LANGUAGE_SELECTION");

        ListView listView = (ListView) findViewById(R.id.activity_lesson_list);
        ArrayList<String> lessons = getIntent().getStringArrayListExtra("LESSONS");
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_view,lessons));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String chosen_lesson_tag = getID((String)parent.getItemAtPosition(position));
                Intent intent = new Intent(LessonListActivity.this,LessonDetailActivity.class);
                intent.putExtra("LANGUAGE_SELECTION",language);
                intent.putExtra("UNIT_SELECTION",unit);
                intent.putExtra("LESSON_SELECTION",chosen_lesson_tag);
                startActivity(intent);
            }
        });
    }

    public String getID(String chosen_lesson) {
        try {
            //Getting the XML File
            InputStream ims = getAssets().open(language + "/lesson_directory.xml");
            InputSource source = new InputSource(ims);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(source);
            doc.getDocumentElement().normalize();
            ims.close();

            //Getting the lesson node
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String compile = "//Unit[@id='" + unit + "']/lesson[text()='"+chosen_lesson+"']";
            XPathExpression expr = xpath.compile(compile);
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            return ((Element)node).getAttribute("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
