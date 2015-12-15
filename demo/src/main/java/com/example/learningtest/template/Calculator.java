package com.example.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public int calcSum(String filepath) throws IOException {
		return lineReadTemplate(filepath, new LineCallback<Integer>() {

			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return Integer.valueOf(line) + value;
			}
			
		}, 0);
	}

	public Integer calcMultiply(String filepath) throws IOException {
		return lineReadTemplate(filepath, new LineCallback<Integer>() {

			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return Integer.valueOf(line) * value;
			}
			
		}, 1);
	}
	
	public String concatenate(String filepath) throws IOException {
		return lineReadTemplate(filepath, new LineCallback<String>() {

			@Override
			public String doSomethingWithLine(String line, String value) {
				return line + value;
			}
			
		}, "");
	}
	
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		} catch (IOException e) {
			throw e;
		} finally {
			if (br != null) { try { br.close(); } catch (IOException e) {} }
		}
	}

}
