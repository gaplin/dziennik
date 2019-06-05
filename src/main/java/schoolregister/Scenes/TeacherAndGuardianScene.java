package schoolregister.Scenes;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import schoolregister.DataType.Absence;
import schoolregister.DataType.Grade;
import schoolregister.DataType.Group;
import schoolregister.DataType.Person;
import schoolregister.Database;
import schoolregister.Factory.SceneFactory;
import schoolregister.Factory.ViewFactory;
import schoolregister.Wrapper.GroupWrapper;
import schoolregister.Wrapper.IntegerWrapper;
import schoolregister.Wrapper.PersonWrapper;

import java.util.ArrayList;

import static schoolregister.Main.*;

public class TeacherAndGuardianScene {
    private static ViewFactory viewFactory = ViewFactory.getInstance();
    public static Scene newTeacherScene(int teacherId) {
        TableView<Group> groups = viewFactory.getGroupsFor(teacherId);
        TableView<Grade> grades = viewFactory.getGrades();
        TableView<Person> students = viewFactory.getStudents();
        TableView<Absence> absences = viewFactory.getAbsences();

        GroupWrapper currentGroup = new GroupWrapper();
        PersonWrapper currentStudent = new PersonWrapper();
        IntegerWrapper currentType = new IntegerWrapper(0);

        GridPane grid = createGrid();
        Button backButton = new Button("Back");
        Button lessonsButton = new Button("Lessons");
        Button gradesButton = new Button("Grades");
        Button absencesButton = new Button("absences");

        Label groupsLabel = new Label("Groups");
        groupsLabel.setFont(new Font("Arial", 20));
        Label studentsLabel = new Label("Students");
        studentsLabel.setFont(new Font("Arial", 20));
        Label gradesLabel = new Label("Grades");
        gradesLabel.setFont(new Font("Arial", 20));
        Label absencesLabel = new Label("Absences");
        absencesLabel.setFont(new Font("Arial", 20));

        grid.add(backButton, 1, 21);
        grid.add(lessonsButton, 1, 20);
        grid.add(gradesButton, 1, 22);
        grid.add(absencesButton, 1, 23);
        grid.add(groupsLabel, 0, 5);
        grid.add(groups, 0, 6);
        grid.add(studentsLabel, 10, 5);
        grid.add(students, 10, 6);
        grid.add(gradesLabel, 20, 5);
        grid.add(grades, 20, 6);
        grid.add(absencesLabel, 20, 5);
        grid.add(absences, 20, 6);

        lessonsButton.setOnAction(e -> {
            lessonsTableScene = SceneFactory.getInstance().createLessonTableSceneForTeacher(teacherId);
            window.setScene(lessonsTableScene);
        });

        groups.getSelectionModel().selectedItemProperty().addListener((observableValue, group, t1) -> {
            currentGroup.setGroup(t1);
            if(t1 != null) {
                students.setItems(FXCollections.observableArrayList(Database.getInstance().getStudentsFor(t1.getId())));
            }
            else{
                students.getItems().clear();
            }
        });

        students.getSelectionModel().selectedItemProperty().addListener((observableValue, person, t1) -> {
            currentStudent.setPerson(t1);
            if(t1 != null){
                switch (currentType.getValue()){
                    case 0:
                        grades.setItems(FXCollections.observableArrayList(Database.getInstance().getGrades(t1.getId(), currentGroup.getGroup().getId())));
                        break;
                    case 1:
                        absences.setItems(FXCollections.observableArrayList(Database.getInstance().getAbsences(t1.getId(), currentGroup.getGroup().getId(), currentGroup.getGroup().getSubjectId())));
                        break;
                }
            }
            else{
                switch(currentType.getValue()){
                    case 0:
                        grades.getItems().clear();
                        break;
                    case 1:
                        absences.getItems().clear();
                }
            }
        });

        absencesLabel.setVisible(false);
        absences.setVisible(false);

        backButton.setOnAction(actionEvent -> window.setScene(mainScene));
        gradesButton.setOnAction(actionEvent -> {
            if(currentType.getValue() == 0)
                return;
            currentType.setValue(0);

            gradesLabel.setVisible(true);
            grades.setVisible(true);
            if(currentStudent.getPerson() != null && currentGroup.getGroup() != null){
                grades.setItems(FXCollections.observableArrayList(Database.getInstance().getGrades(currentStudent.getPerson().getId(), currentGroup.getGroup().getId())));
            }

            absencesLabel.setVisible(false);
            absences.setVisible(false);
        });

        absencesButton.setOnAction(actionEvent -> {
            if(currentType.getValue() == 1)
                return;
            currentType.setValue(1);

            absencesLabel.setVisible(true);
            absences.setVisible(true);
            if(currentStudent.getPerson() != null && currentGroup.getGroup() != null){
                absences.setItems(FXCollections.observableArrayList(Database.getInstance().getAbsences(currentStudent.getPerson().getId(), currentGroup.getGroup().getId(), currentGroup.getGroup().getSubjectId())));
            }

            gradesLabel.setVisible(false);
            grades.setVisible(false);
        });
        return new Scene(grid, 1280, 720);
    }


    public static Scene newGuardianScene(int guardianId) {
        currentIndex = 0;
        guardianKids = new ArrayList<>(Database.getInstance().getGuardianKids(guardianId));
        studentsScenes = new ArrayList<>();
        GridPane grid = createGrid();

        Button backButton = new Button("Back");
            grid.add(backButton, 1, 21);

            backButton.setOnAction(actionEvent -> window.setScene(mainScene));

            if(guardianKids.isEmpty()) {
            studentsScenes.add(new Scene(grid, 1280, 720));
        }

            for(Integer i : guardianKids){
            studentsScenes.add(SceneFactory.getInstance().createStudentScene(i, true));
            currentIndex++;
        }
        currentIndex = 0;
        studentScene = studentsScenes.get(currentIndex);
        return studentScene;
    }

    private static GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }
}
