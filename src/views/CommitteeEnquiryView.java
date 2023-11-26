package views;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Student;
import utils.PrintUtil;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;
import interfaces.dao.CampDao;

import java.util.Map;

import dao.CampDaoImpl;

public class CommitteeEnquiryView implements EnquiryViewable {
    public void view() {
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
        CampDao campDao = new CampDaoImpl();
        Camp facilitatingCamp = campDao.getCamps().get(committeeMember.getFacilitatingCamp());
        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentsMap = studentDao.getStudents();
        PrintUtil.header("Facilitating Camp Enquiries");
        for (Enquiry enq : facilitatingCamp.getEnquiries().values()) {
            System.out.println("-".repeat(29));
            System.out.printf("%-10s: %s\n", "Enquiry ID", enq.getEnquiryID());
            System.out.printf("%-10s: %s\n", "Enquirer", studentsMap.get(enq.getEnquirer()));
            System.out.printf("%-10s: %s\n", "Camp", facilitatingCamp.getName());
            System.out.printf("%-10s: %s\n", "Enquiry", enq.getEnquiry());
            if (enq.getReplier() != null) {
                System.out.printf("%-10s: %s\n", "Replier", enq.getReplier());
                System.out.printf("%-10s: %s\n", "Reply", enq.getReply());
            }
            System.out.println();
        }

    }
}
