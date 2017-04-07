package model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import model.exception.InvalidDataException;

public class Document implements Serializable {

	private static final long serialVersionUID = 3404763898326246494L;
	
	private String documentPath;
	private Date added;
	
	public Document(String documentPath) throws InvalidDataException {
		setDocumentPath(documentPath);
		setAdded(new Date());
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) throws InvalidDataException {
		File file = new File(documentPath);
		
		if(!file.exists()) {
			throw new InvalidDataException("File " + documentPath + " does not exists");
		}
		
		if(!file.isFile()) {
			throw new InvalidDataException("Path " + documentPath + " does not point to a file");
		}
		
		this.documentPath = documentPath;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

}
