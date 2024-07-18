package cronometro;


public class Dados {
 private String numEquipe;
 private String pilotoNome;
 private String carCor;
 private String cronometro;
 private String total;
 

 public Dados(String numEquipe, String pilotoNome,String carroCor, String quantMulheres, String total){
  this.numEquipe=numEquipe;
  this.pilotoNome = pilotoNome;
  this.carCor = carCor;
  this.cronometro = cronometro;
  this.total = total;
 }
 public String getNumEquipe(){
  return this.numEquipe;
 }
 public String getPilotoNome(){
  return this.pilotoNome;
 }
 public String getCarCor(){
  return this.carCor;
 }
 public String getCronometro(){
  return this.cronometro;
 }

    String getObs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}
