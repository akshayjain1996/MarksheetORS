package com.example.marksheetors;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.dto.UserDTO;
import com.example.model.UserModel;
import com.example.parser.XMLParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegActivity extends Activity {

	EditText uName = null;
	EditText password = null;
	EditText name = null;
	String root = "DATA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_reg);
		uName = (EditText) findViewById(R.id.regUname);
		password = (EditText) findViewById(R.id.regPass);
		name = (EditText) findViewById(R.id.regName);

		Button save = (Button) findViewById(R.id.opSave);
		Button list = (Button) findViewById(R.id.regList);

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (android.os.Build.VERSION.SDK_INT > 9) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				XMLParser parser = new XMLParser();

				String uVal = uName.getText().toString();
				String pVal = password.getText().toString();
				String nVal = name.getText().toString();

				String url = "http://localhost:8080/MarksheetORSWeb/UserCtl?name="
						+ nVal
						+ "&username="
						+ uVal
						+ "&password="
						+ pVal
						+ "&operation=Save";

				String xml = parser.getXmlFromUrl(url);

				Document doc = parser.getDomElement(xml);

				NodeList nodeList = doc.getElementsByTagName(root);

				Node message = nodeList.item(0);

				String mesgVal = message.getTextContent();

				Toast.makeText(UserRegActivity.this, mesgVal, Toast.LENGTH_LONG)
						.show();
			}
		});

		list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UserRegActivity.this,
						MarksheetListActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_reg, menu);
		return true;
	}

}
