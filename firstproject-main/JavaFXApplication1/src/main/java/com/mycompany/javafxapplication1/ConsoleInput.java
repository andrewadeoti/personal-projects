/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.javafxapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;




/**
 *
 * @author ntu-user
 */
public class ConsoleInput {

    /*
- makeFile
- mv
- cp
- ls
- mkdir
- ps
- whoami
- tree
- nano
     */
    
    //file and folder editing
    public static String makeFile(String source) throws IOException
    {
               
        String output = "File "+source+" Exists";
        String newLine = System.getProperty("line.separator");
        
        String temp = ls("");
        String[] fileList = temp.split(newLine, -1);
 
        boolean create = true;
        for (String i : fileList) {
            if (source.equals(i)) {
                create = false;
                break;
            }
        }
        if (create == true && !source.equals("")) {
            try {
                File myObj = new File(source);
                if (myObj.createNewFile()) {
                    output=("File created: " + myObj.getName());
                } else {
                    output=("File already exists.");
                }
            } catch (IOException e) {
                output=("An error occurred.");
            }
        } else if (source.equals("")) {
            output = "File " + source + " could not be created";
        }
        return output;
    }

    public static String deleteFile(String source) throws IOException
    {
        String output = "File "+source+" Doesn't Exist";
        String newLine = System.getProperty("line.separator");
        newLine = newLine+"             ";
        String temp = ls("");
        String[] fileList = temp.split(newLine, -1);
        
        boolean delete = false;
        for (String i : fileList) {
            if(source.equals(i))
            {
                delete = true;
                break;
            }
        }
        if (delete == true && !source.equals("")) {
            try {
                File myObj = new File(source);
                if (myObj.delete()) {
                    output =("Deleted the file: " + myObj.getName());
                } else {
                    output =("Failed to delete the file.");
                }
            } catch (Exception e) {
                output = "File " + source + " could not be deleted";
            }
        } else if (source.equals("")) {
            output = "File " + source + " could not be deleted";
        }

        return output;
    }
    
    public static String retriveFile(String source) throws IOException
    {
        String output = "File "+source+" Doesn't Exist";
        String newLine = System.getProperty("line.separator");
        newLine = newLine+"             ";
        String temp = ls("");
        String[] fileList = temp.split(newLine, -1);
        
        boolean exists = false;
        for (String i : fileList) {
            if(source.equals(i))
            {
                exists = true;
                break;
            }
        }
        newLine = System.getProperty("line.separator");
        if(exists==true && !source.equals(""))
        {
            try {
                var processBuilder = new ProcessBuilder();

                processBuilder.command("cat", source);

                var process = processBuilder.start();
                try (var reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {

                    String line;
                    output = "";
                    while ((line = reader.readLine()) != null) {
                        output = output+line+newLine;
                    }
                }
            } catch (IOException e) {
                output = "File " + source + " could not be found";
            }
        } else if (source.equals(""))
        {
            output="File "+source+" could not be found";
        }
        
        return output;
    }
    
    public static String updateFile(String source, String data) throws IOException
    {
        String output = "File " + source + " Doesn't Exist";
        String newLine = System.getProperty("line.separator");
        newLine = newLine + "             ";
        String temp = ls("");
        String[] fileList = temp.split(newLine, -1);

        boolean exists = false;
        for (String i : fileList) {
            if (source.equals(i)) {
                exists = true;
                break;
            }
        }
        if (exists == true && !source.equals("")) {
            try {
                try (java.io.FileWriter writer = new FileWriter(source)) {
                    writer.write(data);
                }
                output = "Successfully wrote to file: " + source;
            } catch (IOException e) {
                output = "Error writing to file: " + source;
            }
        } else if (source.equals("")) {
            output = "File " + source + " could not be found";
        }



        return output;
    }
    
    public static String mkdir(String directory) throws IOException 
    {
        String output; 
           
        try{
            
            var processBuilder = new ProcessBuilder();

            processBuilder.command("mkdir", directory);

            var process = processBuilder.start();
            output = "Folder made at " + getPath(directory);
        } catch (IOException e) {
            output = "Error Creating Folder";
        }
        return output;
    }
    
    public static String cp(String source, String destination) throws IOException 
    {
        String output = ""; 
           
        try {
            var processBuilder = new ProcessBuilder();

            processBuilder.command("cp", source, destination);

            var process = processBuilder.start();
            output = "File coppied at " + getPath(destination);
        } catch (IOException e) {
            output = "No files";
        }
        return output;
    }
    
    public static String mv(String source, String destination) throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("mv", source, destination);
        
        var process = processBuilder.start();

        String output; 
           
        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = "Moved "+getPath(source)+" to "+getPath(destination);
        }
        
        catch(Exception e)
        {
            output = "Couldn't move "+source+" to "+destination+" due to an error: "+e;
        }
        
        return output;
    }
    
    //file and folder inspecting
    
    public static String ls(String source) throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("ls"+source);

        var process = processBuilder.start();
        
        String output = "No files";
        String newLine = System.getProperty("line.separator");

        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            output = "";
            while ((line = reader.readLine()) != null) {
                output=line+newLine+"             "+output;
            }
        }
        
        return output;
        
    }

    public static String tree() throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("tree");
        
        var process = processBuilder.start();
        
        String output = "No files";
        String newLine = System.getProperty("line.separator");

        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            output = "";
            while ((line = reader.readLine()) != null) {
                output=line+newLine+output;
            }
        }
        
        return output;
    }
    
    
    public static String getPath(String source) throws IOException 
    {
        var processBuilder = new ProcessBuilder();

        processBuilder.command("readlink -f",source);
        
        var process = processBuilder.start();
        
        String output = "No files";

        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            output = "";
            while ((line = reader.readLine()) != null) {
                output+=line+" ";
            }
        }
        
        return output;
    }
    
    public static String nano() throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("nano");

        var process = processBuilder.start();

        String output = ""; 
           
        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            
            while ((line = reader.readLine()) != null) {
                output += line + " ";
            }
        }
        catch(Exception e)
        {
            output = "No files";
        }
        return output;
    }
    
    //system inquireies

    public static String ps() throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("ps");

        var process = processBuilder.start();

        String output = ""; 
        
        String newLine = System.getProperty("line.separator");
        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            
            while ((line = reader.readLine()) != null) {
                output += line + " "+newLine;
            }
        }
        catch(Exception e)
        {
            output = "No files";
        }
        return output;
    }

    public static String whoami() throws IOException 
    {

        var processBuilder = new ProcessBuilder();

        processBuilder.command("whoami");

        var process = processBuilder.start();

        String output = ""; 
           
        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            
            while ((line = reader.readLine()) != null) {
                output += line + " ";
            }
        }
        catch(Exception e)
        {
            output = "Error";
        }
        return output;
    }

    

}
