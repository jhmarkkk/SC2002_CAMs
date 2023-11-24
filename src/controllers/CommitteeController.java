package controllers;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.services.GenerateReportServiceable;
import interfaces.services.ReplyEnquiryServiceable;
import interfaces.services.SuggestionServiceable;
import interfaces.views.CampDetailViewable;
import interfaces.views.SuggestionViewable;

import models.CommitteeMember;

import services.CommitteeGenerateReportService;
import services.CommitteeReplyEnquiryService;
import services.CommitteeSuggestionService;

import views.CampDetailView;
import views.CommitteeEnquiryView;
import views.CommitteeSuggestionView;

public class CommitteeController extends StudentController {
	
	private static CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final CampDetailViewable campDetailView = new CampDetailView();
	
	private static final SuggestionViewable suggestionView = new CommitteeSuggestionView();
	
	private static final ReplyEnquiryServiceable replyEnquiryService = new CommitteeReplyEnquiryService();
	
	private static final SuggestionServiceable suggestionService = new CommitteeSuggestionService();
	
	private static final GenerateReportServiceable generateReportService = new CommitteeGenerateReportService();

	@Override
	public void start() {
		
		CommitteeMember committeeMember = (CommitteeMember)currentUserDao.getCurrentUser();
		int choice;
		
		do {
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View registered camps");
			System.out.println("4. View enquiries");
			System.out.println("5. Create enquiry");
			System.out.printf("6. View %s details", committeeMember.getFacilitatingCamp());
			System.out.println("7. Log out");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				viewProfile();
				break;
			case 2:
				viewAllCamps();
				break;
			case 3:
				viewRegisteredCamps();
				break;
			case 4:
				viewEnquiries();
				break;
			case 5:
				createEnquiry();
				break;
			case 6:
				viewCampDetails();
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while(true);
	}
	
	protected void viewCampDetails() {
		
		int choice;
		
		campDetailView.view();
		
		do {
			System.out.println("1. View attendees' enquires");
			System.out.println("2. View suggestions");
			System.out.println("3. Create suggestion");
			System.out.println("4. Generate attendee report");
			System.out.println("5. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				viewAttendeesEnquiries();
				break;
			case 2:
				viewSuggestions();
				break;
			case 3:
				createSuggestion();
				break;
			case 4:
				generateReport();
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}

		} while(true);
	}
	
	protected void viewAttendeesEnquiries() {
		
		int choice;
		
		enquiryView = new CommitteeEnquiryView();
		enquiryView.view();
		
		do {
			System.out.println("1. Reply attendees' enquiry");
			System.out.println("2. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				replyEnquiry();
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (true);
	}
	
	protected void viewSuggestions() {

		int choice;
		
		suggestionView.view();
		
		do {
			System.out.println("1. Create suggestion");
			System.out.println("2. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				createSuggestion();
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
	}
	
	protected void createSuggestion() {

		suggestionService.create();
	}
	
	protected void editSuggestion() {

		suggestionService.edit();
	}
	
	protected void deleteSuggestion() {

		suggestionService.delete();
	}
	
	protected void replyEnquiry() {
		
		replyEnquiryService.reply();
	}
	
	protected void generateReport() {
		
		generateReportService.generate();
	}
	
}
