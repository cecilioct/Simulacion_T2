/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import static java.lang.Double.parseDouble;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class csMedias {
   

    int contador = 0;
    double acumulador = 0;
    String ruta;
    int x100t=0;
    double media;
    double alfamed;
    double LI;
    double LS;
    int busca=1;
    double va;
    int numfil = 0;
    int numCols = 0;
    double mat [][];

    

    public csMedias(String rutab, int x100to) {
        this.ruta = rutab;
        this.x100t = x100to;
    }

    csMedias() {
    }

    public void LeerExcel(String rut) {
        
       

        try (FileInputStream file = new FileInputStream(new File(rut))) {
            XSSFWorkbook libro = new XSSFWorkbook(file);
            XSSFSheet sheet = libro.getSheetAt(0);

            Iterator<Row> rowiterator = sheet.iterator();

            Row row;
            
           int x=0;
           int y=0;

            while (rowiterator.hasNext()) {
                row = rowiterator.next();

                Iterator<org.apache.poi.ss.usermodel.Cell> celliterator = row.cellIterator();
                org.apache.poi.ss.usermodel.Cell cell;
                 if (busca == 1) {
                    numfil = sheet.getLastRowNum()+1;
                }
               

                while (celliterator.hasNext()) {
                  
                    if (busca == 1) {
                        Row filast = sheet.getRow(contador);
                        numCols = filast.getLastCellNum();
                        busca = 2;
                        mat = new double[numfil][numCols];

                    }
                   
                
                    cell = celliterator.next();
                    contador++;
                    acumulador += (double) cell.getNumericCellValue();
                    
                    
                    va = (double)cell.getNumericCellValue();
                    mat[x][y]=va;
                   
                      
                    y++;
                    

                }
                y=0;
                x++;

            }
           
            

        } catch (Exception e) {
            e.getMessage();
        }

    }
    
    public double[] [] vector(){
         
        return mat;
    }
    

    public void calcmedia() {
        media = acumulador / contador;
    }

    public void alphaMedios() {
       
        double decimal;
        double porciento=100;
        decimal= x100t/porciento;
        
        decimal = 1-decimal;
        
        double alfa = decimal/2;
        
        
        JOptionPane.showMessageDialog(null, "Buscar en la tabla de distribución" + "\n"
                + "Distribucion Normal el valor de: " + alfa);
        String columna = JOptionPane.showInputDialog("Ingresa el valor de la columna: ");
        String fila = JOptionPane.showInputDialog("Ingese el valor de la fila: ");
        alfamed = parseDouble(columna) + parseDouble(fila);
    }

    public double equivalente(int i) {
        double a = 0;
        return a;
    }

    public void LS() {
        LS = 0.5 + (alfamed * (1 / Math.sqrt(12 * contador)));

    }

    public void LI() {
        LI = 0.5 - (alfamed * (1 / Math.sqrt(12 * contador)));

    }
    
    public void verMatriz(){
        for (int i = 0; i < numfil; i++) {
            for (int j = 0; j < numCols; j++) {
                
                System.out.print("["+mat[i][j]+"]");
            }
            System.out.println("");
            
        }
    
    }

    public boolean verificar() {
        return (LS > media && media > LI);
    }

    public double getAlfamed() {
        return alfamed;
    }

    public double getMedia() {
        return media;
    }

    public String getRuta() {
        return ruta;
    }

    public int getX100t() {
        return x100t;
    }
    
    public int getNumfil() {
        return numfil;
    }

    public int getNumCols() {
        return numCols;
    }
    
    public double getLI() {
        return LI;
    }

    public double getLS() {
        return LS;
    }
    

}
