package model.db;

import java.util.Map;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;

public class UserDB {
	
	private static Map<String,User> users;
	
	static {
		initializeUsersList();
	}

	private static void initializeUsersList() {
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
	}
	
	public static User getUser(String login) throws DatabaseAccessError {
		User u = users.get(login);
		if(u == null)
			return null;
		return u;
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

	public static boolean createUser(String username, String name, int password){
		return DBUtil.createUser(username, name, password);
	}
}
