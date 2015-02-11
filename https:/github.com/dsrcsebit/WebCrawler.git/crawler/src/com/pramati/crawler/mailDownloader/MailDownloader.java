package com.pramati.crawler.mailDownloader;

import java.io.IOException;

public interface MailDownloader extends Runnable {

	boolean saveMailFromURL() throws IOException;
	
}
