package schoolregister;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class LessonsTable extends Scene {
    private final static StackPane root = new StackPane();
    public LessonsTable(int w, int h) {
        super(root, w,h);
        load();
    }


    public static void load() {
        GridPane gridPane = new GridPane();

        Lesson[][] lessons = Database.getInstance().getLessonsAssignedTo(87);
        for(int i=0;i<lessons.length;i++) {
            for(int j=0;j<lessons[i].length;j++) {
                if(lessons[i][j] != null) {
                    gridPane.add(new Label(lessons[i][j].toString()), i, j, 1, 1);
                }
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(100);
        gridPane.setVgap(60);
        root.getChildren().add(gridPane);
    }

}