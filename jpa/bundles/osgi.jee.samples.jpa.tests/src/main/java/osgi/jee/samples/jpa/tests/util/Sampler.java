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
import java.util.Calendar;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.BigProject;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.Gender;
import osgi.jee.samples.jpa.model.Period;
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

	public static final String HENRI_MENARD_FIRSTNAME = "Henri";
	public static final String HENRI_MENARD_LASTNAME = "Ménard";
	public static final String HENRI_MENARD_START_DATE = "17/04/2004";
	public static final String CORINNE_PARIZEAU_LASTNAME = "Parizeau";
	public static final String FRANCE = "France";
	public static final String PHONE_TYPE_PRO = "Pro";


	/**
	 * France phone number area code.
	 */
	public static final String FRANCE_AREA_CODE = "33";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static void generateEmploymentSample(DataConnection dataConnection) {
		dataConnection.beginTransaction();

		try {
			//Model definition
			EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);

			// Employee 1
			Employee henriMenard = createHenriMenard(employmentFactory);
			persistEmployee(dataConnection, henriMenard);
			
			// Employee 2
			Employee corinneParizeau = createCorinneParizeau(employmentFactory, henriMenard);
			persistEmployee(dataConnection, corinneParizeau);

			// Employee 3
			Employee valentineGagne = createValentineGagne(employmentFactory, henriMenard);
			persistEmployee(dataConnection, valentineGagne);
						
			// Employee 4
			Employee didierCourcelle = createDidierCourcelle(employmentFactory, corinneParizeau);
			persistEmployee(dataConnection, didierCourcelle);

			// Employee 5
			Employee josetteDuval = createJosetteDuval(employmentFactory, corinneParizeau);
			persistEmployee(dataConnection, josetteDuval);
						
			// Employee 6
			Employee honoreDubeau = createHonoreDubeau(employmentFactory, corinneParizeau);
			persistEmployee(dataConnection, honoreDubeau);
			
			// Employee 7
			Employee tillyLapointe = createTillyLapointe(employmentFactory, corinneParizeau);
			persistEmployee(dataConnection, tillyLapointe);
						
			// Employee 8
			Employee robertPhaneuf = createRobertPhaneuf(employmentFactory);
			persistEmployee(dataConnection, robertPhaneuf);
						
			// Employee 9
			Employee jacquesAllard = createJacquesAllard(employmentFactory, josetteDuval);
			persistEmployee(dataConnection, jacquesAllard);
			
			// Employee 10
			Employee brigitteBoisclair = createBrigitteBoisclair(employmentFactory, valentineGagne);
			persistEmployee(dataConnection, brigitteBoisclair);
			
			// Employee 11
			Employee nicolasDionne = createNicolasDionne(employmentFactory, brigitteBoisclair);
			persistEmployee(dataConnection, nicolasDionne);
			
			// Employee 12
			Employee timotheeBeaulac = createThimoteeBeaulac(employmentFactory);
			persistEmployee(dataConnection, timotheeBeaulac);
			
			// Employee 13
			Employee francoisePetit = createFrancoisePetit(employmentFactory, brigitteBoisclair);
			persistEmployee(dataConnection, francoisePetit);
						
			// Employee 14
			Employee mathildeJette = createMathildeJette(employmentFactory, valentineGagne);
			persistEmployee(dataConnection, mathildeJette);
			
			ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
			
			// Helios project
			BigProject helios = employmentFactory.createBigProject();
			helios.setName("Helios");
			helios.setBudget(new BigDecimal(1500000));
			helios.setTeamLeader(corinneParizeau);
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

	/**
	 * @param employmentFactory
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param postalCode
	 * @param country
	 * @param phoneNumber
	 * @param type
	 * @param areaCode
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static Employee createEmployee(EmploymentFactory employmentFactory, String firstName, String lastName, Gender gender, Calendar birthDate, String street, String city, String postalCode, String country, String phoneNumber, String type, String areaCode, String startDate, String endDate) throws ParseException {
		Employee employee = employmentFactory.createEmployee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setGender(gender);
		employee.setBirthDate(birthDate);

		Address address = employmentFactory.createAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setPostalCode(postalCode);
		address.setCountry(country);
		employee.setAddress(address);
		
		Phone phone = employmentFactory.createPhone();
		phone.setNumber(phoneNumber);
		phone.setType(type);
		phone.setAreaCode(areaCode);
		employee.addPhone(phone);
		
		Period employmentPeriod = employmentFactory.createPeriod();
		employmentPeriod.setStartDate(dateFormat.parse(startDate));
		if (endDate != null) {
			employmentPeriod.setEndDate(dateFormat.parse(endDate));			
		}
		employee.setEmploymentPeriod(employmentPeriod);
		return employee;
	}
	
	/**
	 * @param employmentFactory
	 * @param valentineGagne
	 * @return
	 * @throws ParseException
	 */
	public static Employee createMathildeJette(EmploymentFactory employmentFactory, Employee valentineGagne) throws ParseException {
		Employee mathildeJette = createEmployee(employmentFactory, "Mathilde", "Jetté", Gender.FEMALE,createBirthDate(22, 11, 1966), "42 rue Michel Ange", "Le Havre", "76610", FRANCE, "02.70.91.02.97", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "24/08/2012", null);
		mathildeJette.setManager(valentineGagne);
		return mathildeJette;
	}

	/**
	 * @param employmentFactory
	 * @param brigitteBoisclair
	 * @return
	 * @throws ParseException
	 */
	public static Employee createFrancoisePetit(EmploymentFactory employmentFactory, Employee brigitteBoisclair) throws ParseException {
		Employee francoisePetit = createEmployee(employmentFactory, "Françoise", "Petit", Gender.FEMALE, createBirthDate(27, 7, 1973), "40 rue Gouin de Beauchesne", "Saint-Paul", "97460", FRANCE, "02.93.09.72.46", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "13/02/2012", null);
		francoisePetit.setManager(brigitteBoisclair);
		return francoisePetit;
	}

	/**
	 * @param employmentFactory
	 * @return
	 * @throws ParseException
	 */
	public static Employee createThimoteeBeaulac(EmploymentFactory employmentFactory) throws ParseException {
		Employee timotheeBeaulac = createEmployee(employmentFactory, "Timothée", "Beaulac", Gender.MALE, createBirthDate(3, 3, 1983), "67 Rue Joseph Vernet", "Baie-Mahault", "97122", FRANCE, "05.78.93.91.48", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "17/03/2007", "30/04/2013");
		return timotheeBeaulac;
	}

	/**
	 * @param employmentFactory
	 * @param brigitteBoisclair
	 * @return
	 * @throws ParseException
	 */
	public static Employee createNicolasDionne(EmploymentFactory employmentFactory, Employee brigitteBoisclair) throws ParseException {
		Employee nicolasDionne = createEmployee(employmentFactory, "Nicolas", "Dionne", Gender.MALE, createBirthDate(13, 11, 1969), "21 rue des Coudriers", "Mont-Saint-Aignan", "76130", FRANCE, "02.28.30.99.71", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "23/01/2010", null);
		nicolasDionne.setManager(brigitteBoisclair);
		return nicolasDionne;
	}

	/**
	 * @param employmentFactory
	 * @param valentineGagne
	 * @return
	 * @throws ParseException
	 */
	public static Employee createBrigitteBoisclair(EmploymentFactory employmentFactory, Employee valentineGagne) throws ParseException {
		Employee brigitteBoisclair = createEmployee(employmentFactory, "Brigitte", "Boisclair", Gender.FEMALE, createBirthDate(15, 12, 1976), "10 rue des six frères Ruellan", "Saint-Sebastien-Sur-Loire", "44230", FRANCE, "02.73.54.13.82", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "22/09/2008", null);
		brigitteBoisclair.setManager(valentineGagne);
		return brigitteBoisclair;
	}

	/**
	 * @param employmentFactory
	 * @param josetteDuval
	 * @return
	 * @throws ParseException
	 */
	public static Employee createJacquesAllard(EmploymentFactory employmentFactory, Employee josetteDuval) throws ParseException {
		Employee jacquesAllard = createEmployee(employmentFactory, "Jacques", "Allard", Gender.MALE, createBirthDate(14, 2, 1973), "79 rue du Président Roosevelt", "Savigny-Le-Temple", "77176", FRANCE, "01.62.71.65.41", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "23/06/2013", null);
		jacquesAllard.setManager(josetteDuval);
		return jacquesAllard;
	}

	/**
	 * @param employmentFactory
	 * @return
	 * @throws ParseException
	 */
	public static Employee createRobertPhaneuf(EmploymentFactory employmentFactory) throws ParseException {
		Employee robertPhaneuf = createEmployee(employmentFactory, "Robert", "Phaneuf", Gender.MALE, createBirthDate(1, 2, 1958), "51 rue de la Hulotais", "Saint-Pol-Sur-Mer", "59430", 	FRANCE, "03.31.17.66.86", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "30/03/2009", "17/04/2011");
		return robertPhaneuf;
	}

	/**
	 * @param employmentFactory
	 * @param corinneParizeau
	 * @return
	 * @throws ParseException
	 */
	public static Employee createTillyLapointe(EmploymentFactory employmentFactory, Employee corinneParizeau) throws ParseException {
		Employee tillyLapointe = createEmployee(employmentFactory, "Tilly", "Lapointe", Gender.MALE, createBirthDate(18, 10, 1966), "5 avenue de Provence", "Vannes", "56000", FRANCE, "02.46.59.15.46", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "25/02/2007", null);
		tillyLapointe.setManager(corinneParizeau);
		return tillyLapointe;
	}

	/**
	 * @param employmentFactory
	 * @param corinneParizeau
	 * @return
	 * @throws ParseException
	 */
	public static Employee createHonoreDubeau(EmploymentFactory employmentFactory, Employee corinneParizeau) throws ParseException {
		Employee honoreDubeau = createEmployee(employmentFactory, "Honore", "Dubeau", Gender.MALE, createBirthDate(12, 12, 1978), "79 rue Banaudon", "Lyon", "69009", FRANCE, "04.42.93.38.71", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "23/07/2010", null);
		honoreDubeau.setManager(corinneParizeau);
		return honoreDubeau;
	}

	/**
	 * @param employmentFactory
	 * @param corinneParizeau
	 * @return
	 * @throws ParseException
	 */
	public static Employee createJosetteDuval(EmploymentFactory employmentFactory, Employee corinneParizeau) throws ParseException {
		Employee josetteDuval = createEmployee(employmentFactory, "Josette", "Duval", Gender.FEMALE, createBirthDate(14, 10, 1965), "40 rue Saint Germain", "Gap", "05000", FRANCE, "04.53.53.82.77", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "17/04/2006", null);
		josetteDuval.setManager(corinneParizeau);
		return josetteDuval;
	}

	/**
	 * @param employmentFactory
	 * @param corinneParizea
	 * @return
	 * @throws ParseException
	 */
	public static Employee createDidierCourcelle(EmploymentFactory employmentFactory, Employee corinneParizea) throws ParseException {
		Employee didierCourcelle = createEmployee(employmentFactory, "Didier", "Courcelle", Gender.MALE, createBirthDate(22, 9, 1965), "30 boulevard de la Liberation", "Marseille", "13011", FRANCE, "04.40.74.81.90", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "1/03/2009", null);
		didierCourcelle.setManager(corinneParizea);
		return didierCourcelle;
	}

	/**
	 * @param employmentFactory
	 * @param henriMenard
	 * @return
	 * @throws ParseException
	 */
	public static Employee createValentineGagne(EmploymentFactory employmentFactory, Employee henriMenard) throws ParseException {
		Employee valentineGagne = createEmployee(employmentFactory, "Valentine", "Gagné", Gender.FEMALE, createBirthDate(23, 07, 1973), "11 rue St Ferréol", "Metz", "57070", FRANCE, "03.00.88.10.59", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "30/09/2006", null);
		valentineGagne.setManager(henriMenard);
		return valentineGagne;
	}

	/**
	 * @param employmentFactory
	 * @param henriMenard
	 * @return
	 * @throws ParseException
	 */
	public static Employee createCorinneParizeau(EmploymentFactory employmentFactory, Employee henriMenard) throws ParseException {
		Employee corinneParizeau = createEmployee(employmentFactory, "Corinne", CORINNE_PARIZEAU_LASTNAME, Gender.FEMALE, createBirthDate(18, 9, 1981), "42 place Stanislas", "Nantes", "44100", FRANCE, "02.72.74.22.59", PHONE_TYPE_PRO, FRANCE_AREA_CODE, "25/11/2007", null);
		corinneParizeau.setManager(henriMenard);
		return corinneParizeau;
	}

	/**
	 * @param employmentFactory
	 * @return
	 * @throws ParseException
	 */
	public static Employee createHenriMenard(EmploymentFactory employmentFactory) throws ParseException {
		Employee henriMenard = createEmployee(employmentFactory, HENRI_MENARD_FIRSTNAME, HENRI_MENARD_LASTNAME, Gender.MALE, createBirthDate(26, 9, 1961), "11 rue Sebastopol", "Saintes", "17100", FRANCE, "05.39.37.63.09", PHONE_TYPE_PRO, FRANCE_AREA_CODE, HENRI_MENARD_START_DATE, null);
		return henriMenard;
	}

	public static void persistEmployee(DataConnection dataConnection, Employee employee) {
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		AddressDAO addressDAO = TestsActivator.getInstance().getService(AddressDAO.class);
		addressDAO.create(dataConnection, employee.getAddress());
		PhoneDAO phoneDAO = TestsActivator.getInstance().getService(PhoneDAO.class);
		for (Phone phone : employee.getPhones()) {				
			phoneDAO.create(dataConnection, phone);
		}
		employeeDAO.create(dataConnection, employee);
	}

	private static Calendar createBirthDate(int date, int month, int year) {
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(year, month, date);
		return birthDate;
	}
}
