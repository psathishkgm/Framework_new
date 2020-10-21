package com.framework.utilities;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Parameters;

public class HTTPRequest {

	@Parameters ({"url"})
	public static void testStatusCode(String url) throws Exception {
	
	// Create Object and pass the url
	HttpUriRequest request = new HttpGet(url);
	HttpResponse response = HttpClientBuilder.create().build().execute(request);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

}
