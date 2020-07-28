package gui.tableview;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

public class Student
{
    private SimpleStringProperty nameProperty = new SimpleStringProperty();

    private SimpleIntegerProperty semesterProperty = new SimpleIntegerProperty();

    private SimpleIntegerProperty matnrProperty = new SimpleIntegerProperty();

    private SimpleBooleanProperty graduateProperty = new SimpleBooleanProperty();

    private ObservableList<Student> studentOL = FXCollections.observableArrayList();

    private CheckBox isGraduated;

    public Student(String name, Integer semester, Integer matnr, boolean isGraduated)
    {
        super();
        this.nameProperty.setValue(name);

        this.semesterProperty.setValue(semester);
        this.matnrProperty.setValue(matnr);
        this.isGraduated = new CheckBox();
        this.graduateProperty.setValue(isGraduated);
        this.isGraduated.setSelected(graduateProperty.getValue());
        studentOL.add(this);
    }

    public ObservableList<Student> getStudentOL()
    {
        return studentOL;
    }

    public void setStudentOL(ObservableList<Student> studentOL)
    {
        this.studentOL = studentOL;
    }

    public CheckBox getIsGraduated()
    {
        return isGraduated;
    }

    public SimpleStringProperty getNameProperty()
    {
        return nameProperty;
    }

    public void setNameProperty(SimpleStringProperty nameProperty)
    {
        this.nameProperty = nameProperty;
    }

    public SimpleIntegerProperty getSemesterProperty()
    {
        return semesterProperty;
    }

    public void setSemesterProperty(SimpleIntegerProperty semesterProperty)
    {
        this.semesterProperty = semesterProperty;
    }

    public SimpleIntegerProperty getMatnrProperty()
    {
        return matnrProperty;
    }

    public void setMatnrProperty(SimpleIntegerProperty matnrProperty)
    {
        this.matnrProperty = matnrProperty;
    }

    public String getName()
    {
        return nameProperty.getValue();
    }

    public Integer getSemester()
    {
        return semesterProperty.getValue();
    }

    public Integer getMatnr()
    {
        return matnrProperty.getValue();
    }

    public void setMatnr(Integer value)
    {
        getMatnrProperty().setValue(value);
    }

    public void setName(String value)
    {
        getNameProperty().setValue(value);
    }

    public void setSemester(Integer value)
    {
        getSemesterProperty().setValue(value);
    }

    public SimpleBooleanProperty getGraduateProperty()
    {
        return graduateProperty;
    }

    public void setGraduateProperty(boolean value)
    {
        this.graduateProperty.setValue(value);
        this.isGraduated.setSelected(graduateProperty.getValue());
    }
}
