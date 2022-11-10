package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Json05 {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Language message = getLanguage("らーめんたべたい");
		if (message != null) {
			System.out.println(message.documents02[0].detectedLanguage.name);
		}
	}

	static Language getLanguage(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3b01-test.cognitiveservices.azure.com/"
				+"text/analytics/v3.0/languages";
		Map<String,String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key","392e2313ec87488990042ebddbc46633");
		
		Docs02 doc=new Docs02();
		doc.id="1";
		doc.text=s;

		Source02 src = new Source02();
		src.documents=new Docs02[1];
		src.documents[0]=doc;
		
		String jsonData = new Gson().toJson(src);
		
		//InetSocketAddress proxy =new InetSocketAddress("172.17.0.2", 80);

		//JsonReader reader = WebApiConnector.postJsonReader(url,proxy,map,jsonData);
		JsonReader reader = WebApiConnector.postJsonReader(url,map,jsonData);
		Language message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Language.class);
			reader.close();
		}
		return message;
	}

}
class Language{
	Documents02[] documents02;
	String[] errors;
	String modelVersion;
}
class Documents02{	
	DetectedLanguage detectedLanguage;
}
class DetectedLanguage{
	String name;
}
class Source02{
	Docs02[] documents;
}

class Docs02{
      String id;
      String text;
}
