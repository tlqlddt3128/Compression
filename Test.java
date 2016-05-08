package Compression;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import Compression.BWT;
import Compression.LZW;
import Compression.RunLengthEncoding;

public class Test {
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String RLECompress(String content) {
		BWT bwt = new BWT();
		String encoded = bwt.enCode(content);
		String rleCompressed = RunLengthEncoding.encode(encoded);
		return rleCompressed;
	}
	
	public static String RLEDecompress(String compressed) {
		BWT bwt = new BWT();
		String temp = RunLengthEncoding.decode(compressed);
		String decoded = bwt.deCode(temp);
		return decoded;
	}
	
	public static List<Integer> LZWCompress(String content) {
		BWT bwt = new BWT();
		String encoded = bwt.enCode(content);
		List<Integer> LZWCompressed = LZW.compress(encoded);
		return LZWCompressed;
	}
	
	public static String LZWDecompress(List<Integer> compressed) {
		BWT bwt = new BWT();
		String temp = LZW.decompress(compressed);
		String decoded = bwt.deCode(temp);
		return decoded;
	}

	public static void main(String[] args) throws IOException {
		String path = "aaaaaaaaaa.txt";
		String content = readFile(path, StandardCharsets.UTF_8);
		String rlecom = RLECompress(content);
		System.out.println(rlecom);
		List<Integer> lzwcom = LZWCompress(content);
		System.out.println(lzwcom);
		String rledocom = RLEDecompress(rlecom);
		System.out.println(rledocom);
		String lzwdecom = LZWDecompress(lzwcom);
		System.out.println(lzwdecom);
	}

}
