
/**
 * Created by Hugo Teixeira Mafra <hugorc10@hotmail.com> on 14/07/2018. Last modification on 19/07/2018.
 * <p>
 * Enrollment number: 201611540
 * <p>
 * Encoder it is a software that simulates a physical layer encoder.
 * <p>
 * Encoder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Encoder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Encoder.  If not, see <http://www.gnu.org/licenses/>.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ScreenView;

public class Principal extends Application {

    public static void main(String[] args) {
        Application.launch(Principal.class);
    }

    @Override
    public void start(Stage myStage) throws Exception {
        ScreenView screenView = new ScreenView();

        Scene scene = new Scene(screenView.crateContent());
        myStage.setScene(scene);
        myStage.setTitle("Physical Layer Encoder");
        myStage.setResizable(false);
        myStage.setMaximized(true);

        myStage.show();
    }
}
