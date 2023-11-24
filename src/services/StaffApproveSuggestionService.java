package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ApproveSuggestionServiceable;
import models.Camp;
import models.Staff;

public class StaffApproveSuggestionService implements ApproveSuggestionServiceable {

    Scanner sc = new Scanner(System.in);
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    private static final Staff staff = (Staff) currentUserDao.getCurrentUser();
    private static final ArrayList<String> createdCampIDs = staff.getCreatedCamps();
    private static final CampDao campDao = new CampDaoImpl();
    private static final Map<String, Camp> campsMap = campDao.getCamps();

    public void approve() {
        int i = 0, choice;
        Map<Integer, String> sugIDtoCampMap = new HashMap<Integer, String>();
        for (String createdCampID : createdCampIDs) {
            for (Integer suggestionID : campsMap.get(createdCampID).getSuggestions().keySet()) {
                sugIDtoCampMap.put(suggestionID, createdCampID);
            }
        }
        do {
            System.out.printf("===== Suggestions =====\n");
            for (Integer suggestionID : sugIDtoCampMap.keySet()) {
    
                System.out.printf("%d. Suggestion ID %d ", i++, );
            }

            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 0 || choice <= i) {
                suggestionDeleteID = suggestionIDs.get(i);
                break;
            }
        } while (true);
    }
}
