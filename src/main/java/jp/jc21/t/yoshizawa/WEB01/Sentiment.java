package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment{

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Sentimental message = getLanguage("子どもたちがいなかったので、 鳥たちは歌いたいと思いませんでしたし、 木々は花を咲かせるのを忘れておりました。");
		if (message != null) {
			System.out.println("negative：" + message.documents[0].confidenceScores.negative);
			System.out.println("newtral" +message.documents[0].confidenceScores.newtral);
			System.out.println("positive" +message.documents[0].confidenceScores.positive);
		}
	}

	static Sentimental getLanguage(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3b01-test.cognitiveservices.azure.com/" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "392e2313ec87488990042ebddbc46633");

		Docs doc = new Docs();
		doc.id = "1";
		doc.text = s;

		Source src = new Source();
		src.documents = new Docs[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		Sentimental message = null;
		if (reader != null) {
			message = gson.fromJson(reader,Sentimental.class);
			reader.close();
		}
		return message;
	}

}

class Sentimental{
	Documents[] documents;
	String[] errors;
	String modelVersion;
}

class Documents{
	confidenceScores confidenceScores;
	String id;
}

class confidenceScores {
	float negative;
	float newtral;
	float positive;
}

class Source {
	Docs[] documents;
}

class Docs {
	String id;
	String text;
}