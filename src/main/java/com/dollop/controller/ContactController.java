package com.dollop.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dollop.dao.ContactInterfaceDAO;
import com.dollop.dao.ContactInterfaceDAOImpl;
import com.dollop.model.Contact;

/**
 * Servlet implementation class ContactController
 */
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	ContactInterfaceDAO contactDAO = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
		super();
		contactDAO = new ContactInterfaceDAOImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "LIST";
		}

		switch (action) {

		case "ADD":
			createContact(request, response);
			break;

		case "LIST":
			listContact(request, response);
			break;

		case "EDIT":
			getSingleContact(request, response);
			break;

		case "DELETE":
			deleteContact(request, response);
			break;

		default:
			listContact(request, response);
			break;

		}

	}

	private void listContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Contact> theList = contactDAO.viewAllContact();

		request.setAttribute("list", theList);
		
		dispatcher = request.getRequestDispatcher("/views/contact-list.jsp");
		
		dispatcher.forward(request, response);

	}

	private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if(contactDAO.deleteContact(Integer.parseInt(id))) {
			request.setAttribute("NOTIFICATION", "Employee Deleted Successfully!");
		}
		
		listContact(request, response);

	}

	private void getSingleContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Contact theContact = contactDAO.viewContactById(Integer.parseInt(id));
		
		request.setAttribute("contact", theContact);
		
		dispatcher = request.getRequestDispatcher("/views/contact-form.jsp");
		
		dispatcher.forward(request, response);

	}

	private void createContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String id = request.getParameter("id");
		
		Contact contact = new Contact();
		contact.setName(request.getParameter("name"));
		contact.setEmail(request.getParameter("email"));
		contact.setPhone(new BigInteger(request.getParameter("phone")));
		
		if(id.isEmpty() || id == null) {
			
			if(contactDAO.createContact(contact)) {
				request.setAttribute("NOTIFICATION", "Contact Saved Successfully!");
			}
		
		}else {
			
			contact.setId(Integer.parseInt(id));
			if(contactDAO.updateContact(contact)) {
				request.setAttribute("NOTIFICATION", "Contact Updated Successfully!");
			}
			
		}
		dispatcher = request.getRequestDispatcher("/views/contact-form.jsp");
		
		dispatcher.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
