/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.*;


public class DocSalvato {
    String linea,testo;
    private File f;
   private  BufferedReader re;
   
   
   public DocSalvato() throws IOException{
       
 f = new File("salvato/infoGAME.txt");
 testo=new String("");
   }
    
    public String stampaIlFile() throws FileNotFoundException, IOException{

if ( !f.exists() )
{ System.out.println("infoGAME.txt non esiste!");
}else{
    try {FileReader fr = new FileReader(f); 
        re = new BufferedReader(fr);
         linea=re.readLine();
        
        while (linea!=null)
        { 
           testo+=linea+"\n";
        
                linea=re.readLine();;
     
             
             
    
            
       
      
        }  
 
    
    }catch(Exception esc){System.out.println(esc);}
    finally{ re.close();}

}

// System.out.println(linea);
return testo;
}
    
}
