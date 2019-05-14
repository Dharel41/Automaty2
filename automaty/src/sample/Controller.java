package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller{

    public Canvas canvas;
    public TextField text_field2;
    public TextField text_field3;
    public ComboBox comboBox;
    ObservableList<String> rule;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private Alert alert2 = new Alert(Alert.AlertType.ERROR);
    private Alert alert3 = new Alert(Alert.AlertType.ERROR);
    Principle principle;
    Thread t=new Thread(principle);


    public void start(ActionEvent actionEvent) {
        principle=new Principle(canvas);
        principle.stop=false;

try {
    principle.height = Integer.parseInt(text_field3.getText());
    principle.width= Integer.parseInt(text_field2.getText());
    principle.board =  new int[principle.width][principle.height];
    principle.next_step = new int[principle.width][principle.height];

    if (comboBox.getValue().toString().equals("Oscillator"))
    {
        principle.fill_oscillator();
    }
    if (comboBox.getValue().toString().equals("Glider"))
    {
        principle.fill_glider();
    }
    if (comboBox.getValue().toString().equals("Still life"))
    {
        principle.still_life();
    }
    if (comboBox.getValue().toString().equals("Random"))
    {
        principle.random();
    }

       if(t.isAlive())
        {
        t.stop();
        }

        t=new Thread(principle);
        t.start();


}
catch(NumberFormatException e)
{
    alert.setTitle("Error Dialog");
    alert.setHeaderText("Incorrect size");
    alert.showAndWait();
}
catch(NullPointerException e)
{
    alert2.setTitle("Error Dialog");
    alert2.setHeaderText("You didn't choose pattern");
    alert2.showAndWait();
}

catch(ArrayIndexOutOfBoundsException e)
{
    alert3.setTitle("Error Dialog");
    alert3.setHeaderText("Too small board");
    alert3.showAndWait();
}


    }
    public void configurate(){
        ObservableList<String> rule =
                FXCollections.observableArrayList("Oscillator","Glider","Still life","Random","Set");
        comboBox.setItems(rule);
    }
    public void stop(){
        principle.stop=!(principle.stop);
    }

}
