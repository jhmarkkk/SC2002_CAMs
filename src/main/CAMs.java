package main;

import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import enums.Role;

import interfaces.CurrentUserDao;

import models.User;

/**
 * The main class executing the Camp Application and Management System (CAMs).
 * This class handles the initialisation of data, log-in and starting the
 * appropriate session based on the role of logged-in user.
 * 
 * @author Chua Shan Hong
 *
 */
public class CAMs {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		do {
			DataTransferController.importData();
			SessionController.startSession();

			DataTransferController.exportData();
		} while (true);

	}

}
