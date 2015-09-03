/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2014 Goulwen Le Fur
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
package osgi.jee.samples.jpa.tests.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ImageViewer extends JFrame {

	private static final long serialVersionUID = -6220346142888234794L;

	public ImageViewer(final Image image) throws HeadlessException {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(30, 30, 300, 300);
		this.getContentPane().add(new JComponent() {
			private static final long serialVersionUID = -2246826707817458058L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(image, 10, 10, this);
				g2.finalize();
			}

		});
	}

	
	
}
