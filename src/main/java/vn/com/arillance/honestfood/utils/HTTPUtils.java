package vn.com.arillance.honestfood.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class HTTPUtils {
	/*
	 * "data": 
	{ "title": "aaaa", 
      "content": "bbbssdsdbbb", 
       "dataFrom" : "1" },
  "registration_ids" : ["eNPG6gVwWU8:APA91bHzklrzMiDpnip0k6TEqGRLoMxPtRRlQ_SSfLlGWkwkRVJISg30SccIluW-A6sFXz8aXuzpM5JmI_nLdkeumoUOZdlXMa_r3XEzZWdl44gwgJ1jMnrUKzCfV7hYjIO7IZJDywUS"],
	 */
	public static final int NEW_ORDER = 1;
	public static final int NEW_ORDER_CONFIRM = 2;
	public static final int WEEKLY_SCHEDULE = 3;
	private static final String USER_AGENT = "Mozilla/5.0";
//	public static final String NOTI_MESSAGE = "{ \"notification\":{\"title\" : \"%s\", \"body\":\"%s\"}, \"data\": { \"title\": \"%s\", \"content\": \"%s\", \"dataFrom\" : \"%s\" }, \"to\" : \"%s\"}";
	public static final String NOTI_MESSAGE = "{ \"notification\":{\"title\" : \"%s\", \"body\":\"%s\", \"icon\" : \"app_icon\", \"sound\" : \"default\"}, \"to\" : \"%s\"}";

	// HTTP GET request
	public static String checkAccessToken(String param) throws Exception {

		String url = String.format(Constants.API.ACCESS_TOKEN, param);

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			JSONObject json = new JSONObject(response.toString());
			System.out.println(json.get("id"));
			return json.getString("id");
		}
		System.out.println("Invalid Access Token");
		return "";
	}

	public static void pushNotficationToAllDevices(String rootToken, String title, String content, int dataFrom) {
		if (rootToken != null && !rootToken.trim().equals("")) {
			String[] tokens = rootToken.split(",");
			System.out.println("Noti Token : " + rootToken);
			for (String token : tokens) {
				System.out.println("Token : " + token);
				HTTPUtils.pushNotification(title, content, token, dataFrom);				
			}
		}
	}

	public static void pushNotification(String title, String content, String notiToken, int dataFrom) {
		try {
//			String body = "{\"notification\": {\"title\": \"%s\", \"body\": \"%s\"}, \"to\" : \"%s\"}";
			String body = NOTI_MESSAGE;
			body = String.format(body, title, content, notiToken);			
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			// DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");
			StringEntity input = new StringEntity(body, "UTF-8");
			input.setContentType("application/json");			
			postRequest.setHeader("Authorization", "key= AIzaSyD-zCGPl40bHg7s-UumuyeFzLEtlz16rHM");
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(input);
			
			System.out.println("Request : " + body);

			for (Header header : postRequest.getAllHeaders()) {
				System.out.println("Header : " + header.toString());
			}
			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println(response.toString());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.close();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	// HTTP POST request
	// private void sendPost() throws Exception {
	//
	// String url = "https://selfsolve.apple.com/wcResults.do";
	// URL obj = new URL(url);
	// HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	//
	// //add reuqest header
	// con.setRequestMethod("POST");
	// con.setRequestProperty("User-Agent", USER_AGENT);
	// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	//
	// String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	//
	// // Send post request
	// con.setDoOutput(true);
	// DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	// wr.writeBytes(urlParameters);
	// wr.flush();
	// wr.close();
	//
	// int responseCode = con.getResponseCode();
	// System.out.println("\nSending 'POST' request to URL : " + url);
	// System.out.println("Post parameters : " + urlParameters);
	// System.out.println("Response Code : " + responseCode);
	//
	// BufferedReader in = new BufferedReader(
	// new InputStreamReader(con.getInputStream()));
	// String inputLine;
	// StringBuffer response = new StringBuffer();
	//
	// while ((inputLine = in.readLine()) != null) {
	// response.append(inputLine);
	// }
	// in.close();
	//
	// //print result
	// System.out.println(response.toString());
	//
	// }

}
