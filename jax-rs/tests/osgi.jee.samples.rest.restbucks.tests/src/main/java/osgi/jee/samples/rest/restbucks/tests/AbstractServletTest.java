/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2016 Goulwen Le Fur
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package osgi.jee.samples.rest.restbucks.tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@SuppressWarnings("restriction")
public class AbstractServletTest extends AbstractIntegrationTest {

	private HttpClient client;

	/**
	 * @return
	 */
	protected HttpClient getClient() {
		if (client == null) {
			client = HttpClientBuilder.create().build();
		}
		return client;
	}
	
	/**
	 * Send an object in a XML format via a post method.
	 * @param path the destination path
	 * @param contents the object to pass to the server.
	 * @return the response of this operation.
	 * @throws Exception an error occurred.
	 */
	protected HttpResponse postPOX(String path, Object contents) throws Exception {
		HttpPost post = new HttpPost(BASE_URL + path);
		post.setEntity(new StringEntity(getXMLUtil().toXML(contents)));
		return getClient().execute(post);
	}

	/**
	 * Send an object in a XML format via a put method.
	 * @param path the destination path
	 * @param contents the object to pass to the server.
	 * @return the response of this operation.
	 * @throws Exception an error occurred.
	 */
	protected HttpResponse putPOX(String path, Object contents) throws Exception {
		HttpPut put = new HttpPut(BASE_URL + path);
		StringEntity entity = new StringEntity(getXMLUtil().toXML(contents));
		entity.setContentType("application/xml");
		put.setEntity(entity);
		return getClient().execute(put);
	}

	
}
