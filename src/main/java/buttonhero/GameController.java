package buttonhero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by William on 12/5/2015.
 */
public class GameController implements Initializable {
    private int bpm;
    private int offset;
    private boolean snapToBeat;
    private int snapBeatDenominator;
    private List<List<HitObject>> hitObjectArrays;

    @FXML
    private Pane mainPane;
    private Canvas mainCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Start");
        hitObjectArrays = new ArrayList<>(4);
        if (new File("colors.txt").isFile()) {
            try (Scanner scanner = new Scanner(new File("colors.txt"))){
                bpm = scanner.nextInt();
                offset = Integer.parseInt(scanner.next().substring(1));
                snapToBeat = scanner.nextBoolean();
                if (snapToBeat) snapBeatDenominator = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    hitObjectArrays.add(new ArrayList<>());
                    int index = scanner.nextInt();
                    if (snapToBeat) {
                        while (!scanner.hasNextInt() && scanner.hasNext()) {
                            String str = scanner.next();
                            if (str.contains("d"))
                                hitObjectArrays.get(index - 1).add(new HitObject(Integer.parseInt(str.substring(0, str.indexOf("n"))),
                                        Integer.parseInt(str.substring(str.indexOf("n") + 1, str.indexOf("d"))), snapBeatDenominator,
                                        str.substring(str.indexOf("d") + 1)));
                            else
                                hitObjectArrays.get(index - 1).add(new HitObject(Integer.parseInt(str.substring(0, str.indexOf("n"))),
                                        Integer.parseInt(str.substring(str.indexOf("n") + 1)), snapBeatDenominator));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }
    }
}
