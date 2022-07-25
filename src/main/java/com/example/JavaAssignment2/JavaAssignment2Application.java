package com.example.JavaAssignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class where object of MyFiles , Watcher and Mover is made and watcher and mover thread got started.
 * @author Jasvinder Singh
 * @version 1
 * @since 23/07/2022
 */
@SpringBootApplication
public class JavaAssignment2Application {
	/**
	 * Main method where watcher and mover thread starts.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(JavaAssignment2Application.class, args);
		MyFiles myFiles =new MyFiles();
		Watcher watcher=new Watcher(myFiles);
		Mover mover=new Mover(myFiles);
		watcher.start();
		mover.start();
		System.out.println("Hello world!");
	}

}
