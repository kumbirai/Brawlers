package com.kumbirai.golf.backend;

import java.io.Serializable;
import java.util.Collection;

import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.samples.backend.data.Category;
import com.kumbirai.golf.samples.backend.data.Product;
import com.kumbirai.security.principal.ISecurityPrincipal;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * <br>
	 * get<br>
	 * <br>
	 * @return<br>
	 */
	public static DataService get()
	{
		return GolfDataService.getInstance();
	}

	/**
	 * Purpose:
	 * <br>
	 * getAllProducts<br>
	 * <br>
	 * @return<br>
	 */
	public abstract Collection<Product> getAllProducts();

	/**
	 * Purpose:
	 * <br>
	 * getAllCategories<br>
	 * <br>
	 * @return<br>
	 */
	public abstract Collection<Category> getAllCategories();

	/**
	 * Purpose:
	 * <br>
	 * updateProduct<br>
	 * <br>
	 * @param p<br>
	 */
	public abstract void updateProduct(Product p);

	/**
	 * Purpose:
	 * <br>
	 * deleteProduct<br>
	 * <br>
	 * @param productId<br>
	 */
	public abstract void deleteProduct(int productId);

	/**
	 * Purpose:
	 * <br>
	 * getProductById<br>
	 * <br>
	 * @param productId
	 * @return<br>
	 */
	public abstract Product getProductById(int productId);

	/**
	 * Purpose:
	 * <br>
	 * login<br>
	 * <br>
	 * @param username
	 * @param password
	 * @return<br>
	 */
	public abstract PersonInfoLoginProfile login(String username, String password);

	/**
	 * Purpose:
	 * <br>
	 * getAllEvents<br>
	 * <br>
	 * @return<br>
	 */
	public abstract Collection<GolfEvent> getAllEvents();

	/**
	 * Purpose:
	 * <br>
	 * getPerson<br>
	 * <br>
	 * @param securityPrincipal
	 * @param entityNo
	 * @return<br>
	 */
	public abstract Person getPerson(ISecurityPrincipal securityPrincipal, long entityNo);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param person<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, Person person);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param personInfoEmail<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, PersonInfoEmail personInfoEmail);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param personInfoGolfDetails<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, PersonInfoGolfDetails personInfoGolfDetails);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param personInfoTel<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, PersonInfoTel personInfoTel);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param personInfoWebAddress<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, PersonInfoWebAddress personInfoWebAddress);

	/**
	 * Purpose:
	 * <br>
	 * getGolfEventByNo<br>
	 * <br>
	 * @param securityPrincipal
	 * @param eventNo
	 * @return<br>
	 */
	public abstract GolfEvent getGolfEventByNo(ISecurityPrincipal securityPrincipal, Long eventNo);

	/**
	 * Purpose:
	 * <br>
	 * deleteGolfEvent<br>
	 * <br>
	 * @param securityPrincipal
	 * @param eventNo<br>
	 */
	public abstract void deleteGolfEvent(ISecurityPrincipal securityPrincipal, GolfEvent golfEvent);

	/**
	 * Purpose:
	 * <br>
	 * getAllCourses<br>
	 * <br>
	 * @return<br>
	 */
	public abstract Collection<GolfCourse> getAllCourses();

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param golfEvent<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, GolfEvent golfEvent);

	/**
	 * Purpose:
	 * <br>
	 * save<br>
	 * <br>
	 * @param securityPrincipal
	 * @param golfCourse<br>
	 */
	public abstract void save(ISecurityPrincipal securityPrincipal, GolfCourse golfCourse);

	/**
	 * Purpose:
	 * <br>
	 * getGolfCourseByNo<br>
	 * <br>
	 * @param securityPrincipal
	 * @param golfCourseNo
	 * @return<br>
	 */
	public abstract GolfCourse getGolfCourseByNo(ISecurityPrincipal securityPrincipal, Long golfCourseNo);

	/**
	 * Purpose:
	 * <br>
	 * deleteGolfCourse<br>
	 * <br>
	 * @param securityPrincipal
	 * @param golfCourse<br>
	 */
	public abstract void deleteGolfCourse(ISecurityPrincipal securityPrincipal, GolfCourse golfCourse);
}
