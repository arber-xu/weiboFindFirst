package weibo4j;

import java.util.List;

import weibo4j.http.HttpClient;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Weibo implements java.io.Serializable {

	private static final long serialVersionUID = 4282616848978535016L;

	public HttpClient client = new HttpClient();

	public  void setToken(String token) {
		client.setToken(token);
	}
//	public StatusWapper getPublicTimeline() throws WeiboException {
//		return Status.constructWapperStatus(client.get(WeiboConfig
//				.getValue("baseURL") + "statuses/public_timeline.json?count=200"));
//	}

}