package com.example.gtnavigate;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JSONUseActivity {
 EditText addTag;		// to add tag for buildings
 Button submit;
 TextView tv;  		// textview to show the result of MySQL query
 
 String returnString; 			// to store the result of MYSQL query after decoding JSON
 
 	public void onCreate(Bundle savedInstanceState){
 		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
 		.detectDiskReads().detectDiskWrites()
 		.detectNetwork().penaltyLog().build());	// strictMode is most commonly used to catch accidental disk or network
 		// access on the application's main thread
 		
 		addTag = (EditText) findViewById(R.id.editText1);
 		submit = (Button) findViewById(R.id.subButton);
 		tv = (TextView) findViewById(R.id.showRes);
 		
 		
 		// define the action listener when submit button is clicked
 		submit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// declare parameters that are passed to php script, i.e the tag name "coc" value submitted by user
				ArrayList<NameValuePair> postPara = new ArrayList<NameValuePair>();
				
				//define the para
				postPara.add(new BasicNameValuePair("place", addTag.getText().toString()));
				String response = null;
				
				// call myHttppost method  to pass the param
				try{
					response = CustomHttpClient.executeHttpPost("  ", postPara);
					
					
					String result = response.toString();
					
					// parse json data
					try{
						returnString = "";
						JSONArray myJArray = new JSONArray(result);
							for(int i = 0; i < myJArray.length(); i ++){
								JSONObject j_data = myJArray.getJSONObject(i);
								Log.i("log_tag", "id: "+j_data.getInt("id")+
										", place: "+j_data.getString("place")+
										", tag: "+j_data.getString("tag")
								);
								// output
								returnString +="\n" +j_data.getString("place") + " -> " + j_data.getString("tag");
								
							}
					}
					catch(JSONException e ){
						Log.e("log_tag", "Error when parsing data "+e.toString());
					}
					
					try{
						tv.setText(returnString);
					}
					catch(Exception e){
						Log.e("log_tag","Error when display" + e.toString());
					}
				}
					catch(Exception e){
						Log.e("log_tag","Error when http connection" + e.toString());
					}
				
			}
		});
 		
 	}
}
