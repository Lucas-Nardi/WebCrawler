package webcrawler;

public class Observador implements Observer{

    private boolean temUrls = false;
    private int num = 1;
    
    @Override
    public void update(Object o) {
        String url;
        if(o instanceof Buffer){            
            Buffer bu = (Buffer)o;
            url = bu.getFila().peek();            
            if(url != null){
                temUrls = true;
                url = bu.getFila().remove();
                ThreadImgBaixada thread = new ThreadImgBaixada(url,num);
                thread.start();
                num ++;
            }
        }else{
            temUrls = false;
        }
    }
    
}
