package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.db.DBUtil;
import model.exception.InvalidDataException;

public class Project implements Serializable {

	private static final long serialVersionUID = 2180069907986538519L;

	private String acronym;
	private String description;
	private int fundingDuration;
	private double budget;
	private Date created;
	private Owner owner;
	private Category category;
	private List<Evaluation> evaluations;
	private List<Document> documents;

	public Project(String acronym, String description, int fundingDuration,
			double budget, Owner owner, Category category) throws InvalidDataException {
		setAcronym(acronym);
		setDescription(description);
		setFundingDuration(fundingDuration);
		setBudget(budget);
		setCreated(new Date());
		setOwner(owner);
		setCategory(category);
		setEvaluations(new LinkedList<Evaluation>());
		setDocuments(new LinkedList<Document>());
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) throws InvalidDataException {
		if(acronym == null || acronym.trim().equals("")) {
			throw new InvalidDataException("Acronym is mandatory");
		}
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws InvalidDataException {
		if(description == null || description.trim().equals("")) {
			throw new InvalidDataException("Description is mandatory");
		}		
		this.description = description;
	}

	public int getFundingDuration() {
		return fundingDuration;
	}

	public void setFundingDuration(int fundingDuration) throws InvalidDataException {
		if(fundingDuration <= 0) {
			throw new InvalidDataException("Funding must be specified");
		}				
		this.fundingDuration = fundingDuration;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) throws InvalidDataException {
		if(budget <= 0) {
			throw new InvalidDataException("budget must be specified");
		}				
		this.budget = budget;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) throws InvalidDataException {
		if(owner == null) {
			throw new InvalidDataException("Project must have an owner");
		}				
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) throws InvalidDataException {
		if(category == null) {
			throw new InvalidDataException("Project must have a category");
		}				
		this.category = category;
	}

	public void addEvaluation(Evaluation eval) {
		//evaluations.add(eval);
//		eval.setProject(this);
		DBUtil.addEvaluation(this.acronym, eval.getEvaluator().getEmail(), eval);
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
	
	//here will update database
	public void addDocument(Document doc) {
		//documents.add(doc);
		DBUtil.addDocPath(this.acronym, doc);
	}
	
	public List<Document> getDocuments() {
		return documents;
	}

}
