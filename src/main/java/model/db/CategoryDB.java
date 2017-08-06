package model.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Category;
import model.db.exception.DatabaseAccessError;

public class CategoryDB {
	
	private static Map<String,Category> categories;

	
	static {
		initializeCategoryList();
	}
	
	public static List<Category> getCategories() throws DatabaseAccessError {
		categories = DBUtil.queryCategoryDB();
		return new LinkedList<Category>(categories.values());
	}
	
	public static Category getCategory(String name) {
		return categories.get(name);
	}

	private static void initializeCategoryList() {
		categories = DBUtil.queryCategoryDB();
	}
	

}
