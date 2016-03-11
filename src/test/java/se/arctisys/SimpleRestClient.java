package se.arctisys;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class SimpleRestClient {

	private final String baseURL;

	private final WebTarget baseResource;

	private static final MediaType responseType = MediaType.APPLICATION_JSON_TYPE;

	private final String keyFile;

	public SimpleRestClient(String nExtBaseURL, String keyFilePath) {
		baseURL = nExtBaseURL;
		keyFile = keyFilePath;
		baseResource = ClientBuilder.newClient().target(baseURL);
	}

	private String encryptAuthParameter(String user, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		// construct the base for the auth parameter

		String login = DatatypeConverter.printBase64Binary(user.getBytes()) + ":"
				+ DatatypeConverter.printBase64Binary(password.getBytes()) + ":"
				+ DatatypeConverter.printBase64Binary(String.valueOf(System.currentTimeMillis()).getBytes());

		// RSA encrypt it using the nExt public key
		PublicKey pubKey = getKeyFromPEM(keyFile);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);

		byte[] encryptedBytes = cipher.doFinal(login.getBytes("UTF-8"));

		// Convert it to plain ASCII with base64
		String authParam = DatatypeConverter.printBase64Binary(encryptedBytes);

		// URL encode to get rid of uncomfortable characters
		return URLEncoder.encode(authParam, "UTF-8");
	}

	public String login(String user, String password)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, IOException {

		String authParam = encryptAuthParameter(user, password);

		// Fetch the resource.
		Response response = baseResource.path("login").queryParam("service", "NEXTAPI").queryParam("auth", authParam)
				.request(responseType).post(null);

		ObjectNode json = response.readEntity(ObjectNode.class);

		String sessionKey = json.get("session_key").asText();

		// add the session key to basic auth for all calls
		baseResource.register(HttpAuthenticationFeature.basic(sessionKey, sessionKey));

		return sessionKey;
	}

	public void getAccounts() {
		String resp = baseResource.path("accounts").request(responseType).get(String.class);
		System.out.println(resp);
	}

	public void getAccountSummary(int accno) {
		String resp = baseResource.path("accounts").path(String.valueOf(accno)).request(responseType).get(String.class);
		System.out.println(resp);
	}

	public void getAccountPositions(int accno) {
		String resp = baseResource.path("accounts").path(String.valueOf(accno)).path("positions").request(responseType)
				.get(String.class);
		System.out.println(resp);
	}

	public void logout(String sessionKey) {
		String resp = baseResource.path("login").path(sessionKey).request(responseType).delete(String.class);
		System.out.println(resp);
	}

	private static PublicKey getKeyFromPEM(String filename)
			throws NoSuchAlgorithmException, InvalidKeySpecException, FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = null;
			String key = "";
			while (true) {
				line = reader.readLine();
				if (line == null)
					break;
				else if (line.startsWith("-----BEGIN PUBLIC KEY-----"))
					continue;
				else if (line.startsWith("-----END PUBLIC KEY-----"))
					continue;
				else
					key += line.trim();
			}
			byte[] binary = DatatypeConverter.parseBase64Binary(key);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(binary);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePublic(spec);
		}
	}

	public void trade() throws Exception {

		// https://api.test.nordnet.se/next/1/accounts/9211428/orders?identifier=3966&marketID=11&price=149.7000&side=BUY&volume=11&currency=SEK&order_type=NORMAL&smart_order=false
		String urlParameters = "identifier=3966&marketID=11&price=149.7000&side=BUY&volume=11&currency=SEK&order_type=NORMAL&smart_order=false";
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		String request = "https://api.test.nordnet.se/next/1/accounts/9211428/orders";
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);

		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			wr.write(postData);
		}
	}

	public void trade2(String sessionKey) throws Exception {

		//https://api.test.nordnet.se/next/2/accounts/9210681/orders?identifier=101&market_id=11&price=100.0000&side=BUY&volume=1&currency=SEK&order_type=NORMAL&reference=1423730280799
		URL url = new URL("https://api.test.nordnet.se/next/2/accounts/9211428/orders");
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("identifier", "101");
		params.put("marketID", "11");
		params.put("price", "149.7000");
		params.put("side", "BUY");
		params.put("volume", "11");
		params.put("currency", "SEK");
		params.put("order_type", "NORMAL");
		params.put("smart_order", "false");

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		String urlParameters = postData.toString();
		URLConnection conn = url.openConnection();

		conn.setDoOutput(true);
		
		conn.setRequestProperty  ("Authorization", "Basic " + sessionKey);
		conn.setRequestProperty  ("Content-Type", "application/x-www-form-urlencoded");

		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		writer.write(urlParameters);
		writer.flush();

		String result = "";
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		while ((line = reader.readLine()) != null) {
			result += line;
		}
		writer.close();
		reader.close();
		System.out.println(result);

	}

	public void trade3(String sessionKey) throws Exception {
		{
		    String httpsURL = "https://api.test.nordnet.se/next/2/accounts/9211428/orders?identifier=3966&market_id=11&price=100.0000&side=BUY&volume=1&currency=SEK&order_type=NORMAL";
		    URL myurl = new URL(httpsURL);
		    
		    HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		    con.setRequestProperty  ("Authorization", "Basic " + sessionKey);
		    //con.setRequestProperty  ("Content-Type", "application/json");
		    con.setRequestProperty  ("Content-Type", "application/x-www-form-urlencoded");
		    //application/x-www-form-urlencoded
		    InputStream ins = con.getInputStream();
		    
		    InputStreamReader isr = new InputStreamReader(ins);
		    BufferedReader in = new BufferedReader(isr);
		 
		    String inputLine;
		 
		    while ((inputLine = in.readLine()) != null)
		    {
		      System.out.println(inputLine);
		    }		 
		    in.close();
		  }
	}
	
	public static void main(String[] args) throws Exception {
		SimpleRestClient client = new SimpleRestClient("https://api.test.nordnet.se/next/2",
				"C:/Projekt/arctisys/filedirs/config/NEXTAPI_TEST_public.pem");
		String sessionKey = client.login("tornqvistb@gmail.com", "stensture");
		System.out.println(sessionKey);
		client.getAccounts();
		// client.getAccountPositions(Integer.valueOf("ACCOUNT ID"));
		client.getAccountPositions(Integer.valueOf("9211428"));
		// client.getAccountSummary(Integer.valueOf("ACCOUNT ID"));
		client.getAccountSummary(Integer.valueOf("9211428"));
		client.trade3(sessionKey);
		client.logout(sessionKey);
	}
}
