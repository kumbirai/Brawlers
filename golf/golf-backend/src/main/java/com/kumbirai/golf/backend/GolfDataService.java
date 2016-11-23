package com.kumbirai.golf.backend;

import java.util.Collection;
import java.util.List;

import com.kumbirai.golf.data.course.GolfCourse;
import com.kumbirai.golf.data.entity.Person;
import com.kumbirai.golf.data.entity.info.PersonInfoEmail;
import com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails;
import com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile;
import com.kumbirai.golf.data.entity.info.PersonInfoTel;
import com.kumbirai.golf.data.entity.info.PersonInfoWebAddress;
import com.kumbirai.golf.data.event.GolfEvent;
import com.kumbirai.golf.facade.course.GolfCourseFacade;
import com.kumbirai.golf.facade.course.HoleInfoFacade;
import com.kumbirai.golf.facade.entity.PersonFacade;
import com.kumbirai.golf.facade.entity.info.PersonInfoEmailFacade;
import com.kumbirai.golf.facade.entity.info.PersonInfoGolfDetailsFacade;
import com.kumbirai.golf.facade.entity.info.PersonInfoLoginProfileFacade;
import com.kumbirai.golf.facade.entity.info.PersonInfoTelFacade;
import com.kumbirai.golf.facade.entity.info.PersonInfoWebAddressFacade;
import com.kumbirai.golf.facade.event.GolfEventFacade;
import com.kumbirai.golf.facade.event.GolfEventResultFacade;
import com.kumbirai.golf.facade.score.MatchFacade;
import com.kumbirai.golf.facade.score.ScoreCardFacade;
import com.kumbirai.golf.facade.score.ScoreFacade;
import com.kumbirai.golf.facade.security.SecurityRoleFacade;
import com.kumbirai.golf.samples.backend.data.Category;
import com.kumbirai.golf.samples.backend.data.Product;
import com.kumbirai.security.principal.ISecurityPrincipal;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class GolfDataService extends DataService
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//
	private static GolfDataService instance;
	//
	private List<Product> products;
	private List<Category> categories;
	private int nextProductId = 0;
	//
	private GolfCourseFacade golfCourseFacade = new GolfCourseFacade();
	private HoleInfoFacade holeInfoFacade = new HoleInfoFacade();
	private PersonFacade personFacade = new PersonFacade();
	private PersonInfoEmailFacade personInfoEmailFacade = new PersonInfoEmailFacade();
	private PersonInfoGolfDetailsFacade personInfoGolfDetailsFacade = new PersonInfoGolfDetailsFacade();
	private PersonInfoLoginProfileFacade personInfoLoginProfileFacade = new PersonInfoLoginProfileFacade();
	private PersonInfoTelFacade personInfoTelFacade = new PersonInfoTelFacade();
	private PersonInfoWebAddressFacade personInfoWebAddressFacade = new PersonInfoWebAddressFacade();
	private PersonInfoLoginProfileFacade personInfoLoginProfile = new PersonInfoLoginProfileFacade();
	private GolfEventFacade golfEventFacade = new GolfEventFacade();
	private GolfEventResultFacade golfEventResultFacade = new GolfEventResultFacade();
	private MatchFacade matchFacade = new MatchFacade();
	private ScoreCardFacade scoreCardFacade = new ScoreCardFacade();
	private ScoreFacade scoreFacade = new ScoreFacade();
	private SecurityRoleFacade securityRoleFacade = new SecurityRoleFacade();

	/**
	 * Constructor:
	 */
	private GolfDataService()
	{
		categories = MockDataGenerator.createCategories();
		products = MockDataGenerator.createProducts(categories);
		nextProductId = products.size() + 1;
	}

	/**
	 * Purpose:
	 * <br>
	 * getInstance<br>
	 * <br>
	 * @return<br>
	 */
	public static synchronized DataService getInstance()
	{
		if (instance == null)
		{
			instance = new GolfDataService();
		}
		return instance;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getAllProducts()
	 */
	@Override
	public synchronized List<Product> getAllProducts()
	{
		return products;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getAllCategories()
	 */
	@Override
	public synchronized List<Category> getAllCategories()
	{
		return categories;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#updateProduct(com.kumbirai.golf.samples.backend.data.Product)
	 */
	@Override
	public synchronized void updateProduct(Product p)
	{
		if (p.getId() < 0)
		{
			// New product
			p.setId(nextProductId++);
			products.add(p);
			return;
		}
		for (int i = 0; i < products.size(); i++)
		{
			if (products.get(i).getId() == p.getId())
			{
				products.set(i, p);
				return;
			}
		}

		throw new IllegalArgumentException("No product with id " + p.getId() + " found");
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getProductById(int)
	 */
	@Override
	public synchronized Product getProductById(int productId)
	{
		for (int i = 0; i < products.size(); i++)
		{
			if (products.get(i).getId() == productId)
			{
				return products.get(i);
			}
		}
		return null;
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#deleteProduct(int)
	 */
	@Override
	public synchronized void deleteProduct(int productId)
	{
		Product p = getProductById(productId);
		if (p == null)
		{
			throw new IllegalArgumentException("Product with id " + productId + " not found");
		}
		products.remove(p);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public PersonInfoLoginProfile login(String username, String password)
	{
		return personInfoLoginProfileFacade.login(username, password);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getAllEvents()
	 */
	@Override
	public Collection<GolfEvent> getAllEvents()
	{
		return golfEventFacade.listAll();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getPerson(long)
	 */
	@Override
	public Person getPerson(ISecurityPrincipal securityPrincipal, long entityNo)
	{
		personFacade.setSecurityPrincipal(securityPrincipal);
		return personFacade.find(entityNo);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.golf.data.entity.Person)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, Person person)
	{
		personFacade.setSecurityPrincipal(securityPrincipal);
		personFacade.save(person);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.golf.data.entity.info.PersonInfoEmail)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, PersonInfoEmail personInfoEmail)
	{
		personInfoEmailFacade.setSecurityPrincipal(securityPrincipal);
		personInfoEmailFacade.save(personInfoEmail);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.golf.data.entity.info.PersonInfoGolfDetails)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, PersonInfoGolfDetails personInfoGolfDetails)
	{
		personInfoGolfDetailsFacade.setSecurityPrincipal(securityPrincipal);
		personInfoGolfDetailsFacade.save(personInfoGolfDetails);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.golf.data.entity.info.PersonInfoTel)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, PersonInfoTel personInfoTel)
	{
		personInfoTelFacade.setSecurityPrincipal(securityPrincipal);
		personInfoTelFacade.save(personInfoTel);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.golf.data.entity.info.PersonInfoWebAddress)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, PersonInfoWebAddress personInfoWebAddress)
	{
		personInfoWebAddressFacade.setSecurityPrincipal(securityPrincipal);
		personInfoWebAddressFacade.save(personInfoWebAddress);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getGolfEventByNo(com.kumbirai.security.principal.ISecurityPrincipal, java.lang.Long)
	 */
	@Override
	public GolfEvent getGolfEventByNo(ISecurityPrincipal securityPrincipal, Long eventNo)
	{
		golfEventFacade.setSecurityPrincipal(securityPrincipal);
		return golfEventFacade.find(eventNo);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#delete(com.kumbirai.security.principal.ISecurityPrincipal, com.kumbirai.golf.data.event.GolfEvent)
	 */
	@Override
	public void delete(ISecurityPrincipal securityPrincipal, GolfEvent golfEvent)
	{
		golfEventFacade.setSecurityPrincipal(securityPrincipal);
		golfEventFacade.delete(golfEvent);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getAllCourses()
	 */
	@Override
	public Collection<GolfCourse> getAllCourses()
	{
		return golfCourseFacade.listAll();
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.security.principal.ISecurityPrincipal, com.kumbirai.golf.data.event.GolfEvent)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, GolfEvent golfEvent)
	{
		golfEventFacade.setSecurityPrincipal(securityPrincipal);
		golfEventFacade.save(golfEvent);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.security.principal.ISecurityPrincipal, com.kumbirai.golf.data.course.GolfCourse)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, GolfCourse golfCourse)
	{
		golfCourseFacade.setSecurityPrincipal(securityPrincipal);
		golfCourseFacade.save(golfCourse);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#getGolfCourseByNo(com.kumbirai.security.principal.ISecurityPrincipal, java.lang.Long)
	 */
	@Override
	public GolfCourse getGolfCourseByNo(ISecurityPrincipal securityPrincipal, Long golfCourseNo)
	{
		golfCourseFacade.setSecurityPrincipal(securityPrincipal);
		return golfCourseFacade.find(golfCourseNo);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#delete(com.kumbirai.security.principal.ISecurityPrincipal, com.kumbirai.golf.data.course.GolfCourse)
	 */
	@Override
	public void delete(ISecurityPrincipal securityPrincipal, GolfCourse golfCourse)
	{
		golfCourseFacade.setSecurityPrincipal(securityPrincipal);
		golfCourseFacade.delete(golfCourse);
	}

	/** (non-Javadoc)
	 * @see com.kumbirai.golf.backend.DataService#save(com.kumbirai.security.principal.ISecurityPrincipal, com.kumbirai.golf.data.entity.info.PersonInfoLoginProfile)
	 */
	@Override
	public void save(ISecurityPrincipal securityPrincipal, PersonInfoLoginProfile personInfoLoginProfile)
	{
		personInfoLoginProfileFacade.setSecurityPrincipal(securityPrincipal);
		personInfoLoginProfileFacade.save(personInfoLoginProfile);
	}
}