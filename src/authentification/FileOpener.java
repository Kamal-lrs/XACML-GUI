/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author kml
 */
public class FileOpener {
    
    private String file_path=null;
    
    public String getInputPath() {
        JFileChooser fc=new JFileChooser();
        fc.setDialogTitle("Choose input file");
        int returnVal= fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            return file_path = file.getAbsolutePath();
        }
        return null;
    }
    
    public String read() throws IOException{
        JFileChooser fc=new JFileChooser();
        fc.setDialogTitle("Choose a file");
        int returnVal= fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            FileReader fr = null;
            BufferedReader br = null;
            String s = null;
            try {
                File file = new File(fc.getSelectedFile().getAbsolutePath());
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while( (s = br.readLine()) != null ){
                    s = s + "\n";
                }
                return s;
                
            } catch (FileNotFoundException ex) {
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileOpener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return null;
    }

}
