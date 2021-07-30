import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Ui implements Initializable {
    @FXML
    Label result, display, operator;
    public Button decimal, equal_to, delete;

    @FXML
    void click(ActionEvent event){
        Button btn = (Button) event.getSource();
        if (display.getText().matches("^\\d*\\.?\\d* [÷×+\\-] \\d*\\.?\\d*$"))
            clear();
//        if ( t > 19) {
//            setFontSize(result, 18 - ((t-19)*0.8));  TODO
//        }
        result.setText(result.getText().concat(btn.getText()));
    }

    @FXML
    void click_operator(ActionEvent event){
        if (result.getText().equals("NaN") || result.getText().equals("Infinity")){
            clear();
            return; }
        if ( ! operator.getText().isEmpty())
            return;
        if (result.getText().isEmpty() || result.getText().equals("."))
            return;
        Button btn = (Button) event.getSource();
        String operator_ = btn.getText();
        display.setText(result.getText());
        operator.setText(operator_);
        result.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decimal.setOnAction(event -> {
            if (operator.getText().isEmpty())
                display.setText("");
            if (result.getText().equals("NaN") || result.getText().equals("Infinity"))
                result.setText("");
            String current_number = result.getText();
            result.setText(current_number.contains(".") ? current_number : current_number.concat("."));
        });

        equal_to.setOnAction(event->{
            String display_ = display.getText();
            String result_ = result.getText();
            String operator_ = operator.getText();

            if (result_.isEmpty() || display_.isEmpty() || operator_.isEmpty() || result_.equals(".")) return;

            char op = operator_.charAt(0);
            String operation = display_.concat(" ").concat(operator_).concat(" ").concat(result_);
            Double num_one = new Double(display_);
            Double num_two = new Double(result_);
            double ans = op == '+' ? num_one + num_two : op == '-' ? num_one - num_two : op == '÷' ? num_one / num_two : num_one * num_two;
            String answer = String.valueOf(ans);

            display.setText(operation);
            result.setText(answer.endsWith(".0") ? answer.substring(0, answer.length()-2) : answer);
            operator.setText("");
        });

        delete.setOnAction(event->{
            String txt = result.getText();
            if (txt.equals("NaN") || txt.equals("Infinty")){
                clear();
                return; }
            if (txt.isEmpty()) return;
            del_clicked ++;

            if(!bool_del_clicked){
                PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
                pause.play();
                pause.setOnFinished(event1 -> {
                    if (del_clicked > 1)
                        clear();
                    else
                        result.setText(txt.substring(0, txt.length()- 1));
                    del_clicked = 0;
                    bool_del_clicked = false;
                });
        }
            bool_del_clicked = true;
        }
        );
    }

    private static byte del_clicked = 0;
    private static boolean bool_del_clicked = false;

    private void clear(){
        Label[] labels = new Label[]{result, display, operator};
        for (Label label: labels)
            label.setText("");
    }
}
