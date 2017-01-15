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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class UnitListActivity extends AppCompatActivity {
    String language;
    ArrayList<String> units;
    Document lesson_directory;
    Element chosen_unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);
        language = getIntent().getStringExtra("LANGUAGE_SELECTION");
        units = readDirectory();
        ListView listView = (ListView) findViewById(R.id.activity_unit_list);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_view, units));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                String unit = (String)parent.getItemAtPosition(position);
                try {
                    //Getting the chosen unit and converting it to an XML Element
                    XPathFactory xPathfactory = XPathFactory.newInstance();
                    XPath xpath = xPathfactory.newXPath();
                    String compile = "//Unit[@name='" + unit + "']";
                    XPathExpression expr = xpath.compile(compile);
                    Node node = (Node) expr.evaluate(lesson_directory, XPathConstants.NODE);
                    chosen_unit = (Element)node;
                    NodeList x = chosen_unit.getChildNodes();
                    ArrayList<String> lessons = new ArrayList();
                    for(int i=0;i<x.getLength();i++) {
                        Node child = x.item(i);
                        if(child instanceof Element) {
                            lessons.add(child.getTextContent());
                        }
                    }

                    //Sending intent
                    Intent intent = new Intent(UnitListActivity.this,LessonListActivity.class);
                    String unit_id = chosen_unit.getAttribute("id");
                    intent.putExtra("UNIT_SELECTION",unit_id);
                    intent.putExtra("LANGUAGE_SELECTION",language);
                    intent.putStringArrayListExtra("LESSONS",lessons);
                    startActivity(intent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public ArrayList<String> readDirectory() {
        ArrayList<String> units = new ArrayList<>();
        try {
            //Getting the XML File
            InputStream ims = getAssets().open(language + "/lesson_directory.xml");
            InputSource source = new InputSource(ims);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(source);
            doc.getDocumentElement().normalize();
            lesson_directory = doc;
            ims.close();

            //Parsing the File
            Element root = doc.getDocumentElement();
            NodeList cList = root.getChildNodes();
            for(int i=0;i<cList.getLength();i++) {
                if(cList.item(i) instanceof Element) {
                    Element n = (Element)cList.item(i);
                    units.add(n.getAttribute("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return units;
    }
}
