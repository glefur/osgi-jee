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
package server.tests.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import server.internal.model.users.UserPersisted;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class TestService {

    private EntityManagerFactory entityManagerFactory;


    /**
     * @param entityManagerFactory
     */
    public TestService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        test();
    }

    public void test() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("Select u from UserPersisted u");
        List<UserPersisted> resultList = query.getResultList();
        for (UserPersisted userPersisted : resultList) {
            System.out.println("user: " + userPersisted.getLogin());
        }
        UserPersisted user = new UserPersisted();
        user.setLogin("Login" + resultList.size());
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("User created");
        em.close();
        System.out.println("Manager closed");
    }

}
