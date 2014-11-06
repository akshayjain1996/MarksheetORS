package com.example.marksheetors;

import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.parser.XMLParser;

public class MarksheetListActivity extends Activity {
	ListView view = null;
	ResourceBundle bundle = null;
	String root = "DATA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marksheet_list);
		bundle = ResourceBundle.getBundle("com.example.bundle.system");

		view = (ListView) findViewById(R.id.marksheetList);
		String[] val = null;

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		XMLParser parser = new XMLParser();

		String url = bundle.getString("URL") + "/MarksheetListCtl";

		String xml = parser.getXmlFromUrl(url);

		Document doc = parser.getDomElement(xml);

		NodeList elementList = doc.getElementsByTagName("ELEMENT");
		val = new String[elementList.getLength()];

		for (int i = 0; i < elementList.getLength(); i++) {
			Element element = (Element) elementList.item(i);
			val[i] = parser.getValue(element, "ROLLNO") + "\t"
					+ parser.getValue(element, "NAME") + "\t"
					+ parser.getValue(element, "PHYSICS") + "\t"
					+ parser.getValue(element, "CHEMISTRY") + "\t"
					+ parser.getValue(element, "MATHS");
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MarksheetListActivity.this,
				android.R.layout.simple_expandable_list_item_1, val);
		view.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.marksheet_list, menu);
		return true;
	}

}