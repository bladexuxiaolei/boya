package com.example.boya;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class Getdown {
	private Getdown down=null;
	private static Getdown getdown = new Getdown();
	private List<String> toptitle = new ArrayList<String>();
	private List<String> topHref = new ArrayList<String>();
	private List<String> topsummary = new ArrayList<String>();
	private ArrayList<String> topdata = new ArrayList<String>();
	String TAG = "boya";
	synchronized public static Getdown getInstance()
	{
		
		return getdown;
	}
	public void refresh(String titletag)
	{
		if(titletag.equals("topten"))
		{
			//Log.i(TAG,"refresh");
			Httpdowntop();
		}
		else 
		{
			
		}
	}
	public void Httpdowntop()
	{
		
		new Thread(){  
            @Override   
            public void run() {    
	            	try {      		
	            		//Log.i(TAG,"httpdowntopthread");
	            		URL url = new URL("http://1.hboyamuseum.sinaapp.com/?cat=1"); 
	            		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	            		HttpURLConnection httpConnection = (HttpURLConnection)httpConn;
	                  
	            		InputStreamReader input = new InputStreamReader(httpConn
	            				.getInputStream(), "UTF-8");
	            		 
	            		BufferedReader bufReader = new BufferedReader(input);
	            		
	            		String line = ""; 
	            		StringBuilder contentBuf = new StringBuilder();
	            		while ((line = bufReader.readLine()) != null) {
	            			contentBuf.append(line);
	            		}
	            		Document doc = Jsoup.parse(contentBuf.toString());
	            		Element topsite=doc.getElementById("content");
	            		//Log.i(TAG,topsite.toString());
	            		Elements links=topsite.getElementsByTag("a");
	            		Elements summarys=topsite.getElementsByTag("p");
	            		for(Element summary:summarys)
	            		{
	            			topsummary.add(summary.text());
	            			Log.i(TAG,summary.text());
	            		}
	            		for (int i=0;i<links.size();i++) {
	            			
	            			topHref.add(links.get(i).attr("href"));
	            			toptitle.add(links.get(i).text());
	            			//topsummary.add(links.get(i+2).text());
	            			Log.i(TAG,toptitle.get(i)+"**");
	            			}
	            		//put the data into the mainActivity
	            		for(int i=0;i<topsummary.size();i++)
	            		{
	            			topdata.add(toptitle.get(i));
	            			topdata.add(toptitle.get(i+1));
	            			topdata.add(toptitle.get(i+2));
	            			topdata.add(topHref.get(i));
	            			topdata.add(topsummary.get(i));
	            		}
	            		Bundle bundle = new Bundle();
	            		bundle.putStringArrayList("topdata", topdata);
	            		Message msg = new Message();
	            		msg.what=0x123;
	            		msg.setData(bundle);
	            		Log.i(TAG,links.size()+"");
						bufReader.close();
						input.close();
						httpConn.disconnect();
					
				} catch (Exception e) {
					
					Log.i(TAG,e.toString());
				}
            }  
        }.start(); 
	}
}
