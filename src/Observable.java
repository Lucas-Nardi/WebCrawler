

interface Observable {
 
    void attach(Observer o);
    void dettach(Observer o);
    void notifyObserver();
    
}
