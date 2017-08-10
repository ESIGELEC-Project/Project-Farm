
import java.net.URL;
import java.util.List;

import model.*;
import model.db.CategoryDB;
import model.db.DBUtil;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class GeneralTest {

	@Before
	public void setup(){

	}

	@Test
	public void testDatabaseConnection(){
		Assert.assertEquals(true, DBUtil.checkConnection());
	}

	@Test
	public void testCategory() {
		Category c = new Category("Test");
		Assert.assertEquals("Test", c.getDescription());
	}

	@Test
	public void testProject() {
		Category c = new Category("Test");
		Owner o = new Owner("a@a", "ma", "12");
		try {
			new Project("AA", "BB", 10, 100, o, c);
			new Project("AA", "BB", 1, 1, o, c);
		} catch (InvalidDataException e) {
			Assert.fail("error in project");
		}

		try {
			new Project(null, "BB", 1, 1, o, c);
			Assert.fail("project allowed null acronym");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", null, 1, 1, o, c);
			Assert.fail("project allowed null description");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", -1, 1, o, c);
			Assert.fail("project allowed negative funding period");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, -1, o, c);
			Assert.fail("project allowed negative budget");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, 1, null, c);
			Assert.fail("project allowed null owner ");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, 1, o, null);
			Assert.fail("project allowed null category ");
		} catch (InvalidDataException e) {
		}

	}

	@Test
	public void testEvaluation() {

		Evaluator ev = new Evaluator("x@x", "x", "y");
		try {
			new Evaluation(null, 1, 1);
			Assert.fail("accepted null evaluator");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(ev, 0, 1);
			Assert.fail("accepted invalid attractiveness range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(ev, 6, 1);
			Assert.fail("accepted invalid attractiveness range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(ev, 1, 0);
			Assert.fail("accepted invalid risk range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(ev, 5, 6);
			Assert.fail("accepted invalid risk range");
		} catch (InvalidDataException e) {
		}

		Category ca = new Category("Test");
		Owner ow = new Owner("a@a", "ma", "12");
		try {
			Project po = new Project("AA", "BB", 10, 100, ow, ca);
			Evaluation e = new Evaluation(ev, 5, 5);
			po.addEvaluation(e);
			Assert.assertEquals(1, po.getEvaluations().size());
			Assert.assertEquals(e, po.getEvaluations().get(0));
		} catch (InvalidDataException e) {
			Assert.fail("error creating project");
		}

	}

	@Test
	public void testDocument() {

		URL location = Project.class.getProtectionDomain().getCodeSource()
				.getLocation();
		try {
			new Document(location.getFile() + "/model/Project.class");
		} catch (model.exception.InvalidDataException e) {
			Assert.fail("error creating document");
		}

		try {
			new Document(location.getFile() + "/model");
		} catch (model.exception.InvalidDataException e) {
			Assert.fail("invalid document path");
		}

		Category ca = new Category("Test");
		Owner ow = new Owner("a@a", "ma", "12");
		try {
			Project po = new Project("AA", "BB", 10, 100, ow, ca);
			Document doc = new Document(location.getFile()
					+ "/model/Project.class");
			po.addDocument(doc);
			Assert.assertEquals(po.getDocuments().size(), 1);
			Assert.assertEquals(po.getDocuments().get(0), doc);
		} catch (InvalidDataException e) {
			Assert.fail("error creating project");
		}
	}

	@Test
	public void testCategoriesDB() {
		try {
			List<Category> cats = CategoryDB.getCategories();
			if (cats.size() == 0) {
				Assert.fail("empty category list");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("Error accessing db");
		}
	}

	@Test
	public void testUserDB() {
		try {
			if (UserDB.checkLogin("george@geek.com", "4456")) {
				Assert.fail("error checking password");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");
		}

		try {
			if (UserDB.checkLogin("george@geek.com", "457")) {
				Assert.fail("error checking password");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");
		}

		try {
			Owner o = UserDB.getOwner("john@acme.com");
			if (o == null) {
				Assert.fail("error retrieving owner");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");

		}

		try {
			Evaluator e = UserDB.getEvaluator("sarah@geek.com");
			if (e == null) {
				Assert.fail("error retrieving evaluator");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");
		}

		try {
			Owner o = UserDB.getOwner("sarah@geek.com");
			if (o != null) {
				Assert.fail("error retrieving owner");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");
		}

		try {
			Evaluator e = UserDB.getEvaluator("john@acme.com");
			if (e != null) {
				Assert.fail("error retrieving evaluator");
			}
		} catch (DatabaseAccessError e) {
			Assert.fail("database error");

		}

	}

	@Test
	public void testProjectDB() {

		Owner o;
		try {
			o = UserDB.getOwner("john@acme.com");
			Category c = CategoryDB.getCategories().get(0);
			Project p = new Project("A", "B", 10, 1, o, c);
			
//			ProjectDB.saveProject(p);
			Project x = ProjectDB.getProject("A");
			Assert.assertEquals(p.getAcronym(), x.getAcronym());
			
			List<Project> ps = ProjectDB.getProjectsOfOwner(o);
			Assert.assertTrue(ps.contains(p));
			
			ps = ProjectDB.getProjectsOfOwner(UserDB.getOwner("paul@acme.com"));
			if(ps.size() != 0) {
				Assert.fail("error getting projects");
			}


		} catch (DatabaseAccessError | InvalidDataException e) {
			Assert.fail("error creating project");
		}

	}

	@Test
	public void testDatabase(){
		// user exists
		Assert.assertEquals( false, UserDB.createOwner("john@acme.com", "john", 123));
//		Assert.assertEquals(true, UserDB.createOwner("ttt@test.com", "cat", 333));
	}

}
