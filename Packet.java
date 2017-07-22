public class Packet {

  private int ddst;
  private int dsrc;
  private int ndst;
  private int nsrc;
  private byte[] payload = new byte[50];

  public Packet() {}

  public void setDatalink(int dst, int src) {
    this.ddst = dst;
    this.dsrc = src;
  }

  public void setNetwork(int dst, int src) {
    this.ndst = dst;
    this.nsrc = src;
  }

}
