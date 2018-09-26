package View;

import javax.swing.JPanel;

public interface ViewObservable {
    
    public void notifyAllObserver(JPanel newPanel);
    
    public void addObserver(ViewObserver newObserver);
    
    public void remove(ViewObserver toBeRemoved);
}
