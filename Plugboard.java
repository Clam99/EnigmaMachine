

public class Plugboard {
  private int[] map;
  
  public Plugboard(String[] s) {
    map=new int[26];
    for (int i=0; i<26; i++) {
      map[i]=i;
    }
    for (int i=0; i<s.length; i++) {
      if (s[i].length()==2) {
        int p=toNum(s[i].charAt(0));
        int q=toNum(s[i].charAt(1));
        if (map[p]==p&&map[q]==q) { //If they have not been altered yet
          map[p]=q; map[q]=p;
        }
      }
    }
  }
  
  private int toNum(char c) {
    return (int)c-65;
  }
  
  public char output(char in) {
    int num=map[(int)in-65];
    return (char)(num+65);
  }

}
