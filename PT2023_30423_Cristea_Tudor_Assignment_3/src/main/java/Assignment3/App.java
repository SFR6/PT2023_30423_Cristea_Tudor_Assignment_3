package Assignment3;

import Assignment3.businessLayer.MainBLL;
import Assignment3.presentationLayer.MainView;

/**
 * This class represents the main class of this application. It contains the main method of the application
 * @author Tudor Cristea
 */
public class App
{
    /**
     * This is the main method of the Main class. It creates the main view of the application as well as the business
     * logic behind it
     * @param args the arguments of the main method
     */
    public static void main(String[] args)
    {
        MainView mainView = new MainView();

        MainBLL mainBLL = new MainBLL(mainView);
    }
}