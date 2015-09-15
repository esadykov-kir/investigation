package ser.amc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Realm;
import com.ning.http.client.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ser
 * @since 09.07.2015 21:24
 */
public class TestAsUtil {
    private static final Logger logger = LoggerFactory.getLogger("util");

    private static final Realm REALM = new Realm.RealmBuilder().setScheme(Realm.AuthScheme.BASIC).setPassword("APSiakq8KfKkSXfisbeDnSYgUu").setPrincipal("esadykov").build();

    @Test
    public void searchByVersions() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder requestBuilder = asyncHttpClient.prepareGet("http://maven.i-novus.ru/api/search/gavc?v=1.0.58-SNAPSHOT");

        requestBuilder.setRealm(REALM);
        Future<Response> f = requestBuilder.execute();
        try {
            Response r = f.get();
            JsonParser parser = new JsonParser();
            JsonElement response = parser.parse(new InputStreamReader(r.getResponseBodyAsStream()));
            Iterator<JsonElement> results = response.getAsJsonObject().get("results").getAsJsonArray().iterator();
            Set<String> uris = new HashSet<>();

            while (results.hasNext()) {
                JsonElement element = results.next();
                String uri = element.getAsJsonObject().get("uri").getAsString();
                uri = uri.replace("/api/storage", "");
                uri = uri.substring(0, uri.lastIndexOf('/'));
                if (uris.add(uri)) {
                    logger.debug(uri);
                    Future<Response> deleteResponse = asyncHttpClient.prepareDelete(uri).setRealm(REALM).execute();
                    logger.debug(deleteResponse.get().getResponseBody());
                }

            }
            logger.info("Try to deleted paths: {}", uris.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tree() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder requestBuilder = asyncHttpClient.prepareGet("http://maven.i-novus.ru/api/storage/libs-release-local");

        requestBuilder.setRealm(REALM);
        Future<Response> f = requestBuilder.execute();
        try {
            Response r = f.get();
            JsonParser parser = new JsonParser();
            JsonElement response = parser.parse(new InputStreamReader(r.getResponseBodyAsStream()));

            Folder root = new Folder("libs-release-local");

            Iterator<JsonElement> children = response.getAsJsonObject().get("children").getAsJsonArray().iterator();
            while (children.hasNext()) {
                JsonObject item = children.next();

            }
            logger.info("root children: {}", response.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteItem() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {
            Future<Response> f = asyncHttpClient.prepareDelete("http://maven.i-novus.ru/libs-snapshot-local/price-md-mes/price-md-mes/1.0.52-SNAPSHOT").setRealm(REALM).execute();
            logger.debug(f.get().getResponseBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
