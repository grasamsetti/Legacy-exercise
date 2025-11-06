package fi.iki.jka;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestJPhotoFrame {

    @Test
    public void testStartSlideShowWhenPhotosExist() throws Exception {
        JPhotoFrame frame = new JPhotoFrame();

        String[] files = {"one.jpg", "two.jpg"};
        JPhotoCollection photos = new JPhotoCollection(files);
        frame.photos = photos;
        frame.list = null;

        final boolean[] called = {false};
        final JPhotoCollection[] passed = new JPhotoCollection[1];
        final int[] delay = new int[1];

        SlideShowStarter fakeStarter = (p, delayMillis, l) -> {
            called[0] = true;
            passed[0] = p;
            delay[0] = delayMillis;
        };

        frame.setSlideShowStarter(fakeStarter);


        frame.startSlideShow();


        assertTrue("SlideShowStarter should be called when photos exist", called[0]);
        assertSame("Photos passed to starter should be the same collection", photos, passed[0]);
        assertEquals("Default delay should be 5000 ms", 5000, delay[0]);
    }

    @Test
    public void testStartSlideShowWhenNoPhotosShowsDialog() throws Exception {
        JPhotoFrame frame = new JPhotoFrame();
        JPhotoCollection photos = new JPhotoCollection();
        frame.photos = photos;

        final StringBuilder captured = new StringBuilder();
        DialogService fakeDialog = new DialogService() {
            @Override
            public void showMessage(java.awt.Component parent, String message, String title, int messageType) {
                captured.append(message);
            }
        };
        frame.setDialogService(fakeDialog);


        frame.startSlideShow();


        assertEquals("No photos to show!", captured.toString());
    }

    @Test
    public void testActionPerformedInvokesStartSlideShow() throws Exception {
        JPhotoFrame frame = new JPhotoFrame();
        String[] files = {"one.jpg"};
        frame.photos = new JPhotoCollection(files);

        frame.list = new JPhotoList(frame.photos, 100);

        final boolean[] called = {false};
        SlideShowStarter fakeStarter = new SlideShowStarter() {
            @Override
            public void start(JPhotoCollection p, int delayMillis, JPhotoList l) {
                called[0] = true;
            }
        };
        frame.setSlideShowStarter(fakeStarter);

        java.awt.event.ActionEvent evt = new java.awt.event.ActionEvent(frame, 0, JPhotoMenu.A_SLIDESHOW);

        assertNotNull("JPhotoList must be set on frame to avoid NPE", frame.list);
        frame.actionPerformed(evt);

        assertTrue("actionPerformed should trigger slideshow start", called[0]);
    }
}
