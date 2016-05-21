package projects.music.midi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;

public class DeviceSetUpDialog {
	
	public static void show () {
	List<String> choices = new ArrayList<>();
	choices.add("a");
	choices.add("b");
	choices.add("c");

	ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
	dialog.setTitle("Choice Dialog");
	dialog.setHeaderText("Look, a Choice Dialog");
	dialog.setContentText("Choose your letter:");

	// Traditional way to get the response value.
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
	    System.out.println("Your choice: " + result.get());
	}

	// The Java 8 way to get the response value (with lambda expression).
	result.ifPresent(letter -> System.out.println("Your choice: " + letter));

}
}
