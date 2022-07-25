package com.example.JavaAssignment2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.util.List;

/**
 * This class is used to make inter-thread communication between the two threads.
 * @author Jasvinder Singh
 * @version 1
 * @since 23/07/2022
 */
public class MyFiles {

    public static final Logger logger= LoggerFactory.getLogger(MyFiles.class);
    List<File> fileList;
    private Path moveFile;

    /**
     * This method is used to get the new files that is added to the folder.
     * @return List of files.
     */
    public List<File> getFile()
    {
        File[] files;
        File file = new File("C:\\Users\\ja.singh5\\Desktop\\JavaAssignment2");
        files = file.listFiles();//will get files present in folder JavaAssignment2.
        System.out.println(List.of(files));
        return List.of(files); //return all files in the form of a list.
    }

    /**
     * This is a synchronised method which is used to do communication with mover thread.
     */
    synchronized public void watchFile(){
        fileList=getFile();
        if (!fileList.isEmpty()){
            try {
                notify();//notify mover
                wait(); //wait until get notify from mover.

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This is a synchronised method which is used to do communication with watcher thread.
     */
    synchronized public void moveFile(){

        fileList.forEach((file) ->  {        // --> Iterate through the filenames and move
            try {
                //Move files from one folder to the other.
                moveFile = Files.move(Paths.get("C:\\Users\\ja.singh5\\Desktop\\JavaAssignment2\\"+file.getName()),Paths.get("C:\\Users\\ja.singh5\\Desktop\\JavaAssignment2.1\\"+file.getName()));
            } catch (IOException e) {
                logger.info("File Exception");
            }
            if(moveFile!= null)     // --> Verify that the file is moved successfully
            {
                logger.info("File moved successfully");
            }
            else
            {
                logger.info("Failed to move the file");
            }
        });
        try {
            notify(); //notify watcher.
            wait();  //wait until get notify from mover.

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
