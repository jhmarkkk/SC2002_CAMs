package controllers;

import enums.SortType;

import interfaces.views.CampViewable;
import interfaces.views.AttendeeViewable;
import interfaces.views.EnquiryViewable;
import interfaces.views.SuggestionViewable;
import interfaces.services.ToggleVisibilityServiceable;
import interfaces.services.CampServiceable;
import interfaces.services.ApproveSuggestionServiceable;
import interfaces.services.ReplyEnquiryServiceable;
import interfaces.services.GenerateReportServiceable;

import services.ToggleVisibilityService;
import services.StaffCampService;
import services.StaffApproveSuggestionService;
import services.StaffReplyEnquiryService;
import services.StaffGenerateReportService;

import views.StaffAllCampView;
import views.CreatedCampView;
import views.AttendeeView;
import views.StaffEnquiryView;
import views.StaffSuggestionView;

/**
 * This {@link StaffController} class is responsible for handling the
 * staff specific user interface and interactions. It extends the
 * {@link UserController} class and provides functionality for staff to
 * view his/her profile, change password, view all camps, view camps
 * created by him/herself, view attendees within aforementioned camps,
 * create camps, edit camps, delete camps, view suggestions, approve
 * suggestions, view enquiry, reply enquiry and generate report
 * @author Chua Shan Hong
 *
 */
public class StaffController extends AbstractUserController {
	
	private static CampViewable campView;
	
	private static final AttendeeViewable attendeeView = new AttendeeView();
	
	private static final EnquiryViewable enquiryView = new StaffEnquiryView();

	private static final SuggestionViewable suggestionView = new StaffSuggestionView();
	
	private static final ToggleVisibilityServiceable toggleVisibilityService = new ToggleVisibilityService();

	private static final CampServiceable campService = new StaffCampService();
	
	private static final ApproveSuggestionServiceable approveSuggestionService = new StaffApproveSuggestionService();
	
	private static final ReplyEnquiryServiceable replyEnquiryService = new StaffReplyEnquiryService();
	
	private static final GenerateReportServiceable generateReportService = new StaffGenerateReportService();
	
	@Override
	public void start() {
		
		int choice;
		
		do {
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View created camps");
			System.out.println("4. Create new camp");
			System.out.println("5. View suggestions");
			System.out.println("6. View enquiries");
			System.out.println("7. Generate Report");
			System.out.println("8. Log out");
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
				viewCreatedCamps();
				break;
			case 4:
				createCamp();
				break;
			case 5:
				viewSuggestions();
				break;
			case 6:
				viewEnquiries();
				break;
			case 7:
				generateReport();
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}

		} while(true);
	}

	@Override
	protected void viewAllCamps() {
		
		int choice;
		
		campView = new StaffAllCampView();
		campView.sortView(SortType.NAME);
		
		do {
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Sort by camp staff-in-charge");
			System.out.println("6. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				campView.sortView(SortType.DATES);
				break;
			case 2:
				campView.sortView(SortType.CLOSING_DATE);
				break;
			case 3:
				campView.sortView(SortType.LOCATION);
				break;
			case 4:
				campView.sortView(SortType.FACULTY);
				break;
			case 5:
				campView.sortView(SortType.STAFF);
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (true);
	}
	
	@Override
	protected void viewEnquiries() {
		
		int choice;
		
		enquiryView.view();
		
		do {
			System.out.println("1. Reply enquiry");
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
	
	protected void viewCreatedCamps() {

		int choice;
		
		campView = new CreatedCampView();
		campView.sortView(SortType.NAME);
		
		do {
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Sort by camp staff-in-charge");
			System.out.println("6. View camp attendees");
			System.out.println("7. Toggle visibility");
			System.out.println("8. Edit camp");
			System.out.println("9. Delete camp");
			System.out.println("10. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				campView.sortView(SortType.DATES);
				break;
			case 2:
				campView.sortView(SortType.CLOSING_DATE);
				break;
			case 3:
				campView.sortView(SortType.LOCATION);
				break;
			case 4:
				campView.sortView(SortType.FACULTY);
				break;
			case 5:
				campView.sortView(SortType.STAFF);
				break;
			case 6:
				viewAttendees();
				break;
			case 7:
				toggleVisibility();
				break;
			case 8:
				editCamp();
				break;
			case 9:
				deleteCamp();
				break;
			case 10:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
	}
	
	protected void viewAttendees() {
		
		attendeeView.view();
	}
	
	protected void toggleVisibility() {
		
		toggleVisibilityService.toggle();
	}
	
	protected void createCamp() {

		campService.create();
	}
	
	protected void editCamp() {
		
		campService.edit();
	}
	
	protected void deleteCamp() {
		
		campService.delete();
	}
	
	protected void viewSuggestions() {
		
		int choice;
		
		suggestionView.view();
		
		do {
			System.out.println("1. Approve/Reject suggestion");
			System.out.println("2. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				approveSuggestion();
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (true);
	}
	
	protected void approveSuggestion() {
		
		approveSuggestionService.approve();
	}
	
	protected void replyEnquiry() {
		
		replyEnquiryService.reply();
	}
	
	protected void generateReport() {
		
		generateReportService.generate();
	}
	
}
