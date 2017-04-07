package model.db;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;

public class UserDB {
	
	private static Map<String,User> users;

	
	static {
//		users = new LinkedHashMap<String, User>();
//		DBUtil.initialize();
		initializeUsersList();
		//checkConnected();
	}

	private static void initializeUsersList() {
//		users.put("john@acme.com",new Owner("john@acme.com","John Silver","123"));
//		users.put("mary@acme.com",new Owner("mary@acme.com","Mary Moon","123"));
//		users.put("paul@acme.com",new Owner("paul@acme.com","Paul McDonalds","123"));
//		
//		users.put("sarah@geek.com",new Evaluator("sarah@geek.com","Sarah Logan","456"));
//		users.put("thibault@geek.com",new Evaluator("thibault@geek.com","Thibault Moulin","456"));
//		users.put("george@geek.com",new Evaluator("george@geek.com","George Papalodeminus","456"));
		users = DBUtil.selectUserDB();
	}
	
	public static boolean checkLogin(String login,String password) throws DatabaseAccessError{
		User u = users.get(login);
		if(u!=null){
			 if(u.getPassword().equals(password))
				 return true;
			 else
				 return false;
		}else{
			return false;
		}
//		String url = "jdbc:sqlite:ProjectFarm.db";
//		try {
//			Class.forName("org.sqlite.JDBC");
//			Connection con = DriverManager.getConnection(url);
//			Statement st = con.createStatement();
//			String query = "select * from user where email=\""+login+"\"";
//			ResultSet res = st.executeQuery(query);
//			String email = res.getString(1);
//			String pass = res.getString(3);
//			if(email==login&&pass==password){
//				return true;
//			}else{
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
	}
	
	public static User getUser(String login) throws DatabaseAccessError {
		User u = users.get(login);
		if(u == null)
			return null;
		return u;
		
//		String url = "jdbc:sqlite:ProjectFarm.db";
//		try {
//			Class.forName("org.sqlite.JDBC");
//			Connection con = DriverManager.getConnection(url);
//			Statement st = con.createStatement();
//			String query = "select * from user where email=\""+login+"\"";
//			ResultSet res = st.executeQuery(query);
//			String email = res.getString(1);
//			String name = res.getString(2);
//			String password = res.getString(3);
//			String type = res.getString(4);
//			if(type == "owner"){
//				st.close();
//				con.close();
//				return new Owner(email,name,password);
//			}else{
//				st.close();
//				con.close();
//				return new Evaluator(email,name,password);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
	}
	
	public static Owner getOwner(String login) throws DatabaseAccessError{
		User u = users.get(login);
		if(u == null || !(u instanceof Owner))
			return null;
		return (Owner) u;
	}
	
	public static Evaluator getEvaluator(String login) throws DatabaseAccessError {
		User u = users.get(login);
		if(u == null || !(u instanceof Evaluator))
			return null;
		return (Evaluator) u;		
	}

	public static void createUser(String username, String name, int password){
		Statement st;
		String url = "JDBC:sqlite:ProjectFarm.db";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			st = con.createStatement();
			String sql = "insert into user(username,name,password)values('"+username+"','"+name+"','"+password+"')";
			st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkCreatable(String username){
		Statement st;
		String url = "JDBC:sqlite:ProjectFarm.db";
		try{
			Class.forName("org.sqliet.JDBC");
			Connection con = DriverManager.getConnection(url);
			st = con.createStatement();
			String sql = "select username from user";
			st.executeQuery(sql);
		}catch(Exception e){
			
		}
		
	}
}
