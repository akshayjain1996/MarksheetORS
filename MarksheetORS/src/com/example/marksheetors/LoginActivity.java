package com.example.marksheetors;

import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

import com.example.parser.XMLParser;

public class LoginActivity extends Activity {

	EditText uName = null;
	EditText pass = null;
	String root = "DATA";
	ResourceBundle bundle = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		uName = (EditText) findViewById(R.id.loginUsername);
		pass = (EditText) findViewById(R.id.loginPassword);
		Button login = (Button) findViewById(R.id.loginLogBut);
		Button reg = (Button) findViewById(R.id.opReg);
		bundle = ResourceBundle.getBundle("com.example.bundle.system");

		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (android.os.Build.VERSION.SDK_INT > 9) {
						StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
								.permitAll().build();
						StrictMode.setThreadPolicy(policy);
					}
					XMLParser parser = new XMLParser();

					String uVal = uName.getText().toString();
					String pVal = pass.getText().toString();
					System.out.println("Values  " + uVal + " " + pVal);
					
					String url = bundle.getString("URL") + "/UserCtl?username="
							+ uVal + "&password=" + pVal + "&operation=Login";
					System.out.println("URL " + url);

					String xml = parser.getXmlFromUrl(url);

					Document doc = parser.getDomElement(xml);

					NodeList nodeList = doc.getElementsByTagName(root);

					Node message = nodeList.item(0);

					String msegVal = message.getTextContent();

					if (msegVal.equals("Valid")) {
						Intent intent = new Intent(LoginActivity.this,
								MarksheetActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(LoginActivity.this,
								"Invalid Username or Password",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		reg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						UserRegActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}