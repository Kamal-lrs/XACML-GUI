/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;

/**
 *
 * @author kml
 */
public class View {
    
    public static void main(String[] args) throws Exception{
        
        MyDomParser md = new MyDomParser();
        String u = "Victor";
        String p = "ESJ61PQZ9BF";
        
        
        Boolean b = md.authentification(u, p);
        
        System.out.println(String.valueOf(b));
        
    }
}
