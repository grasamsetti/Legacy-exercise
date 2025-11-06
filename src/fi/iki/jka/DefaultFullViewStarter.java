package fi.iki.jka;

import javax.swing.JOptionPane;


public class DefaultFullViewStarter implements FullViewStarter {
    @Override
    public void startFullView(JPhotoFrame frame, JPhotoCollection photos, JPhotoList list, int selectedIndex) {
        if (selectedIndex>=0) {
            JPhoto photo = (JPhoto)photos.getElementAt(selectedIndex);
            if (photo.getAlbumLink()!=null) {
                // It is a link to another album, start new instance
                String newFile = photo.getFullAlbumLink();
                Object existing = JPhotoFrame.allFrames.get(newFile);
                if (existing instanceof JPhotoFrame) {
                    ((JPhotoFrame)existing).setVisible(true);
                } else {
                    try {
                        JPhotoFrame newFrame = new JPhotoFrame(newFile, new JPhotoCollection(newFile));
                        newFrame.setVisible(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Cannot open "+newFile,
                                                      JPhotoFrame.APP_NAME, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else {
                JPhotoShow show = new JPhotoShow(photos, 0, list);
                show.setVisible(true);
            }
        }
        else
            JOptionPane.showMessageDialog(frame, "Select a photo to view!",
                                          JPhotoFrame.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }
}

