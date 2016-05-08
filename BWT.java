package Compression;

import java.util.Arrays;

public class BWT { 
	public String enCode(String str){
		char[] txt = (str + "$").toCharArray();
		String spinneds[] = new String[txt.length];
		char first;
		spinneds[0] = new String(txt);
		// Rotate
		for(int i=1;i<txt.length;i++){
			first = txt[0];
			for(int j=0;j<txt.length-1;j++) txt[j] = txt[j+1];
		    txt[txt.length-1] = first;
		    spinneds[i] = new String(txt);
		    }
		//Sort
		Arrays.sort(spinneds);
		//Complete
		String res = "";
		for(String spin : spinneds) res += spin.charAt(spin.length()-1); 
		return res;
		}
	
	public String deCode(String txt) {
		String[] fullTable = new String[txt.length()];
		for (int i = 0; i < txt.length(); i++) {
			fullTable[i] ="";
			fullTable[i] = fullTable[i] + txt.charAt(i);
		}
		for (int i = 1; i < txt.length(); i++) {
			Arrays.sort(fullTable);
			for (int j = 0; j < txt.length(); j++) {
				fullTable[j] = txt.toCharArray()[j] + fullTable[j];
			}
		}
		String msg = "Error.";
		for (String e : fullTable) {
			if (e.charAt(txt.length() - 1) == '$')
				msg = e;
		}
		return msg.substring(0, msg.length() - 1);
	}
	
	public static void main(String[] args) {
		String str = "SIX.MIXED.PIXIES.SIFT.SIXTY.PIXIE.DUST.BOXES";
		BWT bw = new BWT();
		String encodeStr = bw.enCode(str);
		System.out.println(encodeStr);
		String decodeStr = bw.deCode(encodeStr);
		System.out.println(decodeStr);
	}
}
