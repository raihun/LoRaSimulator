import java.awt.*;
import javax.swing.*;

public class Progress extends Thread {

  private static Screen screen = null;
  private static NodeController nc = null;
  private boolean running = false;
  private boolean pause = false;

  // コンストラクタ
  public Progress() {
    this.running = true;
  }

  // インスタンス
  public void setScreen(Screen screen) {
    this.screen = screen;
  }
  public void setNodeController(NodeController nc) {
    this.nc = nc;
  }

  // スレッド操作
  public void finish() {
    this.running = false;
  }
  public synchronized void pause() {
    this.pause = !this.pause;
    if(!this.pause) notify();
  }

  @Override
  public void run() {
    while(this.running) {
      this.screen.render();
      this.nc.forwardNowtime();

      try {
        Thread.sleep(1);
        synchronized (this) {
          if(this.pause) wait();
        }
      } catch(InterruptedException e) {}
    }
  }

}
