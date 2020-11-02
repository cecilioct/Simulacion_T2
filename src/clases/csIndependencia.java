/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 */
public class csIndependencia {
    String path;
    int porcentaje;
    double equivalente;
    int contador = 0;
    double antes;
    int dimension;
    int busca = 1;
    double valores[];
    int binario[];
    int Co = 1;
    double Mco;
    double Aco;
    double Zo;
    double aph;
    double va;
    
    int numfil = 0;
    int numCols = 0;
    double mat [][];

    
    

   
   
    public csIndependencia(String path, int porcentaje) {
        this.path = path;
        this.porcentaje = porcentaje;
    }
     public double getAph() {
        return aph;
    }
    
    public double getEquivalente() {
        return equivalente;
    }

    public double getMco() {
        return Mco;
    }

    public double getAco() {
        return Aco;
    }

    public double getZo() {
        return Zo;
    }
    public int getNumfil() {
        return numfil;
    }

    public int getNumCols() {
        return numCols;
    }
    

    public void LeerExcel(String pa) {

        try (FileInputStream file = new FileInputStream(new File(pa))) {
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
                    numfil = sheet.getLastRowNum();
                }

                while (celliterator.hasNext()) {

                    if (busca == 1) {
                        Row filast = sheet.getRow(contador);
                        numCols = filast.getLastCellNum();
                        dimension = (numfil + 1) * numCols;
                        valores = new double[dimension];
                        busca = 2;
                         mat = new double[numfil][numCols];

                    }

                    cell = celliterator.next();

                    valores[contador] = (double) cell.getNumericCellValue();
                    contador++;
                    
                    va = (double)cell.getNumericCellValue();
                    mat[x][y]=va;
                    
                    System.out.println("Matriz ="+mat[x][y]);
                    System.out.println("X ="+x);
                    System.out.println("Y ="+y);
                    
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

    public void verValores() {
        for (int i = 0; i < valores.length; i++) {

            System.out.println("[" + valores[i] + "]");
        }
    }

    public void determinarCorridas() {
        binario = new int[dimension];
        for (int i = 0; i < valores.length; i++) {

            if (i == 0) {
                antes = valores[i];
            } else {

                if (valores[i] > antes) {
                    binario[i] = 0;
                    antes = valores[i];

                } else if (valores[i] < antes) {
                    binario[i] = 1;
                    antes = valores[i];

                }

            }
        }

    }

    public void verBin() {
        for (int i = 0; i < binario.length; i++) {
            System.out.print("[" + binario[i] + "] ");

        }

    }

    public void NumeroCorridas() {
        int band = 3;

        for (int i = 0; i < binario.length; i++) {

            if (binario[i] == 0) {
                if (band == 1) {
                    Co++;
                }
                band = 0;

            } else if (binario[i] == 1) {
                if (band == 0) {
                    Co++;
                }
                band = 1;
            }

        }

    }

    public void CMco() {
        Mco = (double) ((2 * contador) - 1) / 3;
    }

    public void CAco() {
        Aco = (double) ((16 * contador) - 29) / 90;
    }
    
    public void CZo(){
        double raiz = Math.sqrt(Aco);
        Zo = (double)(Co-Mco)/raiz;
        if(Zo<0){
            Zo = (double) Zo * -1;
        }
    }
    
    public void alpha(){
        
        aph = (double)porcentaje/100;
        aph = (double)(1-aph)/2;
        JOptionPane.showMessageDialog(null,"Ingresar cordenadas de "+aph+"\n"+
                       "En la tabla de distribucion normal");
        String col = JOptionPane.showInputDialog("Ingresa el valor de la columna");
        String fil = JOptionPane.showInputDialog("Ingresa el valor de la fila");
        
        equivalente = Double.parseDouble(col)+Double.parseDouble(fil);
    }
    
    public boolean verificar(){
     
        return Zo<equivalente;
    
    }

    
    

}
