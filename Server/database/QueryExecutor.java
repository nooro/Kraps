package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import log.Logger;

public class QueryExecutor {
	private Connection db;
	
	private PreparedStatement emailIsAlreadyRegistered = null;
	private PreparedStatement emailIsWaitingForApproval = null;
	private PreparedStatement addNewUserForApproval = null;
	
	public QueryExecutor(Connection connection) {
		this.db = connection;
		
		try {
			emailIsAlreadyRegistered = db.prepareStatement("SELECT COUNT(id) AS is_registered FROM users WHERE email = ?;");
			emailIsWaitingForApproval = db.prepareStatement("SELECT COUNT(id) AS is_waiting FROM waiting_for_approval WHERE email = ?;");
			addNewUserForApproval = db.prepareStatement("INSERT INTO waiting_for_approval VALUES(null, ?, ?, ?, ?, ?);");
		} catch (SQLException exception) {
			Logger.log(exception);
		}
	}
	
	public String register(String[] userQuery, String drivingLicensePhotoURL) {
		try {
			emailIsAlreadyRegistered.setString(1, userQuery[2]);
			ResultSet result = emailIsAlreadyRegistered.executeQuery();
			if(result.next()) {
				if(result.getInt("is_registered") == 0) {
					emailIsWaitingForApproval.setString(1, userQuery[2]);
					result = emailIsWaitingForApproval.executeQuery();
					if(result.next()) {
						if(result.getInt("is_waiting") == 0) {
							addNewUserForApproval.setString(1, userQuery[1]);
							addNewUserForApproval.setString(2, userQuery[2]);
							addNewUserForApproval.setString(3, userQuery[3]);
							addNewUserForApproval.setString(4, userQuery[4]);
							addNewUserForApproval.setString(5, drivingLicensePhotoURL);
							if(addNewUserForApproval.executeUpdate() == 1) {
								return "Successful registration.";
							}
							else {
								return "Something went wrong.";
							}
						}
						else {
							return "This email is already waiting to be approved.";
						}
					}
				}
				else {
					return "This email is already registered.";					
				}
			}
		} catch (SQLException e) {
			return "Something went wrong.";
		}
		return "Something went wrong.";
	}
}
