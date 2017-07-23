import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import java.util.Random;

public class Screen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

  private static Progress progress = null;
  private static NodeController nc = null;
  private JPanel pPaint;
  private JButton btnStart;

  public Screen() {
    setLayout(null);

    // 左側パネル
    pPaint = new JPanel();
    pPaint.addMouseListener( this );
    pPaint.addMouseMotionListener( this );
    pPaint.setBounds( 0, 0, 1000, 720 );
    pPaint.setBackground( new Color(225, 225, 225) );
    this.add( pPaint );

    // 右上パネル1
    JPanel pMenu = new JPanel();
    pMenu.setBounds( 1005, 5, 265, 40 );
    pMenu.setLayout( new GridLayout(1, 2) );
    pMenu.setBackground( new Color(175, 220, 220) );

    btnStart = new JButton("Start");
    btnStart.setFont( new Font("MS ゴシック", Font.BOLD, 18) );
    btnStart.addActionListener( this );
    pMenu.add( btnStart );

    JButton btnStop = new JButton("Stop");
    btnStop.setFont( new Font("MS ゴシック", Font.BOLD, 18) );
    btnStop.addActionListener( this );
    pMenu.add( btnStop );
    this.add( pMenu );

    // 右上パネル2
    JPanel pControl = new JPanel();
    pControl.setBounds( 1005, 50, 265, 40 );
    pControl.setLayout( new GridLayout(1, 4) );
    pControl.setBackground( new Color(175, 220, 220) );

    JButton btnNodeAdd = new JButton("Add");
    btnNodeAdd.setFont( new Font("MS ゴシック", Font.BOLD, 18) );
    btnNodeAdd.addActionListener( this );
    pControl.add( btnNodeAdd );

    JButton btnNodeRemove = new JButton("Remove");
    btnNodeRemove.setFont( new Font("MS ゴシック", Font.BOLD, 18) );
    btnNodeRemove.addActionListener( this );
    pControl.add( btnNodeRemove );
    this.add(pControl);

    // 右下パネル
    JPanel pPacket = new JPanel();
    pPacket.setBounds( 1005, 500, 265, 40);
    pPacket.setLayout( new GridLayout(1, 1) );
    pPacket.setBackground( new Color(175, 220, 220) );
    this.add(pPacket);
  }

  // インスタンス
  public void setNodeController(NodeController nc) {
    this.nc = nc;
  }
  public void setProgress(Progress progress) {
    this.progress = progress;
  }

  public void render() {
    Image back = createImage(1000, 720);
    Graphics buffer = back.getGraphics();
    buffer.setColor( new Color(225, 225, 225) );
    buffer.fillRect( 0, 0, 1000, 720 );
    buffer.setColor( Color.black );
    this.renderNodes(buffer);
    getGraphics().drawImage(back, 0, 0, this);
  }

  private void renderNodes(Graphics buffer) {
    ArrayList<Node> nodeList = nc.getNodes();
    for(Node node : nodeList) {
      int[] pos = node.getPosition();

      // 経過時間描画
      double dNowTime = node.getNowtime() / 3600.0;
      int arcTime = (int)(-360.0 * dNowTime);

      if(node.isOperation()) {
        buffer.setColor( Color.red );
      } else {
        buffer.setColor( Color.black );
      }
      buffer.fillArc(pos[0]-10, pos[1]-10, 20, 20, 90, arcTime);
      buffer.setColor( new Color(225, 225, 225) );
      buffer.fillOval(pos[0]-5, pos[1]-5, 10, 10);
      buffer.setColor( Color.black );

      // ノード描画
      buffer.drawRect(pos[0]-2, pos[1]-2, 4, 4);

      // 電波描画
      if(node.isOperation()) {
        int cnt = node.getCount();
        buffer.drawOval(pos[0]-cnt/2, pos[1]-cnt/2, cnt, cnt);
      }

      // 線描画
      for(Node _node : nodeList) {
        if(node == _node) continue;
        if(nc.getDistance(node, _node) <= 100.0) {
          int[] _pos = _node.getPosition();
          buffer.drawLine(pos[0], pos[1], _pos[0], _pos[1]);
        }
      }

      // ノード名
      buffer.drawString(node.getName(), pos[0]-5, pos[1]+30);

      // 終了
    }
  }

  private String mode = "None";
  public void actionPerformed( ActionEvent ae ) {
    String buttonName = ae.getActionCommand();
    switch(buttonName) {
      case "Start":
        this.progress.start();
        this.btnStart.setEnabled(false);
        break;
      case "Stop":
        this.progress.pause();
        break;
      case "Add":
        this.mode = "Add";
        break;
      case "Remove":
        this.mode = "Remove";
        break;
    }
  }
  public void mousePressed( MouseEvent e ) {
    e.consume();
    switch(this.mode) {
      case "Add":
        nc.addNode(e.getX(), e.getY());
        this.render();
        break;
      case "Remove":
        nc.removeNode(e.getX(), e.getY());
        this.render();
        break;
      default:
        break;
    }

  }
  public void mouseDragged( MouseEvent e ) {}
  public void mouseClicked( MouseEvent e ) {}
  public void mouseMoved( MouseEvent e ) {}
  public void mouseReleased( MouseEvent e ) {}
  public void mouseEntered( MouseEvent e ) {}
  public void mouseExited( MouseEvent e ) {}
}
