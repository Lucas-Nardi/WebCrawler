package webcrawler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Buffer implements Observable{

    private Queue <String> fila = new LinkedList<>();
    private ArrayList <Observer> observers = new ArrayList<>();
    
    
    public void adicionar(String url){
        fila.add(url); 
        notifyObserver();
    }
    
    public Queue<String> getFila() {
        return fila;
    }

    public void setFila(Queue<String> fila) {
        this.fila = fila;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void dettach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        
        int i;
        for(i=0; i < observers.size(); i++){
            
            observers.get(i).update(this);
        }
    }
}
