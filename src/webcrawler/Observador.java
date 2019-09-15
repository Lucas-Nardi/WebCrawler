package webcrawler;

public class Observador implements Observer {

    private int num = 1;

    @Override
    public void update(String imagem) {
        ThreadImgBaixada thread = new ThreadImgBaixada(imagem, num);
        thread.start();
        num++;
    }
}

