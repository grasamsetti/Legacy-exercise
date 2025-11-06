package fi.iki.jka;


public class DefaultSlideShowStarter implements SlideShowStarter {
    @Override
    public void start(JPhotoCollection photos, int delayMillis, JPhotoList list) {
        JPhotoShow show = new JPhotoShow(photos, delayMillis, list);
        show.setVisible(true);
    }
}

