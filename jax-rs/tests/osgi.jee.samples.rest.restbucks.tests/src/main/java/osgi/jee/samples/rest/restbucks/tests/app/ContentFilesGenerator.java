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
package osgi.jee.samples.rest.restbucks.tests.app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

import osgi.jee.samples.rest.restbucks.tests.ContentFileDescription;
import osgi.jee.samples.rest.restbucks.tests.TestRequestingContentFile;
import osgi.jee.samples.rest.restbucks.tests.integration.crud.OrderServiceAsCRUDTest;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ContentFilesGenerator {

	public void generate(Path rootFolder, Class<? extends TestRequestingContentFile>... classes) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException, IOException  {
		for (Class<? extends TestRequestingContentFile> testClass : classes) {
			TestRequestingContentFile test = testClass.newInstance();
			Collection<ContentFileDescription> contentFileDescriptions = test.getContentFileDescriptions();
			for (ContentFileDescription contentFileDescription : contentFileDescriptions) {
				Path absolutePath = Paths.get(rootFolder.toString(), contentFileDescription.path);
				if (!Files.exists(absolutePath.getParent())) {
					Files.createDirectories(absolutePath.getParent());
				}
				Files.write(absolutePath, contentFileDescription.content.getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			String string = args[0];
			Path rootFolder = Paths.get(string);
			new ContentFilesGenerator().generate(rootFolder, OrderServiceAsCRUDTest.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
