package webcrawler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Buffer implements Observable{

    private Queue <String> fila = new LinkedList<>();  
    private Observer observer;
    private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    
    public void adicionar(String url){
        //fila.add(url); 
        queue.add(url);
        notifyObserver();
        
    }
    
    public ConcurrentLinkedQueue<String> getFila() {
        return queue;
    }

    public void setFila(ConcurrentLinkedQueue<String> fila) {
        this.queue = fila;
    }

    @Override
    public void attach(Observer o) {
        this.observer = o;
    } 
    public void dettach(Observer o) {
        this.observer = o;
    }

    @Override
    public void notifyObserver() {
        String imagem = null;
        if(queue.peek() != null){
            imagem = queue.remove();
        }  
        this.observer.update(imagem);
    }
}
