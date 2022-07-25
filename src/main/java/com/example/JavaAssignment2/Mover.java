package com.example.JavaAssignment2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class extends Thread class and moves if there is any file present in the folder and notify watcher thread.
 * @author Jasvinder Singh
 * @version 1
 * @since 23/07/2022
 */
public class Mover extends Thread{

    public static final Logger logger= LoggerFactory.getLogger(Mover.class);
    MyFiles f;

    /**
     * This is made to use MyFiles Class.
     * @param f Used to pass MyFiles Class.
     */
    public Mover(MyFiles f){
        this.f=f;
    }

    /**
     * This method is used to run the thread and call moveFile method of MyFiles class to do its task.
     */
    public void run(){
        while(true) {
            this.f.moveFile();
            logger.info("Mover{}",Thread.currentThread().getName());
        }
    }
}
