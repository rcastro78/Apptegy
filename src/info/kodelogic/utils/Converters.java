package info.kodelogic.utils;

public class Converters {

  public String getMonthName(int n) {
    String mName="";

    if (n == 1) {mName="Jan.";}
    if (n == 2) {mName="Feb.";}
    if (n == 3) {mName="Mar.";}
    if (n == 4) {mName="Apr.";}
    if (n == 5) {mName="May";}
    if (n == 6) {mName="Jun.";}
    if (n == 7) {mName="Jul.";}
    if (n == 8) {mName="Aug.";}
    if (n == 9) {mName="Sep.";}
    if (n == 10) {mName="Oct.";}
    if (n == 11) {mName="Nov.";}
    if (n == 12) {mName="Dec.";}

    return mName;
  }


  public String dateAdd(String d) {
    String dt = "";
    if (d == "1" || d == "11" || d == "21" || d == "31") {dt="st";}
    if (d == "2" || d == "12" || d == "22") {dt="nd";}
    if (d == "3" || d == "13" || d == "23") {dt="rd";}
    if (d.endsWith("0") || d.endsWith("4") || d.endsWith("5") || d.endsWith("6") || d.endsWith("7") || d.endsWith("8") || d.endsWith("9") ) {dt="th";}
    return dt;
  }
}