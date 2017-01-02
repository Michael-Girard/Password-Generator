package passGen;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.image.ImageView;


public class Pass extends Application {
	int inputLength = 0; // To store the integer value of the password length
	private TextField tfPassLength = new TextField();
		
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image lock = new Image(getClass().getResourceAsStream("../resources/icons/ic_lock.png"));
		Image copy = new Image(getClass().getResourceAsStream("../resources/icons/ic_copy.png"));
		TextField tfPassword = new TextField();
		tfPassword.setEditable(false);
		Button btGenerate = new Button("Generate");
		Button btCopy = new Button("", new ImageView(copy));
		
		CheckBox chSpecChar = new CheckBox("Special Characters");
		CheckBox chInWords = new CheckBox("Include Words");
		CheckBox chWordOnly = new CheckBox("Words Only");
		
		VBox checkList = new VBox(8);
		checkList.getChildren().addAll(chSpecChar, chInWords, chWordOnly);
		
		HBox PassLengthBox = new HBox(8), PasswordBox = new HBox(8);
		PassLengthBox.getChildren().addAll(new Label("Password Length:"), tfPassLength);
		PassLengthBox.setAlignment(Pos.CENTER_RIGHT);
		PasswordBox.getChildren().addAll(new Label("Password:"), tfPassword);
		PasswordBox.setAlignment(Pos.CENTER_RIGHT);

		GridPane gPane = new GridPane();
		gPane.add(PassLengthBox, 0, 0); //... col, row);
		gPane.add(PasswordBox, 0, 1);
		gPane.add(btCopy, 1, 1);
		gPane.add(checkList, 0, 2);
		gPane.add(btGenerate, 2, 3);
		
		btCopy.setDisable(true);
		btGenerate.setDisable(true);
		tfPassLength.setOnKeyReleased(e ->{
			try {
		        // Get the password length from the text field
				if(Integer.parseInt(tfPassLength.getText()) >= 4){
					tfPassLength.setStyle("");
					inputLength = Integer.parseInt(tfPassLength.getText());
			        btGenerate.setDisable(false);
				}
				else if(Integer.parseInt(tfPassLength.getText()) < 4 || tfPassLength.getText().equals("")){
					btGenerate.setDisable(true);
					tfPassLength.setStyle("-fx-control-inner-background:#ffcdd2");
				}
		    }
			catch (NumberFormatException ex) {
			    // generic exception handling
			    ex.printStackTrace();
			} 
		});
		
		chSpecChar.setOnAction(e -> { 
			if(chSpecChar.isSelected() == true){
				chWordOnly.setDisable(true);
			}
			else if(chSpecChar.isSelected() == false && chInWords.isSelected() == false){
				chWordOnly.setDisable(false);
			}
		});
		chInWords.setOnAction(e -> { 
			if(chInWords.isSelected() == true){ 
				chWordOnly.setDisable(true);
			}
			else if(chSpecChar.isSelected() == false && chInWords.isSelected() == false){
				chWordOnly.setDisable(false);
			}
		});
		chWordOnly.setOnAction(e -> { 
			if(chWordOnly.isSelected() == true){ 
				chSpecChar.setDisable(true); 
				chInWords.setDisable(true);
			}
			else{ 
				chSpecChar.setDisable(false); 
				chInWords.setDisable(false);
			}
		});
		
		btGenerate.setOnAction(e -> {
			
			new Password(inputLength);
			tfPassword.setText(Password.getPassword());
			btCopy.setDisable(false);
			
		});
		
		btCopy.setOnAction(e -> Password.getCopy());
		
		//gPane.setGridLinesVisible(true);// Debugging
		
		AnchorPane mainPane = new AnchorPane();
		mainPane.setTopAnchor(gPane, 10.0);
		mainPane.setLeftAnchor(gPane, 10.0);
		mainPane.setRightAnchor(gPane, 10.0);
		mainPane.setRightAnchor(gPane, 10.0);
		mainPane.getChildren().add(gPane);
		
		Scene scene = new Scene(mainPane, 370, 151); // w x h
		primaryStage.setTitle("Password Generator"); // Set the stage(window) title
		primaryStage.getIcons().add(lock);
		scene.getStylesheets().add("/resources/style.css");
		primaryStage.setScene(scene); // Place the scene in the stage(window)
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage(window)
		
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	
	public static class Password {
		final static Clipboard clipboard = Clipboard.getSystemClipboard();
        final static ClipboardContent content = new ClipboardContent();
		CharGen characters = new CharGen();
		WordGen words = new WordGen();
		private  static String password;
		
		Password(){	
		}
		
		Password(int length){
			characters.setLength(length);
			setPassword(characters.getSomeCharacters());
		}
		
		public static String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
			
		}
		
		public static void getCopy() {
	        content.putString(getPassword());
	        clipboard.setContent(content);
		}
	}
}