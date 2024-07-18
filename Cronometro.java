package cronometro;
import java.util.concurrent.TimeUnit;

public class Cronometro {
  
    private long startTime;
    private boolean running;

    public Cronometro() {
        startTime = 0;
        running = false;
    }

    public void iniciar() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void parar() {
        running = false;
    }

   public String getTempoDecorrido() {
    if (!running) {
        return "";
    }

    long tempoAtual = System.currentTimeMillis();
    long tempoDecorrido = tempoAtual - startTime;
    long milissegundos = tempoDecorrido % 1000; // Calcula os milésimos de segundo
    long segundos = TimeUnit.MILLISECONDS.toSeconds(tempoDecorrido) % 60;
    long minutos = TimeUnit.MILLISECONDS.toMinutes(tempoDecorrido) % 60;

    return String.format("%02d:%02d:%03d", minutos, segundos, milissegundos);
   }
}