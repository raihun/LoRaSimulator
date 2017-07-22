public class Packet {

  private int ddst;
  private int dsrc;
  private int ndst;
  private int nsrc;
  private byte[] payload = new byte[50];

  public Packet() {}

  public setDatalink(int dst, int src) {
    this.ddst = dst;
    this.dsrc = src;
  }

  public setNetwork(int dst, int src) {
    this.ndst = dst;
    this.nsrc = src;
  }

}
