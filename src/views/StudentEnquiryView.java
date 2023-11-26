package views;

import java.util.ArrayList;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;
import models.Student;
import utils.PrintUtil;

public class StudentEnquiryView implements EnquiryViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void view() {

        String campName;
        Enquiry enquiry;
        ArrayList<Integer> studentCampEnquiryIDList;
        Map<Integer, Enquiry> campEnquiryData;
        Map<String, Camp> campsData = campDao.getCamps();
        Student student = (Student) currentUserDao.getCurrentUser();

        PrintUtil.header("My Enquiries");
        for (Map.Entry<String, ArrayList<Integer>> entry : student.getEnquiries().entrySet()) {
            campName = entry.getKey();
            studentCampEnquiryIDList = entry.getValue();
            campEnquiryData = campsData.get(campName).getEnquiries();
            for (Integer enquiryID : studentCampEnquiryIDList) {
                enquiry = campEnquiryData.get(enquiryID);
                System.out.println("-".repeat(29));
                System.out.printf("%-10s: %s\n", "Enquiry ID", enquiry.getEnquiryID());
                System.out.printf("%-10s: %s\n", "Camp", campName);
                System.out.printf("%-10s: %s\n", "Enquiry", enquiry.getEnquiry());
                if (enquiry.getReplier() != null) {
                    System.out.printf("%-10s: %s\n", "Replier", enquiry.getReplier());
                    System.out.printf("%-10s: %s\n", "Reply", enquiry.getReply());
                }

                System.out.println();
            }
        }
    }
}
