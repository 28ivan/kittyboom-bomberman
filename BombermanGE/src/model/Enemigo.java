package model;

import java.util.Timer;
import java.util.TimerTask;

public class Enemigo {
    private int i, j;
    private boolean vivo;
    private boolean menosDe1s;
    private Timer timer = null;
    private int tiempoMovimiento;
    
    public Enemigo(int i, int j) {
        this.i = i;
        this.j = j;
        this.vivo = true;
        this.menosDe1s = false;
        this.tiempoMovimiento = 1000;
    }

    public void morir() {
        this.vivo = false;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void empezarTimer() {
        if (!menosDe1s) {
            menosDe1s = true;
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    int i = getI();
                    int j = getJ();
                    
                    Tablero.getTablero().moverEnemigo(i, j);
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, tiempoMovimiento);
        }
    }
}
