/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author kml
 */
public class MyDomParser {

    private String content = null;
    private String u_username = null;
    private String u_password = null;
    private String u_group = null;
    private Boolean check = null;
    
    //constructor
    public MyDomParser(){
        content = null;
    }
    
    //getters & setters
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getU_username() {
        return u_username;
    }
      
    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }
    
    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_group() {
        return u_group;
    }

    public void setU_group(String u_group) {
        this.u_group = u_group;
    }
    
    public Boolean getCheck() {
        return check;
    }
    
    public void setCheck(Boolean check) {
        this.check = check;
    }
    
      
      //fct that returns the builder of doc xml
    public static Document xmlOpen() throws IOException, ParserConfigurationException, SAXException{
        
        File xmlDoc = new File("users.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlDoc);
        return doc;
    }
    
    //returns the content of a specified tag
    public String contentOfTag(String tag, String username) throws Exception{
          
        Document doc = xmlOpen();
        //get content
        NodeList userList = doc.getElementsByTagName("user");
        
        for(int i=0; i<userList.getLength(); i++){
            Node user = userList.item(i);
            
            if(user.getNodeType() == Node.ELEMENT_NODE){
                Element eUser = (Element)user; 
                u_username = eUser.getElementsByTagName("name").item(0).getTextContent();
                if(u_username.equals(username)){
                    content = eUser.getElementsByTagName(tag).item(0).getTextContent();
                }    
            }
        }
        
        
        return this.getContent();
    }
    
    //check for correct passwords
    public Boolean authentification(String username, String password) throws Exception{
           
        Document doc = xmlOpen(); 
        //get content
        NodeList userList = doc.getElementsByTagName("user");
        
        for(int i=0; i<userList.getLength(); i++){
            Node user = userList.item(i);
            if(user.getNodeType() == Node.ELEMENT_NODE){
                Element eUser = (Element)user;
                //assign the value from the xml file
                u_username = eUser.getElementsByTagName("name").item(0).getTextContent();
                u_password = eUser.getElementsByTagName("password").item(0).getTextContent();
                //verification of values 
                if(!username.equals(u_username)){
                    continue;
                }
                if(password.equals(u_password)){
                    check = true;
                    return this.getCheck();
                }
            }
        }
        check = false;
        return this.getCheck();
    }

}
