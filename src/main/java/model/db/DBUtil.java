package model.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Category;
import model.Document;
import model.Evaluation;
import model.Evaluator;
import model.Owner;
import model.Project;
import model.User;
import model.db.exception.DatabaseAccessError;

public class DBUtil {
	private static Connection con;
	private static DateFormat format;
	static{
		format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
		initialize();
	}
	public static void initialize(){
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:/Users/Frank/Documents/workspace_JEE/ProjectFarm/src/ProjectFarm.db";//the address should be changed to where the database resides
			con = DriverManager.getConnection(url);
			if(con != null){
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println(dm.getURL());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static Map<String,User> selectUserDB() {
		Map<String,User> users = new LinkedHashMap<String, User>();
		try {
			Statement st = con.createStatement();
			String query = "select * from user";
			ResultSet result = st.executeQuery(query);
			while(result.next()){
				if(result.getString(4).equals("owner") )
					users.put(result.getString(1), new Owner(result.getString(1),result.getString(2),result.getString(3)));
				if(result.getString(4).equals("evaluator"))
					users.put(result.getString(1), new Evaluator(result.getString(1),result.getString(2),result.getString(3)));
			}
			st.close();
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return users;
		}
	}

	public static Map<String,Category> queryCategoryDB(){
		Map<String,Category> categories = new LinkedHashMap<String, Category>();
		try {
			Statement st = con.createStatement();
			String query = "select name, description from category";
			ResultSet result = st.executeQuery(query);
			while(result.next()){
				categories.put(result.getString(1), new Category(result.getString(2)));
			}
			return categories;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return categories;
		}
		
	}
	
	public static Map<String,Project> queryProjectDB(){
		Map<String,Project> projects = new LinkedHashMap<String, Project>();
		try {
			Statement st = con.createStatement();
			String query = "select * from project";
			ResultSet result = st.executeQuery(query);
			String acronym;
			String description;
			int funding_duration;
			double budget;
			Date created_date;
			String owner_email;
			String category_name;
			Owner owner;
			while(result.next()){
				acronym = result.getString(2);
				description = result.getString(3);
				funding_duration = Integer.parseInt(result.getString(4));
				budget = Double.parseDouble(result.getString(5));
				created_date = format.parse(result.getString(6));
				owner_email = result.getString(7);
				category_name = result.getString(8);
				Statement st2 = con.createStatement();
				ResultSet result2 = st2.executeQuery("select name, password from user where email='"+owner_email +"'");
				owner = new Owner(owner_email, result2.getString(1), result2.getString(2));
				Project p = new Project(acronym, description, funding_duration, budget, owner,  new Category(category_name));
				List<Document> documents = queryDoc(acronym);
				List<Evaluation> evaluations = queryEval(acronym);
				p.setCreated(created_date);
				p.setDocuments(documents);
				p.setEvaluations(evaluations);
				
				projects.put(acronym,p);
			}
			return projects;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return projects;
		}
	}
	
	public static void addDocPath(String acronym, Document doc){
		String path = doc.getDocumentPath();
		String date = format.format(doc.getAdded());
		try {
			Statement st = con.createStatement();
			String query = "insert into document values ('"+ acronym +"','"+ path +"','"+ date +"')";
			st.executeUpdate(query);
			st.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void addEvaluation(String acronym, String evaluator_email,Evaluation ev){
		int risk = ev.getRiskLevel();
		int attr = ev.getAttractiveness();
		
		try {
			Statement st = con.createStatement();
			String query = "insert into evaluation values ('"+ acronym +"','"+ evaluator_email +"','"+ risk +"','"+ attr +"')";
			st.executeUpdate(query);
			st.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void addProject(Project p) throws SQLException{// update evaluation table, document table
		DatabaseAccessError duplicated_name_error = new DatabaseAccessError("can not add the same acronym with an existing project in database!");
		try {
			String acronym = p.getAcronym();
			if(isAcronymInDatabase(acronym))
				throw duplicated_name_error;
			else{
				String description = p.getDescription();
				int funding_duration = p.getFundingDuration() ;
				double budget = p.getBudget();
				String created_date = format.format(p.getCreated());
				String owner_email = p.getOwner().getEmail();
				String category_name = p.getCategory().getDescription();
				
				Statement st = con.createStatement();
				String query = "insert into project(acronym,description,funding_duration,budget,created_date,owner_email,category_name)"
						+ " values('"+ acronym +"','"+ description +"','"+ funding_duration +"','"+ budget +"','"+ created_date+
						"','"+ owner_email+ "','"+ category_name +"')";
				st.executeUpdate(query);
				st.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static List<Document> queryDoc(String acronym){
		List<Document> docs = new ArrayList<Document>();
		try {
			Statement st = con.createStatement();
			String query = "select * from document where project_acronym='"+ acronym +"'";
			ResultSet re = st.executeQuery(query);
			Document doc;
			while(re.next()){
				doc = new Document(re.getString(2));
				doc.setAdded(format.parse(re.getString(3)));
				docs.add(doc);
			}
			st.close();
			return docs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return docs;
		}
	}
	
	private static List<Evaluation> queryEval(String acronym){
		//notice that query a resultset inside another resultset should have different statement object, otherwise the outside  
		//resultset will be closed when the inside resultset has been queried out.
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		try {
			Statement st = con.createStatement();
			String query = "select * from evaluation where acronym='"+ acronym +"'";
			ResultSet re = st.executeQuery(query);
			Evaluation evaluation;
			while(re.next()){
				String evaluator_email = re.getString(2);
				int attractive = Integer.parseInt(re.getString(4));
				int risk = Integer.parseInt(re.getString(3));
				String query2 = "select * from user where email='"+evaluator_email+"'";
				Statement st2 = con.createStatement();
				ResultSet resultTemp = st2.executeQuery(query2);
				String name = new String();
				String password = new String();
				while(resultTemp.next()){
					 name = resultTemp.getString(2);
					 password = resultTemp.getString(3);
				}
				
				Evaluator ev = new Evaluator(evaluator_email,name,password);
				evaluation = new Evaluation(ev, attractive, risk);
				
				Project project = getProject(acronym,st2);
				
				st2.close();
				
				evaluation.setProject(project);
				
				evaluations.add(evaluation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return evaluations;
		}
	}
		
	private static boolean isAcronymInDatabase(String acronym){
		boolean flag = true;
		Statement st = null;
		try {
			st = con.createStatement();
			String query = "select acronym from project where acronym = '" + acronym + "'";
			ResultSet result = st.executeQuery(query);
			if(!result.next())
				flag = false; 
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return flag;
		}
		
	}
	
	public static Map<String,Project> getProjectOfOwner (Owner o){
		Map<String,Project> projects = new LinkedHashMap<String, Project> ();
		String owner_email = o.getEmail();
		String query = "select * from project where owner_email = '" + owner_email + "'";
		String acronym;
		String description;
		int funding_duration;
		double budget;
		Date created_date;
		String category_name;
		Owner owner;
		try {
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()){
				acronym = r.getString(2);
				description = r.getString(3);
				funding_duration = Integer.parseInt(r.getString(4));
				budget = Double.parseDouble(r.getString(5));
				created_date = format.parse(r.getString(6));
				owner_email = r.getString(7);
				category_name = r.getString(8);
				Statement st2 = con.createStatement();
				ResultSet result2 = st2.executeQuery("select name, password from user where email='"+owner_email +"'");
				owner = new Owner(owner_email, result2.getString(1), result2.getString(2));
				Project p = new Project(acronym, description, funding_duration, budget, owner,  new Category(category_name));
				List<Document> documents = queryDoc(acronym);
				List<Evaluation> evaluations = queryEval(acronym);
				
				p.setCreated(created_date);
				p.setDocuments(documents);
				p.setEvaluations(evaluations);
				
				projects.put(acronym,p);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return projects;
		}
	}

	public static  Category queryCategoryOfName (String name) {
		Category c = new Category(name);
		try{
			Statement st = con.createStatement();
			String query = "select * from category where name = '"+ name +"'";
			ResultSet r = st.executeQuery(query);
			String description;
			while(r.next()){//not duplicated
				description = r.getString(3);
				c.setDescription(description);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			return c;
		}
		
		
		
	}

	public static Project getProject(String acronym, Statement st){
		Project p = null;
		String query = "select * from project where acronym='"+acronym+"'";
		try{
		ResultSet result = st.executeQuery(query);
		String description;
		int funding_duration;
		double budget;
		Date created_date;
		String owner_email;
		String category_name;
		Owner owner;
		while(result.next()){
			acronym = result.getString(2);
			description = result.getString(3);
			funding_duration = Integer.parseInt(result.getString(4));
			budget = Double.parseDouble(result.getString(5));
			created_date = format.parse(result.getString(6));
			owner_email = result.getString(7);
			category_name = result.getString(8);
			Statement st2 = con.createStatement();
			ResultSet result2 = st2.executeQuery("select name, password from user where email='"+owner_email +"'");
			owner = new Owner(owner_email, result2.getString(1), result2.getString(2));
			p = new Project(acronym, description, funding_duration, budget, owner,  new Category(category_name));
			List<Document> documents = queryDoc(acronym);
			
			p.setCreated(created_date);
			p.setDocuments(documents);
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return p;
		}
	}
	
}
