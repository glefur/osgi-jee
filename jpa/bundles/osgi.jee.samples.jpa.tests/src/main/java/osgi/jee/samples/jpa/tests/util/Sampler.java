/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2015 Goulwen Le Fur
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.BigProject;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.EmploymentPeriod;
import osgi.jee.samples.jpa.model.Phone;
import osgi.jee.samples.jpa.model.SmallProject;
import osgi.jee.samples.jpa.tests.TestsActivator;
import osgi.jee.samples.model.dao.AddressDAO;
import osgi.jee.samples.model.dao.EmployeeDAO;
import osgi.jee.samples.model.dao.PhoneDAO;
import osgi.jee.samples.model.dao.ProjectDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Sampler {
	
	public static void generateEmploymentSample(DataConnection dataConnection) {
		// Util
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataConnection.beginTransaction();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		AddressDAO addressDAO = TestsActivator.getInstance().getService(AddressDAO.class);
		PhoneDAO phoneDAO = TestsActivator.getInstance().getService(PhoneDAO.class);
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);

		try {
			//Model definition
			EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);

			// Employee 1
			Employee henriMenard = employmentFactory.createEmployee();
			henriMenard.setFirstName("Henri");
			henriMenard.setLastName("Ménard");

			Address hmAddr = employmentFactory.createAddress();
			hmAddr.setStreet("11 rue Sebastopol");
			hmAddr.setCity("Saintes");
			hmAddr.setPostalCode("17100");
			hmAddr.setCountry("France");
			henriMenard.setAddress(hmAddr);
			addressDAO.create(dataConnection, hmAddr);

			Phone hmPhone = employmentFactory.createPhone();
			hmPhone.setNumber("05.39.37.63.09");
			hmPhone.setType("Pro");
			hmPhone.setAreaCode("33");
			henriMenard.addPhone(hmPhone);
			phoneDAO.create(dataConnection, hmPhone);

			EmploymentPeriod hmEP = employmentFactory.createEmploymentPeriod();
			hmEP.setStartDate(dateFormat.parse("17/04/2004"));
			henriMenard.setEmploymentPeriod(hmEP);
			employeeDAO.create(dataConnection, henriMenard);
			
			// Employee 2
			Employee corrineParizeau = employmentFactory.createEmployee();
			corrineParizeau.setFirstName("Corinne");
			corrineParizeau.setLastName("Parizeau");

			Address cpAddr = employmentFactory.createAddress();
			cpAddr.setStreet("42 place Stanislas");
			cpAddr.setCity("Nantes");
			cpAddr.setPostalCode("44100");
			cpAddr.setCountry("France");
			corrineParizeau.setAddress(cpAddr);
			addressDAO.create(dataConnection, cpAddr);

			Phone cpPhone = employmentFactory.createPhone();
			cpPhone.setNumber("02.72.74.22.59");
			cpPhone.setType("Pro");
			cpPhone.setAreaCode("33");
			corrineParizeau.addPhone(cpPhone);
			phoneDAO.create(dataConnection, cpPhone);

			EmploymentPeriod cpEP = employmentFactory.createEmploymentPeriod();
			cpEP.setStartDate(dateFormat.parse("25/11/2007"));
			corrineParizeau.setEmploymentPeriod(cpEP);
			corrineParizeau.setManager(henriMenard);
			employeeDAO.create(dataConnection, corrineParizeau);
						
			// Employee 3
			Employee valentineGagne = employmentFactory.createEmployee();
			valentineGagne.setFirstName("Valentine");
			valentineGagne.setLastName("Gagné");

			Address vgAddr = employmentFactory.createAddress();
			vgAddr.setStreet("11 rue St Ferréol");
			vgAddr.setCity("Metz");
			vgAddr.setPostalCode("57070");
			vgAddr.setCountry("France");
			valentineGagne.setAddress(vgAddr);
			addressDAO.create(dataConnection, vgAddr);

			Phone vgPhone = employmentFactory.createPhone();
			vgPhone.setNumber("03.00.88.10.59");
			vgPhone.setType("Pro");
			vgPhone.setAreaCode("33");
			valentineGagne.addPhone(vgPhone);
			phoneDAO.create(dataConnection, vgPhone);

			EmploymentPeriod vgEP = employmentFactory.createEmploymentPeriod();
			vgEP.setStartDate(dateFormat.parse("30/09/2006"));
			valentineGagne.setEmploymentPeriod(vgEP);
			valentineGagne.setManager(henriMenard);
			employeeDAO.create(dataConnection, valentineGagne);
						
			// Employee 4
			Employee didierCourcelle = employmentFactory.createEmployee();
			didierCourcelle.setFirstName("Didier");
			didierCourcelle.setLastName("Courcelle");

			Address dcAddr = employmentFactory.createAddress();
			dcAddr.setStreet("30 boulevard de la Liberation");
			dcAddr.setCity("Marseille");
			dcAddr.setPostalCode("13011");
			dcAddr.setCountry("France");
			didierCourcelle.setAddress(dcAddr);
			addressDAO.create(dataConnection, dcAddr);

			Phone dcPhone = employmentFactory.createPhone();
			dcPhone.setNumber("04.40.74.81.90");
			dcPhone.setType("Pro");
			dcPhone.setAreaCode("33");
			didierCourcelle.addPhone(dcPhone);
			phoneDAO.create(dataConnection, dcPhone);

			EmploymentPeriod dcEP = employmentFactory.createEmploymentPeriod();
			dcEP.setStartDate(dateFormat.parse("1/03/2009"));
			didierCourcelle.setEmploymentPeriod(dcEP);
			didierCourcelle.setManager(corrineParizeau);
			employeeDAO.create(dataConnection, didierCourcelle);
						
			// Employee 5
			Employee josetteDuval = employmentFactory.createEmployee();
			josetteDuval.setFirstName("Josette");
			josetteDuval.setLastName("Duval");

			Address jdAddr = employmentFactory.createAddress();
			jdAddr.setStreet("40 rue Saint Germain");
			jdAddr.setCity("Gap");
			jdAddr.setPostalCode("05000");
			jdAddr.setCountry("France");
			josetteDuval.setAddress(jdAddr);
			addressDAO.create(dataConnection, jdAddr);

			Phone jdPhone = employmentFactory.createPhone();
			jdPhone.setNumber("04.53.53.82.77");
			jdPhone.setType("Pro");
			jdPhone.setAreaCode("33");
			josetteDuval.addPhone(jdPhone);
			phoneDAO.create(dataConnection, jdPhone);
			
			EmploymentPeriod jdEP = employmentFactory.createEmploymentPeriod();
			jdEP.setStartDate(dateFormat.parse("17/04/2006"));
			josetteDuval.setEmploymentPeriod(jdEP);
			josetteDuval.setManager(corrineParizeau);
			employeeDAO.create(dataConnection, josetteDuval);
						
			// Employee 6
			Employee honoreDubeau = employmentFactory.createEmployee();
			honoreDubeau.setFirstName("Honore");
			honoreDubeau.setLastName("Dubeau");

			Address hdAddr = employmentFactory.createAddress();
			hdAddr.setStreet("79 rue Banaudon");
			hdAddr.setCity("Lyon");
			hdAddr.setPostalCode("69009");
			hdAddr.setCountry("France");
			honoreDubeau.setAddress(hdAddr);
			addressDAO.create(dataConnection, hdAddr);

			Phone hdPhone = employmentFactory.createPhone();
			hdPhone.setNumber("04.42.93.38.71");
			hdPhone.setType("Pro");
			hdPhone.setAreaCode("33");
			honoreDubeau.addPhone(hdPhone);
			phoneDAO.create(dataConnection, hdPhone);

			EmploymentPeriod hdEP = employmentFactory.createEmploymentPeriod();
			hdEP.setStartDate(dateFormat.parse("23/07/2010"));
			honoreDubeau.setEmploymentPeriod(hdEP);
			honoreDubeau.setManager(corrineParizeau);
			employeeDAO.create(dataConnection, honoreDubeau);
						
			// Employee 7
			Employee tillyLapointe = employmentFactory.createEmployee();
			tillyLapointe.setFirstName("Tilly");
			tillyLapointe.setLastName("Lapointe");

			Address tlAddr = employmentFactory.createAddress();
			tlAddr.setStreet("5 avenue de Provence");
			tlAddr.setCity("Vannes");
			tlAddr.setPostalCode("56000");
			tlAddr.setCountry("France");
			tillyLapointe.setAddress(tlAddr);
			addressDAO.create(dataConnection, tlAddr);

			Phone tlPhone = employmentFactory.createPhone();
			tlPhone.setNumber("02.46.59.15.46");
			tlPhone.setType("Pro");
			tlPhone.setAreaCode("33");
			tillyLapointe.addPhone(tlPhone);
			phoneDAO.create(dataConnection, tlPhone);

			EmploymentPeriod tlEP = employmentFactory.createEmploymentPeriod();
			tlEP.setStartDate(dateFormat.parse("25/02/2007"));
			tillyLapointe.setEmploymentPeriod(tlEP);
			tillyLapointe.setManager(corrineParizeau);
			employeeDAO.create(dataConnection, tillyLapointe);
						
			// Employee 8
			Employee robertPhaneuf = employmentFactory.createEmployee();
			robertPhaneuf.setFirstName("Robert");
			robertPhaneuf.setLastName("Phaneuf");

			Address rpAddr = employmentFactory.createAddress();
			rpAddr.setStreet("51 rue de la Hulotais");
			rpAddr.setCity("Saint-Pol-Sur-Mer");
			rpAddr.setPostalCode("59430");
			rpAddr.setCountry("France");
			robertPhaneuf.setAddress(rpAddr);
			addressDAO.create(dataConnection, rpAddr);

			Phone rpPhone = employmentFactory.createPhone();
			rpPhone.setNumber("03.31.17.66.86");
			rpPhone.setType("Pro");
			rpPhone.setAreaCode("33");
			robertPhaneuf.addPhone(rpPhone);
			phoneDAO.create(dataConnection, rpPhone);

			EmploymentPeriod rpEP = employmentFactory.createEmploymentPeriod();
			rpEP.setStartDate(dateFormat.parse("30/03/2009"));
			rpEP.setEndDate(dateFormat.parse("17/04/2011"));
			robertPhaneuf.setEmploymentPeriod(rpEP);
			employeeDAO.create(dataConnection, robertPhaneuf);
						
			// Employee 9
			Employee jacquesAllard = employmentFactory.createEmployee();
			jacquesAllard.setFirstName("Jacques");
			jacquesAllard.setLastName("Allard");

			Address jaAddr = employmentFactory.createAddress();
			jaAddr.setStreet("79 rue du Président Roosevelt");
			jaAddr.setCity("Savigny-Le-Temple");
			jaAddr.setPostalCode("77176");
			jaAddr.setCountry("France");
			jacquesAllard.setAddress(jaAddr);
			addressDAO.create(dataConnection, jaAddr);

			Phone jaPhone = employmentFactory.createPhone();
			jaPhone.setNumber("01.62.71.65.41");
			jaPhone.setType("Pro");
			jaPhone.setAreaCode("33");
			jacquesAllard.addPhone(jaPhone);
			phoneDAO.create(dataConnection, jaPhone);

			EmploymentPeriod jaEP = employmentFactory.createEmploymentPeriod();
			jaEP.setStartDate(dateFormat.parse("23/06/2013"));
			jacquesAllard.setEmploymentPeriod(jaEP);
			jacquesAllard.setManager(josetteDuval);
			employeeDAO.create(dataConnection, jacquesAllard);
						
			// Employee 10
			Employee brigitteBoisclair = employmentFactory.createEmployee();
			brigitteBoisclair.setFirstName("Brigitte");
			brigitteBoisclair.setLastName("Boisclair");

			Address bbAddr = employmentFactory.createAddress();
			bbAddr.setStreet("10 rue des six frères Ruellan");
			bbAddr.setCity("Saint-Sebastien-Sur-Loire");
			bbAddr.setPostalCode("44230");
			bbAddr.setCountry("France");
			brigitteBoisclair.setAddress(bbAddr);
			addressDAO.create(dataConnection, bbAddr);

			Phone bbPhone = employmentFactory.createPhone();
			bbPhone.setNumber("02.73.54.13.82");
			bbPhone.setType("Pro");
			bbPhone.setAreaCode("33");
			brigitteBoisclair.addPhone(bbPhone);
			phoneDAO.create(dataConnection, bbPhone);

			EmploymentPeriod bbEP = employmentFactory.createEmploymentPeriod();
			bbEP.setStartDate(dateFormat.parse("22/09/2008"));
			brigitteBoisclair.setEmploymentPeriod(bbEP);
			brigitteBoisclair.setManager(valentineGagne);
			employeeDAO.create(dataConnection, brigitteBoisclair);
						
			// Employee 11
			Employee nicolasDionne = employmentFactory.createEmployee();
			nicolasDionne.setFirstName("Nicolas");
			nicolasDionne.setLastName("Dionne");

			Address ndAddr = employmentFactory.createAddress();
			ndAddr.setStreet("21 rue des Coudriers");
			ndAddr.setCity("Mont-Saint-Aignan");
			ndAddr.setPostalCode("76130");
			ndAddr.setCountry("France");
			nicolasDionne.setAddress(ndAddr);
			addressDAO.create(dataConnection, ndAddr);

			Phone ndPhone = employmentFactory.createPhone();
			ndPhone.setNumber("02.28.30.99.71");
			ndPhone.setType("Pro");
			ndPhone.setAreaCode("33");
			nicolasDionne.addPhone(ndPhone);
			phoneDAO.create(dataConnection, ndPhone);

			EmploymentPeriod ndEP = employmentFactory.createEmploymentPeriod();
			ndEP.setStartDate(dateFormat.parse("23/01/2010"));
			nicolasDionne.setEmploymentPeriod(ndEP);
			nicolasDionne.setManager(brigitteBoisclair);
			employeeDAO.create(dataConnection, nicolasDionne);
			
			// Employee 12
			Employee timotheeBeaulac = employmentFactory.createEmployee();
			timotheeBeaulac.setFirstName("Timothée");
			timotheeBeaulac.setLastName("Beaulac");

			Address tbAddr = employmentFactory.createAddress();
			tbAddr.setStreet("67 Rue Joseph Vernet");
			tbAddr.setCity("Baie-Mahault");
			tbAddr.setPostalCode("97122");
			tbAddr.setCountry("France");
			timotheeBeaulac.setAddress(tbAddr);
			addressDAO.create(dataConnection, tbAddr);

			Phone tbPhone = employmentFactory.createPhone();
			tbPhone.setNumber("05.78.93.91.48");
			tbPhone.setType("Pro");
			tbPhone.setAreaCode("33");
			timotheeBeaulac.addPhone(tbPhone);
			phoneDAO.create(dataConnection, tbPhone);

			EmploymentPeriod tbEP = employmentFactory.createEmploymentPeriod();
			tbEP.setStartDate(dateFormat.parse("17/03/2007"));
			tbEP.setEndDate(dateFormat.parse("30/04/2013"));
			timotheeBeaulac.setEmploymentPeriod(tbEP);
			employeeDAO.create(dataConnection, timotheeBeaulac);
						
			// Employee 13
			Employee francoisePetit = employmentFactory.createEmployee();
			francoisePetit.setFirstName("Françoise");
			francoisePetit.setLastName("Petit");

			Address fpAddr = employmentFactory.createAddress();
			fpAddr.setStreet("40 rue Gouin de Beauchesne");
			fpAddr.setCity("Saint-Paul");
			fpAddr.setPostalCode("97460");
			fpAddr.setCountry("France");
			francoisePetit.setAddress(fpAddr);
			addressDAO.create(dataConnection, fpAddr);

			Phone fpPhone = employmentFactory.createPhone();
			fpPhone.setNumber("02.93.09.72.46");
			fpPhone.setType("Pro");
			fpPhone.setAreaCode("33");
			francoisePetit.addPhone(fpPhone);
			phoneDAO.create(dataConnection, fpPhone);

			EmploymentPeriod fpEP = employmentFactory.createEmploymentPeriod();
			fpEP.setStartDate(dateFormat.parse("13/02/2012"));
			francoisePetit.setEmploymentPeriod(fpEP);
			francoisePetit.setManager(brigitteBoisclair);
			employeeDAO.create(dataConnection, francoisePetit);
						
			// Employee 14
			Employee mathildeJette = employmentFactory.createEmployee();
			mathildeJette.setFirstName("Mathilde");
			mathildeJette.setLastName("Jetté");

			Address mjAddr = employmentFactory.createAddress();
			mjAddr.setStreet("42 rue Michel Ange");
			mjAddr.setCity("Le Have");
			mjAddr.setPostalCode("76610");
			mjAddr.setCountry("France");
			mathildeJette.setAddress(mjAddr);
			addressDAO.create(dataConnection, mjAddr);

			Phone mjPhone = employmentFactory.createPhone();
			mjPhone.setNumber("02.70.91.02.97");
			mjPhone.setType("Pro");
			mjPhone.setAreaCode("33");
			mathildeJette.addPhone(mjPhone);
			phoneDAO.create(dataConnection, mjPhone);

			EmploymentPeriod mjEP = employmentFactory.createEmploymentPeriod();
			mjEP.setStartDate(dateFormat.parse("24/08/2012"));
			mathildeJette.setEmploymentPeriod(mjEP);
			mathildeJette.setManager(valentineGagne);
			employeeDAO.create(dataConnection, mathildeJette);
						
			// Helios project
			BigProject helios = employmentFactory.createBigProject();
			helios.setName("Helios");
			helios.setBudget(new BigDecimal(1500000));
			helios.setTeamLeader(corrineParizeau);
			didierCourcelle.addProject(helios);
			josetteDuval.addProject(helios);
			honoreDubeau.addProject(helios);
			tillyLapointe.addProject(helios);
			jacquesAllard.addProject(helios);
			projectDAO.create(dataConnection, helios);

			// Selene project
			SmallProject selene = employmentFactory.createSmallProject();
			selene.setName("Selene");
			selene.setTeamLeader(brigitteBoisclair);
			nicolasDionne.addProject(selene);
			francoisePetit.addProject(selene);
			projectDAO.create(dataConnection, selene);

			// Ge project
			SmallProject ge = employmentFactory.createSmallProject();
			ge.setName("Ge");
			ge.setTeamLeader(valentineGagne);
			mathildeJette.addProject(ge);
			projectDAO.create(dataConnection, ge);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		dataConnection.commit();
	}

}
