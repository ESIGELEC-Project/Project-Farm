package controller;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

import model.Document;
import model.db.ProjectDB;




@WebServlet("/addDocumentServlet")
public class AddDocument extends HttpServlet{
	private static final long serialVersionUID = 8564629054424385221L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// //using common-file external jar. without using external jar, see google drive
		   File file ;
		   HttpSession session = request.getSession();
		   String acronym = session.getAttribute("acronym").toString();
		   int maxFileSize = 5000 * 1024;//which is 5kb
		   int maxMemSize = 5000 * 1024;
		   ServletContext context = request.getServletContext();
		   String filePath = "\\";//have to change the directory in other os
		
		   // Verify the content type
		   String contentType = request.getContentType();
		   if ( (contentType!= null) && (contentType.indexOf("multipart/form-data") >= 0)) {
		
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File(filePath));
		
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		    	  // Parse the request to get file items.
		    	  List fileItems = upload.parseRequest(request);
		         
		    	  // Process the uploaded file items
		    	  Iterator i = fileItems.iterator();
		    	  
		    	  while ( i.hasNext () ) {
			            FileItem fi = (FileItem)i.next();
			            if ( !fi.isFormField () )	{
			            //to determine whether a file item contains a simple name-value pair of a form field or an uploaded file
			            // Get the uploaded file parameters
			            String fieldName = fi.getFieldName();
			            String fileName = fi.getName();
			            boolean isInMemory = fi.isInMemory();
			            long sizeInBytes = fi.getSize();
			            // Write the file
			            
			            if( fileName.lastIndexOf("\\") >= 0 ){
			      
			            	file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
			            	file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            }
				            fi.write( file ) ;
				            ProjectDB.getProject(acronym).addDocument(new Document(file.getPath()));//add the document name to database
			            }
			      }
		      }catch(Exception ex) {
		         System.out.println(ex);
		      }
		   }
		   RequestDispatcher dis = request.getRequestDispatcher("/my_projects.jsp");
		   dis.forward(request, response);
	}
}
