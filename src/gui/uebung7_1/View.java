package gui.uebung7_1;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class View
{
    private Presenter presenter;

    private GridPane pane;

    private TextField loginName;

    private PasswordField password;

    private Label status;

    private String oldName = "old";

    String name = null;

    private int fails = 0;

    private TextArea display;

    private Button listeLoeschen, login;

    public View(Presenter presenter)
    {
        this.presenter = presenter;
    }

    public void initView()
    {
        initDisplay();
        initStatus();
        pane = new GridPane();
        initListeLoeschen();
        Insets insets = new Insets(5);
        pane.setPadding(insets);
        pane.setHgap(2);
        pane.setVgap(5);
        pane.add(new Label("Login-Kennung:"), 0, 0);
        loginName = new TextField();
        pane.add(loginName, 1, 0);
        pane.add(new Label("Passwort:"), 0, 1);
        password = new PasswordField();
        pane.add(password, 1, 1);
        login = new Button("Login");
        pane.add(login, 0, 2, 2, 1);
        status = new Label();
        pane.add(status, 0, 3, 2, 1);
        pane.add(display, 0, 4, 3, 1);

        EventHandler<ActionEvent> h = e -> handle();
        loginName.setOnAction(h);
        password.setOnAction(h);
        login.setOnAction(h);
        listeLoeschen.setOnAction(e ->
        {
            handleDelete();
        });
    }

    private void handle()
    {
        this.name = loginName.getText();
        String pw = password.getText();
        name = name.trim();
        pw = pw.trim();
        presenter.login(name, pw);
    }

    public Pane getUI()
    {
        return pane;
    }

    public void handleFalseLoginProtocol()
    {
        System.out.println("Name " + name);
        System.out.println("OldName " + oldName);
        if (loginName != null && !loginName.getText().isEmpty() && name.equals(oldName))
        {
            this.setFails(this.getFails() + 1);

        }
        else
        {
            this.setFails(0);
        }
        display.setText(display.getText().concat(LocalDateTime.now() + " " + loginName.getText() + " hat sich efolglos versucht anzumelden.\n"));
        oldName = name;
    }

    public void toMuchFails()
    {
        status.setTextFill(Color.RED);
        status.setUnderline(true);
        status.setText("Warnung: Wiederholter Fehlversuch f�r " + loginName.getText());
    }

    public void handleOkayLoginProtocol()
    {
        if (loginName != null && !loginName.getText().isEmpty())
        {
            display.setText(display.getText().concat(LocalDateTime.now() + " " + loginName.getText() + " hat sich efolgreich angemeldet.\n"));
            this.setFails(0);
        }

    }

    public void initDisplay()
    {
        display = new TextArea();
        display.setEditable(false);
    }

    public void initListeLoeschen()
    {
        listeLoeschen = new Button("Liste l�schen");
        pane.add(listeLoeschen, 0, 5);
    }

    public void initStatus()
    {
        status = new Label();
    }

    public void handleDelete()
    {
        display.clear();
    }

    public void resetInput()
    {
        loginName.setText("");
        password.setText("");
    }

    public void showOkayMessage()
    {
        status.setText("Login erfolgreich.");
    }

    public void showInputError()
    {
        status.setText("Keine Login-Kennung angegeben.");
    }

    public void showLoginError()
    {
        status.setText("Login-Kennung bzw. Passwort falsch.");
    }

    public int getFails()
    {
        return fails;
    }

    public void setFails(int fails)
    {
        this.fails = fails;
    }
}
