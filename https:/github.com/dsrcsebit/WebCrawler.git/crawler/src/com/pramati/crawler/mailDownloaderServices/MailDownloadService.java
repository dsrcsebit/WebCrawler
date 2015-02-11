package com.pramati.crawler.mailDownloaderServices;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.pramati.crawler.mailDownloader.HTMLRequester;
import com.pramati.crawler.mailDownloader.MailDownloader;
import com.pramati.crawler.uti.Utility;

public class MailDownloadService implements MailDownloader {

	String URL;

	public MailDownloadService(String uRL) {
		super();
		URL = uRL;
	}

	@Override
	public boolean saveMailFromURL() throws IOException{
		HTMLRequester hreq = new HTMLRequestService();
		Set<String> mailURL = hreq.htmlURLExtractor(URL, "table#msglist", "");
		Iterator<String> it = mailURL.iterator();
		while(it.hasNext()){
		// System.out.println(mailURL.size() + "mailURLs are"+mailURL );

		String testURL = it.next().toString();
		if(testURL.indexOf("%")!=-1){
		testURL = testURL.substring(0, testURL.indexOf("%") - 1) + "/raw/"
				+ testURL.substring(testURL.indexOf("%"));
		Utility.writeToFile(hreq.linkParser(testURL),
				testURL.substring(testURL.indexOf("%")));
		}
		else{
			Utility.writeToFile(hreq.linkParser(testURL),
					String.valueOf(System.currentTimeMillis()));
		}
		//System.out.println(hreq.linkParser(testURL));
		}
		return false;
	}

	@Override
	public void run() {
		try {
			saveMailFromURL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
