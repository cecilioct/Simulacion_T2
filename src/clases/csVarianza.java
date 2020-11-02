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

/**
 *
 */
public class csVarianza {
    int busca=1;
    double contador = 0;
    double acumulador = 0;
    double media = 0;
    double varianza = 0;
    int porcentaje;
    double liv;
    double lsv;
    double aph;
    double LI;
    double LS;
    String path;
    int numfil = 0;
    int numCols = 0;
    double mat [][];
    double va;

    public int getNumfil() {
        return numfil;
    }

    public int getNumCols() {
        return numCols;
    }
    
    

    

    public csVarianza(int p, String rta) {
        this.porcentaje = p;
        this.path = rta;

    }
    
     public double[] [] vector(){ 
        return mat;
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
                        Row filast = sheet.getRow((int) contador);
                        numCols = filast.getLastCellNum();
                        busca = 2;
                        mat = new double[numfil][numCols];

                    }

                    cell = celliterator.next();
                    if (media == 0) {
                        contador++;
                        acumulador += (double) cell.getNumericCellValue();
                    }
                    if (media != 0) {
                        double value = (double) cell.getNumericCellValue();
                        varianza += Math.pow((value - media), 2);
                    }
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

    public void calcularMedia() {
        media = acumulador / contador;
        //System.out.println("Media = "+media);

    }

    public void calcularVarianza() {
        varianza = varianza / contador;
        //System.out.println("Varianza = "+varianza);
    }

    public void alpha() {
        aph = (double)porcentaje/100; 
        aph=(double)(1-aph);
        //System.out.println("alpha "+aph);
        
        
        
        
    }
    public void equivalente(){
       
        //obteniendo equivalencia en tabla ji cuadrada
        
        double col = aph/2;
        //System.out.println("columna ="+col);
        int n = (int) (contador-1);
        //System.out.println("Fila ="+n);
         String val_1=JOptionPane.showInputDialog("De tabla DISTRIBCION CHI CUADRADA"+"\n"
                 +"Columna ="+col+"\n"
                 +"Fila ="+n);
        
        lsv=parseDouble(val_1);
        
        
        LI=(double)(1-(aph/2));
        //System.out.println("Columns ="+LI);
        int nn = (int) (contador-1);
        //System.out.println("Fila ="+contador);
        String val_2=JOptionPane.showInputDialog("De tabla DISTRIBCION CHI CUADRADA"+"\n"
                 +"Columna ="+LI+"\n"
                 +"Fila ="+nn);
        
        liv=parseDouble(val_2);
        
    }

    public void calcLI() {
        
        LI=liv/(12*(contador-1));
       

    }

    public void calcLS() {
        LS=lsv/(12*(contador-1));
       

    }
    
    public boolean verificar(){
        return varianza>LI && varianza<LS;
    
    }
     public double getLI() {
        return LI;
    }

    public double getLS() {
        return LS;
    }

    public double getContador() {
        return contador;
    }

    public double getAcumulador() {
        return acumulador;
    }

    public double getMedia() {
        return media;
    }

    public double getVarianza() {
        return varianza;
    }

}
