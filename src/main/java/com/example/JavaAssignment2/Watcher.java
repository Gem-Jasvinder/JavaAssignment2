package com.example.JavaAssignment2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * This class extends Thread Class and watch if there is any file present in the folder every 5 seconds and notify mover thread to move those files from one folder to the other.
 * @author Jasvinder Singh
 * @version 1
 * @since 23/07/2022
 */
public class Watcher extends Thread {

    public static final Logger logger= LoggerFactory.getLogger(Watcher.class);

    MyFiles f;

    /**
     * This is made to use MyFiles Class.
     * @param f used to pass MyFiles class.
     */
    public Watcher(MyFiles f){
        this.f=f;
    }

    /**
     * This method is used to run thread and  call watchFile method of MyFiles class to do its task.
     */
    public void run(){
        while(true) {
            this.f.watchFile();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                logger.info("Interrupted Exception");
            }
            logger.info("Watcher{}",Thread.currentThread().getName());
        }
    }
}
