package services;

import java.util.Scanner;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;
import interfaces.services.EnquiryServiceable;
import models.Enquiry;
import models.Student;


public class StudentEnquiryService implements EnquiryServiceable {

    Scanner sc = new Scanner(System.in);

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    //Map<Integer, Enquiry> 

    public void create(){
        //System.out.println("create() in StudentEnquiryService class");
        
        System.out.println("Enter Enquiry >>> ");
        String enquiryString = sc.nextLine();

        Student currentUser = (Student) currentUserDao.getCurrentUser();
        String enquirer = currentUser.getName();
        String replier = null;
        String reply = null;

        Enquiry enquiry = new Enquiry(enquiryString, enquirer, replier, reply);

        //put in enquiry map

        // // ===== For testing purpose only, remove/comment out after testing =====
        // System.out.println("enquiryString: " + enquiry.getEnquiry());
        // System.out.println("enquirer: " + enquiry.getEnquirer());
        // System.out.println("replier: " + enquiry.getReplier());
        // System.out.println("reply: " + enquiry.getReply());
    }
    public void delete(){
        System.out.println("delete() in StudentEnquiryService class");



    }
    public void edit(){
        // TODO Auto-generated method stub
    }
}
