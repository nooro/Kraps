package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	
	private static DateTimeFormatter time = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
	private static BufferedWriter writer;
	
	public static void open() {
		time = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
		String fileName = "./" + time.format(LocalDateTime.now()) + ".log";
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
		} catch (IOException e) {
			System.out.println("Can not create the log file.");
		}
		time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	}
	
	public static void close() {
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Can not close the log file writer.");
		}
	}
	
	public static synchronized void log(Exception exception) {
		String logText = time.format(LocalDateTime.now()) + " | " + "Error: " + exception.getMessage() + "\n";
		System.out.print(logText);
		try {
			writer.write(logText);
			writer.newLine();
		} catch (IOException e) { 
			System.out.println("Can not write to the log file.");
		}
	}
	
	public static synchronized void log(String message) {
		String logText = time.format(LocalDateTime.now()) + " | " + message + "\n";
		System.out.print(logText);
		try {
			writer.write(logText);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Can not write to the log file.");
		}
	}
	
}
