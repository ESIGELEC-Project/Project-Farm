package model;

import java.io.Serializable;

import model.exception.InvalidDataException;

public class Evaluation implements Serializable {

	private static final long serialVersionUID = -2917944580171297941L;

	private Project project;
	private Evaluator evaluator;
	private int attractiveness;
	private int riskLevel;

	public Evaluation(Evaluator evaluator, int attractiveness,
			int riskLevel) throws InvalidDataException {
		setEvaluator(evaluator);
		setAttractiveness(attractiveness);
		setRiskLevel(riskLevel);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator evaluator) throws InvalidDataException {
		if(evaluator == null) {
			throw new InvalidDataException("Evaluator must be specified");
		}
		this.evaluator = evaluator;
	}

	public int getAttractiveness() {
		return attractiveness;
	}

	public void setAttractiveness(int attractiveness) throws InvalidDataException {
		if(attractiveness < 1 || attractiveness > 5) {
			throw new InvalidDataException("Attractiveness must range between 1 and 5");
		}		
		this.attractiveness = attractiveness;
	}

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(int riskLevel) throws InvalidDataException {
		if(riskLevel < 1 || riskLevel > 5) {
			throw new InvalidDataException("Risk level must range between 1 and 5");
		}		
		this.riskLevel = riskLevel;
	}

}
