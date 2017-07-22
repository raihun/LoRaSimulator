import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Main extends JFrame {

  private static Screen screen = new Screen();
  private static Progress progress = new Progress();
  private static NodeController nc = new NodeController();;

  public Main() {
      this.screen.setNodeController(this.nc);
      this.screen.setProgress(this.progress);
      this.progress.setScreen(this.screen);
      this.progress.setNodeController(this.nc);

      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      getContentPane().add( screen );
      setSize( 1280, 720 );
      setTitle( "NO TITLE" );
      setResizable( false );
      setVisible( true );
  }

  public static void main(String[] args) {
    new Main();
  }
}
