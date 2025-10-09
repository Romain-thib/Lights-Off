/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2025 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * La classe HelloController illustre le fonctionnement du contrôleur associé à une vue.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class LightsOffController {
	
	Random r = new Random();
	
	@FXML
	private GridPane lightGrid;
	
	private GameGrid grille = new  GameGrid();
	
	private Button[][] lightButtons = new Button[5][5];

	int nbAleatoire = r.nextInt(1,3);
	
	private void activerGrille() {
		lightGrid.setDisable(false);
		while (nbAleatoire > 0) {
			nbAleatoire --;
			grille.switchAt(r.nextInt(5), r.nextInt(5));
		}
	}
	
	@FXML
	void initialize() {
	    for (Node child : lightGrid.getChildren()) {
	        // On récupère la ligne où le bouton se trouve.
	        Integer row = GridPane.getRowIndex(child);
	        if (row == null) {
	            row = 0;
	        }

	        // On récupère la colonne où le bouton se trouve.
	        Integer column = GridPane.getColumnIndex(child);
	        if (column == null) {
	            column = 0;
	        }

	        if (child instanceof Button button) {
	            lightButtons[row][column] = button;
	        }
	    }
	    grille.init();
	    activerGrille();
	    updateGrid();
	    
	}

	private void updateGrid() {
		for(int i=0; i < lightButtons.length; i++) {
			for (int y = 0; y < lightButtons[0].length; y++) {
				if(grille.isOn(i, y)) {
					lightButtons[i][y].setStyle("-fx-background-color: white");
				}
				else {
					lightButtons[i][y].setStyle("-fx-background-color: black");
				}
			}
		}
	}
	
	
    /**
     * Cette méthode exécute une action lorsque l'utilisateur clique sur le bouton de la fenêtre.
     * Le lien avec le bouton de l'application sera réalisé automatiquement par JavaFX grâce à
     * l'annotation {@link FXML}.
     */
    @FXML
    private void onClick(ActionEvent event) {
        // Ici, on va simplement afficher un message dans le label de l'application.
    	Button button = (Button) event.getSource();
    	Integer row = GridPane.getRowIndex(button);
        if (row == null) {
            row = 0;
        }
        Integer column = GridPane.getColumnIndex(button);
        if (column == null) {
            column = 0;
        }
        grille.switchAt(row, column);
        updateGrid();
        
        if(grille.isOff()) {
        	lightGrid.setDisable(true);
        }
    }
    
}
