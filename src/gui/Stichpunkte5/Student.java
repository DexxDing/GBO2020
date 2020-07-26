package gui.Stichpunkte5;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student
{
    private SimpleStringProperty nameProperty = new SimpleStringProperty();

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

    private SimpleIntegerProperty semesterProperty = new SimpleIntegerProperty();

    private SimpleIntegerProperty matnrProperty = new SimpleIntegerProperty();

    private ObservableList<Student> studentOL = FXCollections.observableArrayList();

    public Student(String name, Integer semester, Integer matnr)
    {
        super();
        this.nameProperty.setValue(name);
        this.semesterProperty.setValue(semester);
        this.matnrProperty.setValue(matnr);
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
}
